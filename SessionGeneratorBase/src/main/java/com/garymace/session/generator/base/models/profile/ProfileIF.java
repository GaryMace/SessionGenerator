package com.garymace.session.generator.base.models.profile;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.base.models.SportType;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface ProfileIF {
    AthleticLevel getAthleticLevel();
    int getWeeklySessionPreference();
    SportType getSportType();
}
