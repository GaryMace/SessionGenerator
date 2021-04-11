package com.garymace.session.generator.base.models.session.rules.config;

import org.immutables.value.Value;
import org.immutables.value.Value.Parameter;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.garymace.session.generator.base.models.session.SetType;
import com.hubspot.immutables.style.HubSpotStyle;

@Value.Immutable
@HubSpotStyle
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface RepDetailIF {
  @Parameter(order = 0)
  SetType getSetType();

  @Parameter(order = 1)
  int getMin();

  @Parameter(order = 2)
  int getMax();
}
