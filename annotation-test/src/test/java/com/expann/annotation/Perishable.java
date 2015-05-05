package com.expann.annotation;

import java.time.Month;
import java.time.Year;

/**
 * Represents a perishable type.
 *
 * @author Mohamed El-Geish
 */
@Expiration(year = 2015, month = Month.APRIL, dayOfMonth = 22)
class Perishable {
  @Expiration(year = Year.MIN_VALUE, month = Month.JANUARY, dayOfMonth = 1, owners = "owner")
  private Object expired;

  @Expiration(year = Year.MAX_VALUE, month = Month.DECEMBER, dayOfMonth = 31, owners = {"foo", "bar"})
  private Object good;

  @Expiration(year = 2015, month = Month.FEBRUARY, dayOfMonth = 28, owners = {"red", "green", "blue"})
  private Perishable() {
  }

  @Expiration(year = 2000, month = Month.FEBRUARY, dayOfMonth = 29, owners = {})
  private void expire() {
  }

  private Object getExpired() {
    @Expiration(year = Year.MIN_VALUE, month = Month.JANUARY, dayOfMonth = 1)
    final Object local = expired;

    return local;
  }

  private void setGood(@Expiration(year = 1, month = Month.JANUARY, dayOfMonth = 1) final Object parameter) {
  }
}
