package com.lordabbett.ecp.vrscontent.gateway;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lordabbett.ecp.vrscontent.model.AemAssetRequest;
import com.lordabbett.ecp.vrscontent.model.BiographyDTO;

@RunWith(MockitoJUnitRunner.class)
public class BiographyAssetGatewayTest extends Mockito {

	BiographyAssetGateway biographyAssetGateway;

	@Mock
	AemAssetRequest aemAssetRequest;

	@Mock
	HttpHeaders httpHeaders;

	@Mock
	RestTemplate restTemplate;

	@Mock
	ResponseEntity<BiographyDTO> myEntity;

	@Mock
	BiographyDTO biographyDTO;

	String biographyQueryServiceUrl = "https://www.google.com";

	@Before
	public void setup() {
		biographyAssetGateway = spy(new BiographyAssetGateway("TestString", restTemplate));
		Whitebox.setInternalState(biographyAssetGateway, "biographyQueryServiceUrl", biographyQueryServiceUrl);
		Whitebox.setInternalState(biographyAssetGateway, "restTemplate", restTemplate);
	}

	@Test
	public void GetBiography() {
		when(myEntity.getBody()).thenReturn(biographyDTO);
		when(restTemplate.exchange(biographyQueryServiceUrl + "/biography-assets/search", HttpMethod.POST,
				new HttpEntity<>(aemAssetRequest, httpHeaders), BiographyDTO.class)).thenReturn(myEntity);
		biographyAssetGateway.getBiography(aemAssetRequest, httpHeaders);
		verify(myEntity, times(1)).getBody();
	}
}
