package com.garymace.session.generator.base.models.session.brief.sport;

import com.garymace.session.generator.base.models.session.brief.TrainingSessionsBase;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SwimTrainingSessionsIF
  extends TrainingSessionsBase<SwimTrainingSession> {}
