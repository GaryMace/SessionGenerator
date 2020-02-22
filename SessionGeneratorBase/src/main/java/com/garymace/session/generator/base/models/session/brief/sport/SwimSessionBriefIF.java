package com.garymace.session.generator.base.models.session.brief.sport;

import com.garymace.session.generator.base.models.session.brief.SessionBriefBase;
import com.garymace.session.generator.base.models.session.brief.SessionBriefCooldown;
import com.garymace.session.generator.base.models.session.brief.SessionBriefWarmup;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SwimSessionBriefIF extends SessionBriefBase, SessionBriefWarmup, SessionBriefCooldown {
}
