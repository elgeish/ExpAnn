package com.expann.annotation.processing;

import com.expann.annotation.Expiration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * A proxy for the {@link Expiration} annotation.
 *
 * @author Mohamed El-Geish
 */
final class ExpirationProxy {
  private final Expiration expiration;
  private final LocalDate expirationDate;

  private ExpirationProxy(final Expiration expiration) {
    this.expiration = Objects.requireNonNull(expiration, "expiration cannot be null");
    this.expirationDate = LocalDate.of(expiration.year(), expiration.month(), expiration.dayOfMonth());
  }

  /**
   * Creates a proxy for the specified {@code expiration} object.
   *
   * @param expiration the object to wrap
   * @return an instance of {@link ExpirationProxy} that wraps the specified {@code expiration} object
   */
  static ExpirationProxy wrap(final Expiration expiration) {
    return new ExpirationProxy(expiration);
  }

  /**
   * Indicates whether or not the annotated program element is expired.
   *
   * @return {@code true} iff the annotated program element is expired; otherwise, {@code false}
   * @apiNote expiry date is evaluated using {@link LocalDate}
   */
  boolean isExpired() {
    return LocalDate.now().isAfter(expirationDate);
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return String.format(
        "[Expiration date: %s, owners: %s]",
        expirationDate.toString(),
        Arrays.toString(expiration.owners()));
  }
}
