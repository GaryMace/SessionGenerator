package com.garymace.session.generator.service;

import com.garymace.session.generator.service.config.SessionGeneratorServiceConfiguration;
import com.garymace.session.generator.service.config.SessionGeneratorServiceModule;
import com.hubspot.dropwizard.guicier.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SessionGeneratorApplication extends Application<SessionGeneratorServiceConfiguration> {

  public static void main(String... args) throws Exception {
    new SessionGeneratorApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<SessionGeneratorServiceConfiguration> bootstrap) {
    GuiceBundle<SessionGeneratorServiceConfiguration> guiceBundle = GuiceBundle.defaultBuilder(SessionGeneratorServiceConfiguration.class)
        .modules(new SessionGeneratorServiceModule())
        .build();

    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(SessionGeneratorServiceConfiguration sessionGeneratorServiceConfiguration, Environment environment) throws Exception {

  }
}
