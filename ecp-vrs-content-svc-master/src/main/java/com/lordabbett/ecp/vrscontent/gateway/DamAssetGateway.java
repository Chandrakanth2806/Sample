package com.lordabbett.ecp.vrscontent.gateway;

import com.lordabbett.ecp.vrscontent.model.AemAssetRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Gateway to the AEM Query Service.
 */
@Component
public class DamAssetGateway {

  private static final Logger LOG = LoggerFactory.getLogger(DamAssetGateway.class);
  private static final String AEM_ASSET_CONTENT_SEARCH = "/aem-assets/content/search";
  private final String aemQueryServiceUrl;
  private final RestTemplate restTemplate;

  @Autowired
  public DamAssetGateway(@Value("${aemQueryServiceUrl}") String aemQueryServiceUrl,
      RestTemplate restTemplate) {
    this.aemQueryServiceUrl = aemQueryServiceUrl;
    this.restTemplate = restTemplate;
  }

  @HystrixCommand(groupKey = "assetgatewayservice", commandKey = "getAemAssetContent")
  public byte[] getAemAssetContent(AemAssetRequest aemAssetRequest, HttpHeaders httpHeaders) {
    LOG.info("Fetching asset content from AEM for assetRequest: {}", aemAssetRequest);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(aemQueryServiceUrl)
        .path(AEM_ASSET_CONTENT_SEARCH);
    ResponseEntity<byte[]> responseEntity = restTemplate
        .exchange(builder.toUriString(), HttpMethod.POST,
            new HttpEntity<>(aemAssetRequest, httpHeaders), byte[].class);
    return responseEntity.getBody();
  }
}
