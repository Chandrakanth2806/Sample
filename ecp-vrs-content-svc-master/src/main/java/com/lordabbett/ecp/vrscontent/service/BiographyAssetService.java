package com.lordabbett.ecp.vrscontent.service;

import static com.lordabbett.ecp.commons.logging.EcpLoggingConstants.ECP_REQUEST_ID;

import com.lordabbett.ecp.commons.model.AemAssetType;
import com.lordabbett.ecp.commons.security.SystemJwtTokenGenerationService;
import com.lordabbett.ecp.vrscontent.gateway.BiographyAssetGateway;
import com.lordabbett.ecp.vrscontent.model.AemAssetRequest;
import com.lordabbett.ecp.vrscontent.model.BiographyDTO;
import java.net.ConnectException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class BiographyAssetService {

  private static final Logger LOG = LoggerFactory.getLogger(BiographyAssetService.class);
  private final BiographyAssetGateway biographyAssetGateway;
  private SystemJwtTokenGenerationService systemJwtTokenGenerationService;

  @Autowired
  public BiographyAssetService(BiographyAssetGateway biographyAssetGateway,
      SystemJwtTokenGenerationService systemJwtTokenGenerationService) {
    this.biographyAssetGateway = biographyAssetGateway;
    this.systemJwtTokenGenerationService = systemJwtTokenGenerationService;
  }

  @Retryable(value = {ConnectException.class}, maxAttempts = 3, backoff = @Backoff(delay = 500))
  public BiographyDTO getBiographyByPath(String path, String messageId) {
    LOG.info("Getting biography node from path: {}", path);
    HttpHeaders headers = createHeaders(messageId);
    AemAssetRequest aemAssetRequest = createAssetRequest(path); 
    return biographyAssetGateway.getBiography(aemAssetRequest, headers);
  }

  private AemAssetRequest createAssetRequest(String path) {
    AemAssetRequest assetRequest = new AemAssetRequest();
    assetRequest.setAssetType(AemAssetType.IP_BIOGRAPHY);
    assetRequest.setAssetPath(path);
    return assetRequest;
  }

  private HttpHeaders createHeaders(String messageId) {
    String token = systemJwtTokenGenerationService.getToken();
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, token);
    if (StringUtils.isNotEmpty(messageId)) {
      headers.set(ECP_REQUEST_ID, messageId);
    }
    return headers;
  }
}
