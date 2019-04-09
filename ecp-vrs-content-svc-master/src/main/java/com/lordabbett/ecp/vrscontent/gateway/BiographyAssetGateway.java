package com.lordabbett.ecp.vrscontent.gateway;

import com.lordabbett.ecp.vrscontent.model.AemAssetRequest;
import com.lordabbett.ecp.vrscontent.model.BiographyDTO;
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

@Component
public class BiographyAssetGateway {

	private static final Logger LOG = LoggerFactory.getLogger(BiographyAssetGateway.class);
	private static final String BIOGRAPHY_ASSET_SEARCH = "/biography-assets/search";
	private final String biographyQueryServiceUrl;
	private final RestTemplate restTemplate;

	@Autowired
	public BiographyAssetGateway(@Value("${biographyQueryServiceUrl}") String biographyQueryServiceUrl,
			RestTemplate restTemplate) {
		this.biographyQueryServiceUrl = biographyQueryServiceUrl;
		this.restTemplate = restTemplate;
	}

	public BiographyDTO getBiography(AemAssetRequest assetRequest, HttpHeaders httpHeaders) {
		LOG.info("Fetching asset content from AEM for assetRequest: {}", assetRequest);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(biographyQueryServiceUrl)
				.path(BIOGRAPHY_ASSET_SEARCH);
		ResponseEntity<BiographyDTO> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
				new HttpEntity<>(assetRequest, httpHeaders), BiographyDTO.class);
		return responseEntity.getBody();
	}
}
