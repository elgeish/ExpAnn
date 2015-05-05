package com.expann.annotation;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Year;

/**
 * Represents a unit-test class for {@link Expiration}.
 *
 * @author Mohamed El-Geish
 */
public class TestExpiration {
  @Test
  public void testWhenConstructorIsAnnotated() throws NoSuchMethodException {
    verify(Perishable.class.getDeclaredConstructor(), LocalDate.of(2015, 2, 28), false, "red", "green", "blue");
  }

  @Test
  public void testWhenFieldIsAnnotated() throws NoSuchFieldException {
    verify(Perishable.class.getDeclaredField("expired"), LocalDate.of(Year.MIN_VALUE, 1, 1), false, "owner");
    verify(Perishable.class.getDeclaredField("good"), LocalDate.of(Year.MAX_VALUE, 12, 31), false, "foo", "bar");
  }

  @Test
  public void testWhenMethodIsAnnotated() throws NoSuchMethodException {
    verify(Perishable.class.getDeclaredMethod("expire"), LocalDate.of(2000, 2, 29), false);
  }

  @Test
  public void testWhenPackageIsAnnotated() {
    verify(Perishable.class.getPackage(), LocalDate.of(Year.MIN_VALUE, 1, 1), false);
  }

  @Test
  public void testWhenParameterIsAnnotated() throws NoSuchMethodException {
    final Parameter parameter = Perishable.class.getDeclaredMethod("setGood", Object.class).getParameters()[0];

    verify(parameter, LocalDate.of(1, 1, 1), false);
  }

  @Test
  public void testWhenTypeIsAnnotated() {
    verify(Perishable.class, LocalDate.of(2015, 4, 22), true);
  }

  private static void verify(
      final AnnotatedElement element,
      final LocalDate expectedDate,
      final boolean expectedDeprecateOnExpiry,
      final String... expectedOwners) {
    final Expiration expiration = element.getAnnotation(Expiration.class);

    Assert.assertEquals(expectedDate, LocalDate.of(expiration.year(), expiration.month(), expiration.dayOfMonth()));
    Assert.assertArrayEquals(expectedOwners, expiration.owners());
  }
}
