package com.garymace.session.generator.base.models.session.rules.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.garymace.session.generator.base.models.AthleticLevel;
import com.hubspot.immutables.style.HubSpotStyle;
import java.util.Set;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface RuleIF {
  AthleticLevel getAthleticLevel();
  int getMaxReps();
  int getMinDistance();
  int getMaxDistance();
  Set<DistanceDetail> getPermittedDistanceDetails();
}
