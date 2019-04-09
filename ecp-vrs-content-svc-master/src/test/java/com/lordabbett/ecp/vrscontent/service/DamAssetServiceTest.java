package com.lordabbett.ecp.vrscontent.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.http.HttpHeaders;
import com.lordabbett.ecp.commons.security.SystemJwtTokenGenerationService;
import com.lordabbett.ecp.vrscontent.gateway.DamAssetGateway;
import com.lordabbett.ecp.vrscontent.model.AemAssetRequest;

@RunWith(MockitoJUnitRunner.class)
public class DamAssetServiceTest extends Mockito {

	DamAssetService damAssetService;

	@Mock
	DamAssetGateway damAssetGateway;

	@Mock
	SystemJwtTokenGenerationService systemJwtTokenGenerationService;

	@Mock
	AemAssetRequest aemAssetRequest;

	@Mock
	HttpHeaders httpHeaders;

	@Before
	public void setup() {
		damAssetService = spy(new DamAssetService(damAssetGateway, systemJwtTokenGenerationService));
		Whitebox.setInternalState(damAssetService, "aemAssetGateway", damAssetGateway);
	}

	@Test
	public void TestOne() {
		damAssetService.getAemAssetContent(aemAssetRequest, "abc");
		verify(damAssetGateway, times(1)).getAemAssetContent(any(AemAssetRequest.class), any(HttpHeaders.class));
	}

}
