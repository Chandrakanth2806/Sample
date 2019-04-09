package com.lordabbett.ecp.vrscontent.resource;

import static com.lordabbett.ecp.commons.logging.EcpLoggingConstants.ECP_REQUEST_ID;

import com.lordabbett.ecp.vrscontent.model.AssetTransferRequest;
import com.lordabbett.ecp.vrscontent.model.AssetTransferResponse;
import com.lordabbett.ecp.vrscontent.model.entity.Biography;
import com.lordabbett.ecp.vrscontent.model.exception.PropagatingSystemException;
import com.lordabbett.ecp.vrscontent.service.AssetTransferOrchestrator;
import com.lordabbett.ecp.vrscontent.service.BiographyDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "biography-resource", description = "Operations to fetch or update IP Biography data")
public class BiographyResource {

  private static final Logger LOG = LoggerFactory.getLogger(BiographyResource.class);
  private Map<String, AssetTransferOrchestrator> assetHandlerMap;
  private BiographyDataService biographyDataService;

  @Autowired
  public BiographyResource(
      @Qualifier("orchestratorMapping") Map<String, AssetTransferOrchestrator> assetHandlerMap,
      BiographyDataService biographyDataService) {
    this.assetHandlerMap = assetHandlerMap;
    this.biographyDataService = biographyDataService;
  }

  @GetMapping(path = "/ip-biography/{personId}", produces = "application/json")
  @ApiOperation(value = "Get IP Biography Data by Id", response = Biography.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful transfer of Asset from AEM to ET", response = Biography.class),
      @ApiResponse(code = 404, message = "IP Bio does not exist in DB", response = PropagatingSystemException.class),
      @ApiResponse(code = 500, message = "Internal server error", response = PropagatingSystemException.class)})
  public Biography getPersonId(@PathVariable("personId") @NotNull Integer personId,
      @RequestHeader HttpHeaders headers) {
    LOG.info("starting to process request: {} ", personId);
    return biographyDataService.findByPersonId(personId);
  }

  @PostMapping(path = "/ip-biography", consumes = "application/json", produces = "application/json")
  @ApiOperation(value = "Manually transfer an asset from AEM to the VRS database", response = AssetTransferResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful transfer of IP Biography from AEM to DB", response = AssetTransferResponse.class),
      @ApiResponse(code = 404, message = "IP Bio does not exist in AEM", response = PropagatingSystemException.class),
      @ApiResponse(code = 500, message = "Internal server error", response = PropagatingSystemException.class)})
  public AssetTransferResponse transferAemAsset(
      @RequestBody AssetTransferRequest assetTransferRequest, @RequestHeader HttpHeaders headers) {
    LOG.info("starting to process request: {} ", assetTransferRequest);
    String messageId = headers.getFirst(ECP_REQUEST_ID);
    AssetTransferOrchestrator orchestrator = assetHandlerMap
        .get(assetTransferRequest.getAssetType() + "_" + assetTransferRequest.getEventType());
    return orchestrator.processAssetTransferRequest(assetTransferRequest, messageId);
  }
}
