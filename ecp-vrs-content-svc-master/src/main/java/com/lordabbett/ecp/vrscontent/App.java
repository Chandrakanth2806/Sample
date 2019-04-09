package com.lordabbett.ecp.vrscontent;

import com.lordabbett.ecp.commons.logging.RequestIdFilter;
import com.lordabbett.ecp.commons.model.AemAssetType;
import com.lordabbett.ecp.commons.model.AemEventType;
import com.lordabbett.ecp.commons.security.SystemJwtTokenGenerationService;
import com.lordabbett.ecp.vrscontent.config.AssetMappingConfig;
import com.lordabbett.ecp.vrscontent.service.AssetTransferOrchestrator;
import com.lordabbett.ecp.vrscontent.service.BiographyActivationOrchestrator;
import com.lordabbett.ecp.vrscontent.service.BiographyAssetService;
import com.lordabbett.ecp.vrscontent.service.BiographyDataService;
import com.lordabbett.ecp.vrscontent.service.BiographyDeactivationOrchestrator;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

  @Value("${ecp.privateKey}")
  private String secretKey;
  @Value("${spring.application.name}")
  private String applicationId;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean
  public SystemJwtTokenGenerationService systemJwtTokenGenerationService(
      ApplicationContext applicationContext) {
    return new SystemJwtTokenGenerationService(secretKey, applicationContext);
  }

  @Bean
  public RequestIdFilter requestIdFilter() {
    return new RequestIdFilter();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer(
      MeterRegistry meterRegistry) {
    return myMeterRegistry -> meterRegistry.config().commonTags("application.id", applicationId);
  }

  @Bean("orchestratorMapping")
  public Map<String, AssetTransferOrchestrator> assetHandlerMapping(
      BiographyAssetService biographyAssetService, BiographyDataService biographyDataService,
      AssetMappingConfig assetMappingConfig) {
    Map<String, AssetTransferOrchestrator> assetHandlerMap = new HashMap<>();
    assetHandlerMap.put(AemAssetType.IP_BIOGRAPHY + "_" + AemEventType.AEM_ASSET_ACTIVATED,
        biographyActivationOrchestrator(biographyAssetService, biographyDataService,
            assetMappingConfig)); assetHandlerMap
        .put(AemAssetType.IP_BIOGRAPHY + "_" + AemEventType.AEM_ASSET_DEACTIVATED,
            biographyDeactivationOrchestrator());
    return assetHandlerMap;
  }

  @Bean
  public BiographyActivationOrchestrator biographyActivationOrchestrator(
      BiographyAssetService biographyAssetService, BiographyDataService biographyDataService,
      AssetMappingConfig assetMappingConfig) {
    return new BiographyActivationOrchestrator(biographyAssetService, biographyDataService,
        assetMappingConfig);
  }

  @Bean
  public BiographyDeactivationOrchestrator biographyDeactivationOrchestrator() {
    return new BiographyDeactivationOrchestrator();
  }
}
