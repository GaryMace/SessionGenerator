package com.garymace.session.generator.base.models.session.brief.sport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.garymace.session.generator.base.models.session.SessionBriefBase;
import com.garymace.session.generator.base.models.session.SessionItem;
import com.garymace.session.generator.base.models.session.brief.SessionBriefCooldown;
import com.garymace.session.generator.base.models.session.brief.SessionBriefWarmup;
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
 * Immutable implementation of {@link SwimSessionBriefIF}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code SwimSessionBrief.builder()}.
 */
@Generated(from = "SwimSessionBriefIF", generator = "Immutables")
@SuppressWarnings({"all"})
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
public final class SwimSessionBrief
    implements SwimSessionBriefIF {
  private final SessionItem mainsetSession;
  private final SessionItem warmupSession;
  private final SessionItem cooldownSession;

  private SwimSessionBrief(
      SessionItem mainsetSession,
      SessionItem warmupSession,
      SessionItem cooldownSession) {
    this.mainsetSession = mainsetSession;
    this.warmupSession = warmupSession;
    this.cooldownSession = cooldownSession;
  }

  /**
   * @return The value of the {@code mainsetSession} attribute
   */
  @JsonProperty
  @Override
  public SessionItem getMainsetSession() {
    return mainsetSession;
  }

  /**
   * @return The value of the {@code warmupSession} attribute
   */
  @JsonProperty
  @Override
  public SessionItem getWarmupSession() {
    return warmupSession;
  }

  /**
   * @return The value of the {@code cooldownSession} attribute
   */
  @JsonProperty
  @Override
  public SessionItem getCooldownSession() {
    return cooldownSession;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SwimSessionBriefIF#getMainsetSession() mainsetSession} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for mainsetSession
   * @return A modified copy of the {@code this} object
   */
  public final SwimSessionBrief withMainsetSession(SessionItem value) {
    if (this.mainsetSession == value) return this;
    SessionItem newValue = Objects.requireNonNull(value, "mainsetSession");
    return new SwimSessionBrief(newValue, this.warmupSession, this.cooldownSession);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SwimSessionBriefIF#getWarmupSession() warmupSession} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for warmupSession
   * @return A modified copy of the {@code this} object
   */
  public final SwimSessionBrief withWarmupSession(SessionItem value) {
    if (this.warmupSession == value) return this;
    SessionItem newValue = Objects.requireNonNull(value, "warmupSession");
    return new SwimSessionBrief(this.mainsetSession, newValue, this.cooldownSession);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SwimSessionBriefIF#getCooldownSession() cooldownSession} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for cooldownSession
   * @return A modified copy of the {@code this} object
   */
  public final SwimSessionBrief withCooldownSession(SessionItem value) {
    if (this.cooldownSession == value) return this;
    SessionItem newValue = Objects.requireNonNull(value, "cooldownSession");
    return new SwimSessionBrief(this.mainsetSession, this.warmupSession, newValue);
  }

  /**
   * This instance is equal to all instances of {@code SwimSessionBrief} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof SwimSessionBrief
        && equalTo((SwimSessionBrief) another);
  }

  private boolean equalTo(SwimSessionBrief another) {
    return mainsetSession.equals(another.mainsetSession)
        && warmupSession.equals(another.warmupSession)
        && cooldownSession.equals(another.cooldownSession);
  }

  /**
   * Computes a hash code from attributes: {@code mainsetSession}, {@code warmupSession}, {@code cooldownSession}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + mainsetSession.hashCode();
    h += (h << 5) + warmupSession.hashCode();
    h += (h << 5) + cooldownSession.hashCode();
    return h;
  }


  /**
   * Prints the immutable value {@code SwimSessionBrief} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "SwimSessionBrief{"
        + "mainsetSession=" + mainsetSession
        + ", warmupSession=" + warmupSession
        + ", cooldownSession=" + cooldownSession
        + "}";
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "SwimSessionBriefIF", generator = "Immutables")
  @Deprecated
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements SwimSessionBriefIF {
    @Nullable SessionItem mainsetSession;
    @Nullable SessionItem warmupSession;
    @Nullable SessionItem cooldownSession;
    @JsonProperty
    public void setMainsetSession(SessionItem mainsetSession) {
      this.mainsetSession = mainsetSession;
    }
    @JsonProperty
    public void setWarmupSession(SessionItem warmupSession) {
      this.warmupSession = warmupSession;
    }
    @JsonProperty
    public void setCooldownSession(SessionItem cooldownSession) {
      this.cooldownSession = cooldownSession;
    }
    @Override
    public SessionItem getMainsetSession() { throw new UnsupportedOperationException(); }
    @Override
    public SessionItem getWarmupSession() { throw new UnsupportedOperationException(); }
    @Override
    public SessionItem getCooldownSession() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static SwimSessionBrief fromJson(Json json) {
    SwimSessionBrief.Builder builder = SwimSessionBrief.builder();
    if (json.mainsetSession != null) {
      builder.setMainsetSession(json.mainsetSession);
    }
    if (json.warmupSession != null) {
      builder.setWarmupSession(json.warmupSession);
    }
    if (json.cooldownSession != null) {
      builder.setCooldownSession(json.cooldownSession);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link SwimSessionBriefIF} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable SwimSessionBrief instance
   */
  public static SwimSessionBrief copyOf(SwimSessionBriefIF instance) {
    if (instance instanceof SwimSessionBrief) {
      return (SwimSessionBrief) instance;
    }
    return SwimSessionBrief.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link SwimSessionBrief SwimSessionBrief}.
   * <pre>
   * SwimSessionBrief.builder()
   *    .setMainsetSession(com.garymace.session.generator.base.models.session.SessionItem) // required {@link SwimSessionBriefIF#getMainsetSession() mainsetSession}
   *    .setWarmupSession(com.garymace.session.generator.base.models.session.SessionItem) // required {@link SwimSessionBriefIF#getWarmupSession() warmupSession}
   *    .setCooldownSession(com.garymace.session.generator.base.models.session.SessionItem) // required {@link SwimSessionBriefIF#getCooldownSession() cooldownSession}
   *    .build();
   * </pre>
   * @return A new SwimSessionBrief builder
   */
  public static SwimSessionBrief.Builder builder() {
    return new SwimSessionBrief.Builder();
  }

  /**
   * Builds instances of type {@link SwimSessionBrief SwimSessionBrief}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SwimSessionBriefIF", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_MAINSET_SESSION = 0x1L;
    private static final long INIT_BIT_WARMUP_SESSION = 0x2L;
    private static final long INIT_BIT_COOLDOWN_SESSION = 0x4L;
    private long initBits = 0x7L;

    private @Nullable SessionItem mainsetSession;
    private @Nullable SessionItem warmupSession;
    private @Nullable SessionItem cooldownSession;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.garymace.session.generator.base.models.session.brief.SessionBriefWarmup} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(SessionBriefWarmup instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.garymace.session.generator.base.models.session.SessionBriefBase} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(SessionBriefBase instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.garymace.session.generator.base.models.session.brief.SessionBriefCooldown} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(SessionBriefCooldown instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBriefIF} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(SwimSessionBriefIF instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    private void from(Object object) {
      if (object instanceof SessionBriefWarmup) {
        SessionBriefWarmup instance = (SessionBriefWarmup) object;
        setWarmupSession(instance.getWarmupSession());
      }
      if (object instanceof SessionBriefBase) {
        SessionBriefBase instance = (SessionBriefBase) object;
        setMainsetSession(instance.getMainsetSession());
      }
      if (object instanceof SessionBriefCooldown) {
        SessionBriefCooldown instance = (SessionBriefCooldown) object;
        setCooldownSession(instance.getCooldownSession());
      }
    }

    /**
     * Initializes the value for the {@link SwimSessionBriefIF#getMainsetSession() mainsetSession} attribute.
     * @param mainsetSession The value for mainsetSession 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder setMainsetSession(SessionItem mainsetSession) {
      this.mainsetSession = Objects.requireNonNull(mainsetSession, "mainsetSession");
      initBits &= ~INIT_BIT_MAINSET_SESSION;
      return this;
    }

    /**
     * Initializes the value for the {@link SwimSessionBriefIF#getWarmupSession() warmupSession} attribute.
     * @param warmupSession The value for warmupSession 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder setWarmupSession(SessionItem warmupSession) {
      this.warmupSession = Objects.requireNonNull(warmupSession, "warmupSession");
      initBits &= ~INIT_BIT_WARMUP_SESSION;
      return this;
    }

    /**
     * Initializes the value for the {@link SwimSessionBriefIF#getCooldownSession() cooldownSession} attribute.
     * @param cooldownSession The value for cooldownSession 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder setCooldownSession(SessionItem cooldownSession) {
      this.cooldownSession = Objects.requireNonNull(cooldownSession, "cooldownSession");
      initBits &= ~INIT_BIT_COOLDOWN_SESSION;
      return this;
    }

    /**
     * Builds a new {@link SwimSessionBrief SwimSessionBrief}.
     * @return An immutable instance of SwimSessionBrief
     * @throws com.hubspot.immutables.validation.InvalidImmutableStateException if any required attributes are missing
     */
    public SwimSessionBrief build() {
      checkRequiredAttributes();
      return new SwimSessionBrief(mainsetSession, warmupSession, cooldownSession);
    }

    private boolean mainsetSessionIsSet() {
      return (initBits & INIT_BIT_MAINSET_SESSION) == 0;
    }

    private boolean warmupSessionIsSet() {
      return (initBits & INIT_BIT_WARMUP_SESSION) == 0;
    }

    private boolean cooldownSessionIsSet() {
      return (initBits & INIT_BIT_COOLDOWN_SESSION) == 0;
    }

    private void checkRequiredAttributes() {
      if (initBits != 0) {
        throw new InvalidImmutableStateException(formatRequiredAttributesMessage());
      }
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if (!mainsetSessionIsSet()) attributes.add("mainsetSession");
      if (!warmupSessionIsSet()) attributes.add("warmupSession");
      if (!cooldownSessionIsSet()) attributes.add("cooldownSession");
      return "Cannot build SwimSessionBrief, some of required attributes are not set " + attributes;
    }
  }
}
