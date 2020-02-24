package com.garymace.session.generator.base.models.profile;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.base.models.SportType;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface ProfileIF {
    AthleticLevel getAthleticLevel();
    int getWeeklySessionPreference();
    SportType getSportType();
}
