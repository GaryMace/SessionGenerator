package com.garymace.session.generator.base.models.session.rules;

import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.config.DistanceDetail;
import com.hubspot.immutables.style.HubSpotStyle;
import java.util.Set;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SessionRulesIF {
  SessionStageType getSessionStageType();
  int getMaxReps();
  int getMinDistance();
  int getMaxDistance();
  Set<DistanceDetail> getPermittedDistanceDetails();
}
