package com.lordabbett.ecp.vrscontent.service;

import static com.lordabbett.ecp.commons.logging.EcpLoggingConstants.ECP_REQUEST_ID;

import com.lordabbett.ecp.commons.security.SystemJwtTokenGenerationService;
import com.lordabbett.ecp.vrscontent.gateway.DamAssetGateway;
import com.lordabbett.ecp.vrscontent.model.AemAssetRequest;
import java.net.ConnectException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class DamAssetService {

  private DamAssetGateway aemAssetGateway;
  private SystemJwtTokenGenerationService systemJwtTokenGenerationService;

  @Autowired
  public DamAssetService(DamAssetGateway aemAssetGateway,
      SystemJwtTokenGenerationService systemJwtTokenGenerationService) {
    this.aemAssetGateway = aemAssetGateway;
    this.systemJwtTokenGenerationService = systemJwtTokenGenerationService;
  }

  @Retryable(value = {ConnectException.class}, maxAttempts = 3, backoff = @Backoff(delay = 500))
  public byte[] getAemAssetContent(AemAssetRequest aemAssetRequest, String messageId) {
    HttpHeaders headers = createHeaders(messageId);
    return aemAssetGateway.getAemAssetContent(aemAssetRequest, headers);
  }

  private HttpHeaders createHeaders(String messageId) {
    String token = systemJwtTokenGenerationService.getToken();
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, token);
    if(StringUtils.isNotEmpty(messageId)) {
      headers.set(ECP_REQUEST_ID, messageId);
    }
    return headers;
  }
}
