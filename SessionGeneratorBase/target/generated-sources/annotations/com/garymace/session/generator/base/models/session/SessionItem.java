package com.garymace.session.generator.base.models.session;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link SessionItemIF}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code SessionItem.builder()}.
 */
@Generated(from = "SessionItemIF", generator = "Immutables")
@SuppressWarnings({"all"})
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
public final class SessionItem implements SessionItemIF {

  private SessionItem(SessionItem.Builder builder) {
  }

  /**
   * This instance is equal to all instances of {@code SessionItem} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof SessionItem
        && equalTo((SessionItem) another);
  }

  @SuppressWarnings("MethodCanBeStatic")
  private boolean equalTo(SessionItem another) {
    return true;
  }

  /**
   * Returns a constant hash code value.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    return 0;
  }


  /**
   * Prints the immutable value {@code SessionItem}.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "SessionItem{}";
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "SessionItemIF", generator = "Immutables")
  @Deprecated
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements SessionItemIF {
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static SessionItem fromJson(Json json) {
    SessionItem.Builder builder = SessionItem.builder();
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link SessionItemIF} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable SessionItem instance
   */
  public static SessionItem copyOf(SessionItemIF instance) {
    if (instance instanceof SessionItem) {
      return (SessionItem) instance;
    }
    return SessionItem.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link SessionItem SessionItem}.
   * <pre>
   * SessionItem.builder()
   *    .build();
   * </pre>
   * @return A new SessionItem builder
   */
  public static SessionItem.Builder builder() {
    return new SessionItem.Builder();
  }

  /**
   * Builds instances of type {@link SessionItem SessionItem}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SessionItemIF", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code SessionItemIF} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(SessionItemIF instance) {
      Objects.requireNonNull(instance, "instance");
      return this;
    }

    /**
     * Builds a new {@link SessionItem SessionItem}.
     * @return An immutable instance of SessionItem
     * @throws com.hubspot.immutables.validation.InvalidImmutableStateException if any required attributes are missing
     */
    public SessionItem build() {
      return new SessionItem(this);
    }
  }
}
