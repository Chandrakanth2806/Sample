package com.lordabbett.ecp.vrscontent.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.lordabbett.ecp.commons.model.AemAssetType;
import com.lordabbett.ecp.vrscontent.config.AssetMappingConfig;
import com.lordabbett.ecp.vrscontent.model.AssetTransferRequest;
import com.lordabbett.ecp.vrscontent.model.AssetTransferResponse;
import com.lordabbett.ecp.vrscontent.model.BiographyDTO;
import com.lordabbett.ecp.vrscontent.model.entity.Biography;

@RunWith(MockitoJUnitRunner.class)
public class BiographyActivationOrchestratorTest extends Mockito {

	BiographyActivationOrchestrator biographyActivationOrchestrator;

	@Mock
	AssetTransferRequest assetTransferRequest;

	@Mock
	AssetTransferOrchestrator assetTransferOrchestrator;

	@Mock
	BiographyAssetService biographyAssetService;

	@Mock
	AssetMappingConfig assetMappingConfig;

	@Mock
	BiographyDataService biographyDataService;

	@Mock
	Biography biography;

	@Mock
	BiographyDTO biographyDTO;

	@Before
	public void setup() {
		biographyActivationOrchestrator = spy(
				new BiographyActivationOrchestrator(biographyAssetService, biographyDataService, assetMappingConfig));
		when(assetTransferRequest.getAssetPath()).thenReturn("assetpath");
		when(biographyDTO.getPersonId()).thenReturn(1234);
		when(biographyDTO.getDesignation()).thenReturn("Designation");
		when(biographyDTO.getDisplayName()).thenReturn("DisplayName");
		when(biographyDTO.getTitle()).thenReturn("Title");
		when(biographyDTO.getHeadshotPath()).thenReturn("HeadShotpath");
		when(biographyAssetService.getBiographyByPath(anyString(), anyString())).thenReturn(biographyDTO);
		when(biographyDataService.save(any(Biography.class))).thenReturn(biography);
	}

	@Test
	public void ProcessBIOAssetTransferRequest() {
		when(assetTransferRequest.getAssetType()).thenReturn(AemAssetType.BIO);
		AssetTransferResponse a = biographyActivationOrchestrator.processAssetTransferRequest(assetTransferRequest,
				"anyString");
		assertTrue(a.getMessage().contains(("The BIO was successfully inserted/updated in the database")));
	}

	@Test
	public void ProcessLEGALDISCLOSUREAssetTransferRequest() {
		when(assetTransferRequest.getAssetType()).thenReturn(AemAssetType.LEGAL_DISCLOSURE);
		AssetTransferResponse a = biographyActivationOrchestrator.processAssetTransferRequest(assetTransferRequest,
				"anyString");
		assertTrue(a.getMessage().contains(("The LEGAL_DISCLOSURE was successfully inserted/updated in the database")));
	}

	@Test
	public void ProcessIPBIOGRAPHYAssetTransferRequest() {
		when(assetTransferRequest.getAssetType()).thenReturn(AemAssetType.IP_BIOGRAPHY);
		AssetTransferResponse a = biographyActivationOrchestrator.processAssetTransferRequest(assetTransferRequest,
				"anyString");
		assertTrue(a.getMessage().contains(("The IP_BIOGRAPHY was successfully inserted/updated in the database")));
	}
}
