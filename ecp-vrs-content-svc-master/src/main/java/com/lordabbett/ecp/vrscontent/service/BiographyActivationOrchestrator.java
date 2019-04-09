package com.lordabbett.ecp.vrscontent.service;

import com.lordabbett.ecp.vrscontent.config.AssetMappingConfig;
import com.lordabbett.ecp.vrscontent.model.AssetTransferRequest;
import com.lordabbett.ecp.vrscontent.model.AssetTransferResponse;
import com.lordabbett.ecp.vrscontent.model.BiographyDTO;
import com.lordabbett.ecp.vrscontent.model.entity.Biography;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

public class BiographyActivationOrchestrator implements AssetTransferOrchestrator {

  private BiographyAssetService biographyAssetService;
  private BiographyDataService biographyDataService;
  private AssetMappingConfig assetMappingConfig;

  public BiographyActivationOrchestrator(BiographyAssetService biographyAssetService,
      BiographyDataService biographyDataService, AssetMappingConfig assetMappingConfig) {
    this.biographyAssetService = biographyAssetService;
    this.biographyDataService = biographyDataService;
    this.assetMappingConfig = assetMappingConfig;
  }

  /**
   * Process the incoming asset initialization kafka message
   */
  @Override
  public AssetTransferResponse processAssetTransferRequest(
      AssetTransferRequest assetTransferRequest, String messageId) {
    String assetPath = assetTransferRequest.getAssetPath();
    BiographyDTO biographyDTO = biographyAssetService.getBiographyByPath(assetPath, messageId);
    Biography biography = biographyDataService.save(transformToEntity(biographyDTO));
    return getAssetTransferResponse(assetTransferRequest, biography);
  }

  private Biography transformToEntity(BiographyDTO biographyDTO) {
    Biography biography = new Biography();
    biography.setPersonId(biographyDTO.getPersonId());
    biography.setDesignation(biographyDTO.getDesignation());
    biography.setDisplayName(biographyDTO.getDisplayName());
    biography.setTitle(biographyDTO.getTitle());
    biography.setImagePath(biographyDTO.getHeadshotPath());
    biography.setLastUpdated(LocalDateTime.now());
    biography.setLastUpdatedUser("ecp-vrs-content-svc");
    return biography;
  }

  private AssetTransferResponse getAssetTransferResponse(AssetTransferRequest assetTransferRequest,
      Biography biography) {
    AssetTransferResponse assetTransferResponse = new AssetTransferResponse();
    assetTransferResponse.setRequest(assetTransferRequest);
    assetTransferResponse.setMappingIds(Collections.singletonList(biography.getPersonId()));
    assetTransferResponse.setMessage("The " + assetTransferRequest.getAssetType().name()
        + " was successfully inserted/updated in the database");
    return assetTransferResponse;
  }
}
