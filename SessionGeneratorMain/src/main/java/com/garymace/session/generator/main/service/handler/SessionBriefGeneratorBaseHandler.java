package com.garymace.session.generator.main.service.handler;

import com.garymace.session.generator.base.models.profile.Profile;

import java.util.Set;

public interface SessionBriefGeneratorBaseHandler<T> {
    Set<T> handle(Profile profile);
}
