package com.garymace.session.generator.base.models.session.rules.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.garymace.session.generator.base.models.session.SetType;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
@HubSpotStyle
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface PostSetRestDurationIF {
  @Parameter(order = 0)
  SetType getSetType();

  @JsonProperty("rest")
  @Parameter(order = 1)
  int getRestDurationSeconds();
}
