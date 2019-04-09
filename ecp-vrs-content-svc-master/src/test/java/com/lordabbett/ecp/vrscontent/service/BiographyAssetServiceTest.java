package com.lordabbett.ecp.vrscontent.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.lordabbett.ecp.commons.security.SystemJwtTokenGenerationService;
import com.lordabbett.ecp.vrscontent.gateway.BiographyAssetGateway;

@RunWith(MockitoJUnitRunner.class)
public class BiographyAssetServiceTest extends Mockito {

	BiographyAssetService biographyAssetService;

	@Mock
	BiographyAssetGateway biographyAssetGateway;

	@Mock
	SystemJwtTokenGenerationService systemJwtTokenGenerationService;

	@Before
	public void setup() {
		biographyAssetService = spy(new BiographyAssetService(biographyAssetGateway, systemJwtTokenGenerationService));
	}

	@Test
	public void BioGraphyPath() {
		biographyAssetService.getBiographyByPath("path", "anyMessageID");
		verify(biographyAssetService, times(1)).getBiographyByPath(anyString(), anyString());
	}
}
