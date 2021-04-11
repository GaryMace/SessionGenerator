package com.garymace.session.generator.service.shared.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garymace.session.generator.base.models.profile.Profile;
import com.google.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfileLoadingUtils {
  private static final Logger LOG = LoggerFactory.getLogger(ProfileLoadingUtils.class);

  private final ObjectMapper objectMapper;

  @Inject
  public ProfileLoadingUtils(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public Optional<Profile> loadProfile(String[] args) {
    try {
      String fileContent = new String(Files.readAllBytes(Paths.get(args[0])));
      return Optional.of(objectMapper.readValue(fileContent, Profile.class));
    } catch (Exception e) {
      LOG.error("Error occurred while loading profile: ", e);
    }
    return Optional.empty();
  }
}
