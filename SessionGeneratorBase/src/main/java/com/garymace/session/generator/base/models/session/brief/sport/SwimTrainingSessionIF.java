package com.garymace.session.generator.base.models.session.brief.sport;

import com.garymace.session.generator.base.models.session.brief.TrainingSessionBase;
import com.garymace.session.generator.base.models.session.brief.TrainingSessionCooldown;
import com.garymace.session.generator.base.models.session.brief.TrainingSessionWarmup;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SwimTrainingSessionIF extends TrainingSessionBase, TrainingSessionWarmup, TrainingSessionCooldown {
}
