package com.lordabbett.ecp.vrscontent.gateway;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lordabbett.ecp.vrscontent.model.AemAssetRequest;
import com.lordabbett.ecp.vrscontent.model.BiographyDTO;

@RunWith(MockitoJUnitRunner.class)
public class DamAssetGatewayTest extends Mockito {

	DamAssetGateway damAssetGateway;

	@Mock
	RestTemplate restTemplate;

	@Mock
	AemAssetRequest aemAssetRequest;

	@Mock
	HttpHeaders httpHeaders;

	@Mock
	ResponseEntity<byte[]> myEntity;

	String aemQueryServiceUrl = "https://www.google.com";

	@Before
	public void setup() {
		damAssetGateway = spy(new DamAssetGateway("TestString", restTemplate));
		Whitebox.setInternalState(damAssetGateway, "aemQueryServiceUrl", aemQueryServiceUrl);
		Whitebox.setInternalState(damAssetGateway, "restTemplate", restTemplate);
	}

	@Test
	public void TestOne() {
		when(myEntity.getBody()).thenReturn(new byte[] { 12, 14 });
		when(restTemplate.exchange(aemQueryServiceUrl + "/aem-assets/content/search", HttpMethod.POST,
				new HttpEntity<>(aemAssetRequest, httpHeaders), byte[].class)).thenReturn(myEntity);
		damAssetGateway.getAemAssetContent(aemAssetRequest, httpHeaders);
		verify(myEntity, times(1)).getBody();
	}
}
