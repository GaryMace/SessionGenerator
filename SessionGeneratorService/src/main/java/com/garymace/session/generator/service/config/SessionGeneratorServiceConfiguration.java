package com.garymace.session.generator.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class SessionGeneratorServiceConfiguration extends Configuration {
  private boolean bindResources = true;
  private boolean bindFilters = true;
  private boolean bindExtras = true;

  @JsonProperty("bindResources")
  public boolean bindResources() {
    return bindResources;
  }

  public void setBindResources(boolean bindResources) {
    this.bindResources = bindResources;
  }

  @JsonProperty("bindFilters")
  public boolean bindFilters() {
    return bindFilters;
  }

  public void setBindFilters(boolean bindFilters) {
    this.bindFilters = bindFilters;
  }

  @JsonProperty("bindExtras")
  public boolean bindExtras() {
    return bindExtras;
  }

  public void setBindExtras(boolean bindExtras) {
    this.bindExtras = bindExtras;
  }
}
