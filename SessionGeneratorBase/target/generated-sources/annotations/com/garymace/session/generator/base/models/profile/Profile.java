package com.garymace.session.generator.base.models.profile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.base.models.SportType;
import com.hubspot.immutables.validation.InvalidImmutableStateException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link ProfileIF}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code Profile.builder()}.
 */
@Generated(from = "ProfileIF", generator = "Immutables")
@SuppressWarnings({"all"})
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
public final class Profile implements ProfileIF {
  private final AthleticLevel athleticLevel;
  private final int weeklySessionPreference;
  private final SportType sportType;

  private Profile(
      AthleticLevel athleticLevel,
      int weeklySessionPreference,
      SportType sportType) {
    this.athleticLevel = athleticLevel;
    this.weeklySessionPreference = weeklySessionPreference;
    this.sportType = sportType;
  }

  /**
   * @return The value of the {@code athleticLevel} attribute
   */
  @JsonProperty
  @Override
  public AthleticLevel getAthleticLevel() {
    return athleticLevel;
  }

  /**
   * @return The value of the {@code weeklySessionPreference} attribute
   */
  @JsonProperty
  @Override
  public int getWeeklySessionPreference() {
    return weeklySessionPreference;
  }

  /**
   * @return The value of the {@code sportType} attribute
   */
  @JsonProperty
  @Override
  public SportType getSportType() {
    return sportType;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ProfileIF#getAthleticLevel() athleticLevel} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for athleticLevel
   * @return A modified copy of the {@code this} object
   */
  public final Profile withAthleticLevel(AthleticLevel value) {
    if (this.athleticLevel == value) return this;
    AthleticLevel newValue = Objects.requireNonNull(value, "athleticLevel");
    if (this.athleticLevel.equals(newValue)) return this;
    return new Profile(newValue, this.weeklySessionPreference, this.sportType);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ProfileIF#getWeeklySessionPreference() weeklySessionPreference} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for weeklySessionPreference
   * @return A modified copy of the {@code this} object
   */
  public final Profile withWeeklySessionPreference(int value) {
    if (this.weeklySessionPreference == value) return this;
    return new Profile(this.athleticLevel, value, this.sportType);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ProfileIF#getSportType() sportType} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for sportType
   * @return A modified copy of the {@code this} object
   */
  public final Profile withSportType(SportType value) {
    if (this.sportType == value) return this;
    SportType newValue = Objects.requireNonNull(value, "sportType");
    if (this.sportType.equals(newValue)) return this;
    return new Profile(this.athleticLevel, this.weeklySessionPreference, newValue);
  }

  /**
   * This instance is equal to all instances of {@code Profile} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof Profile
        && equalTo((Profile) another);
  }

  private boolean equalTo(Profile another) {
    return athleticLevel.equals(another.athleticLevel)
        && weeklySessionPreference == another.weeklySessionPreference
        && sportType.equals(another.sportType);
  }

  /**
   * Computes a hash code from attributes: {@code athleticLevel}, {@code weeklySessionPreference}, {@code sportType}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + athleticLevel.hashCode();
    h += (h << 5) + weeklySessionPreference;
    h += (h << 5) + sportType.hashCode();
    return h;
  }


  /**
   * Prints the immutable value {@code Profile} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Profile{"
        + "athleticLevel=" + athleticLevel
        + ", weeklySessionPreference=" + weeklySessionPreference
        + ", sportType=" + sportType
        + "}";
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "ProfileIF", generator = "Immutables")
  @Deprecated
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements ProfileIF {
    @Nullable AthleticLevel athleticLevel;
    int weeklySessionPreference;
    boolean weeklySessionPreferenceIsSet;
    @Nullable SportType sportType;
    @JsonProperty
    public void setAthleticLevel(AthleticLevel athleticLevel) {
      this.athleticLevel = athleticLevel;
    }
    @JsonProperty
    public void setWeeklySessionPreference(int weeklySessionPreference) {
      this.weeklySessionPreference = weeklySessionPreference;
      this.weeklySessionPreferenceIsSet = true;
    }
    @JsonProperty
    public void setSportType(SportType sportType) {
      this.sportType = sportType;
    }
    @Override
    public AthleticLevel getAthleticLevel() { throw new UnsupportedOperationException(); }
    @Override
    public int getWeeklySessionPreference() { throw new UnsupportedOperationException(); }
    @Override
    public SportType getSportType() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static Profile fromJson(Json json) {
    Profile.Builder builder = Profile.builder();
    if (json.athleticLevel != null) {
      builder.setAthleticLevel(json.athleticLevel);
    }
    if (json.weeklySessionPreferenceIsSet) {
      builder.setWeeklySessionPreference(json.weeklySessionPreference);
    }
    if (json.sportType != null) {
      builder.setSportType(json.sportType);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link ProfileIF} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Profile instance
   */
  public static Profile copyOf(ProfileIF instance) {
    if (instance instanceof Profile) {
      return (Profile) instance;
    }
    return Profile.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link Profile Profile}.
   * <pre>
   * Profile.builder()
   *    .setAthleticLevel(com.garymace.session.generator.base.models.AthleticLevel) // required {@link ProfileIF#getAthleticLevel() athleticLevel}
   *    .setWeeklySessionPreference(int) // required {@link ProfileIF#getWeeklySessionPreference() weeklySessionPreference}
   *    .setSportType(com.garymace.session.generator.base.models.SportType) // required {@link ProfileIF#getSportType() sportType}
   *    .build();
   * </pre>
   * @return A new Profile builder
   */
  public static Profile.Builder builder() {
    return new Profile.Builder();
  }

  /**
   * Builds instances of type {@link Profile Profile}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "ProfileIF", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_ATHLETIC_LEVEL = 0x1L;
    private static final long INIT_BIT_WEEKLY_SESSION_PREFERENCE = 0x2L;
    private static final long INIT_BIT_SPORT_TYPE = 0x4L;
    private long initBits = 0x7L;

    private @Nullable AthleticLevel athleticLevel;
    private int weeklySessionPreference;
    private @Nullable SportType sportType;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code ProfileIF} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(ProfileIF instance) {
      Objects.requireNonNull(instance, "instance");
      setAthleticLevel(instance.getAthleticLevel());
      setWeeklySessionPreference(instance.getWeeklySessionPreference());
      setSportType(instance.getSportType());
      return this;
    }

    /**
     * Initializes the value for the {@link ProfileIF#getAthleticLevel() athleticLevel} attribute.
     * @param athleticLevel The value for athleticLevel 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder setAthleticLevel(AthleticLevel athleticLevel) {
      this.athleticLevel = Objects.requireNonNull(athleticLevel, "athleticLevel");
      initBits &= ~INIT_BIT_ATHLETIC_LEVEL;
      return this;
    }

    /**
     * Initializes the value for the {@link ProfileIF#getWeeklySessionPreference() weeklySessionPreference} attribute.
     * @param weeklySessionPreference The value for weeklySessionPreference 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder setWeeklySessionPreference(int weeklySessionPreference) {
      this.weeklySessionPreference = weeklySessionPreference;
      initBits &= ~INIT_BIT_WEEKLY_SESSION_PREFERENCE;
      return this;
    }

    /**
     * Initializes the value for the {@link ProfileIF#getSportType() sportType} attribute.
     * @param sportType The value for sportType 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder setSportType(SportType sportType) {
      this.sportType = Objects.requireNonNull(sportType, "sportType");
      initBits &= ~INIT_BIT_SPORT_TYPE;
      return this;
    }

    /**
     * Builds a new {@link Profile Profile}.
     * @return An immutable instance of Profile
     * @throws com.hubspot.immutables.validation.InvalidImmutableStateException if any required attributes are missing
     */
    public Profile build() {
      checkRequiredAttributes();
      return new Profile(athleticLevel, weeklySessionPreference, sportType);
    }

    private boolean athleticLevelIsSet() {
      return (initBits & INIT_BIT_ATHLETIC_LEVEL) == 0;
    }

    private boolean weeklySessionPreferenceIsSet() {
      return (initBits & INIT_BIT_WEEKLY_SESSION_PREFERENCE) == 0;
    }

    private boolean sportTypeIsSet() {
      return (initBits & INIT_BIT_SPORT_TYPE) == 0;
    }

    private void checkRequiredAttributes() {
      if (initBits != 0) {
        throw new InvalidImmutableStateException(formatRequiredAttributesMessage());
      }
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if (!athleticLevelIsSet()) attributes.add("athleticLevel");
      if (!weeklySessionPreferenceIsSet()) attributes.add("weeklySessionPreference");
      if (!sportTypeIsSet()) attributes.add("sportType");
      return "Cannot build Profile, some of required attributes are not set " + attributes;
    }
  }
}
