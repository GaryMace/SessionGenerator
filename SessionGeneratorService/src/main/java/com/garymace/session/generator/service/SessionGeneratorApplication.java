package com.garymace.session.generator.service;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.garymace.session.generator.service.config.SessionGeneratorServiceConfiguration;
import com.garymace.session.generator.service.config.SessionGeneratorServiceModule;
import com.hubspot.dropwizard.guicier.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SessionGeneratorApplication
  extends Application<SessionGeneratorServiceConfiguration> {

  public static void main(String... args) throws Exception {
    new SessionGeneratorApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<SessionGeneratorServiceConfiguration> bootstrap) {
    GuiceBundle<SessionGeneratorServiceConfiguration> guiceBundle = GuiceBundle
      .defaultBuilder(SessionGeneratorServiceConfiguration.class)
      .modules(new SessionGeneratorServiceModule())
        .enableGuiceEnforcer(false)
      .build();

    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(
    SessionGeneratorServiceConfiguration sessionGeneratorServiceConfiguration,
    Environment environment
  )
    throws Exception {
    // Enable CORS headers
    FilterRegistration.Dynamic cors =
        environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    // Configure CORS parameters
    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

    // Add URL mapping
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
  }
}
