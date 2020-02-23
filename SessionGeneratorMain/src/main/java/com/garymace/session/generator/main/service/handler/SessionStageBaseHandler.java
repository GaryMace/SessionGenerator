package com.garymace.session.generator.main.service.handler;

import com.garymace.session.generator.base.models.profile.Profile;

public interface SessionStageBaseHandler<T> {
    T handle(Profile profile);
}
