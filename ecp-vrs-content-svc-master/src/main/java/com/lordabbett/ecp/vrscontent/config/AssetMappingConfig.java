package com.lordabbett.ecp.vrscontent.config;

import com.lordabbett.ecp.vrscontent.model.config.BiographyMapping;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "assets")
public class AssetMappingConfig {

  private BiographyMapping biographyMapping;

  public BiographyMapping getBiographyMapping() {
    return biographyMapping;
  }

  public void setBiographyMapping(BiographyMapping biographyMapping) {
    this.biographyMapping = biographyMapping;
  }
}
