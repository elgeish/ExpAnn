package com.expann.annotation;

import java.lang.annotation.*;
import java.time.Month;

import static java.lang.annotation.ElementType.*;

/**
 * An annotation for code expiration.
 * A program element annotated with {@code @Expiration} is one that is meant to perish;
 * typically because it is experimental, or because a better alternative will exist in the foreseeable future.
 * Compiler plugins warn when an expired program element is used (or overridden in non-expired code).
 *
 * @author Mohamed El-Geish
 * @see Deprecated
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Expiration {
  /**
   * The expiration year of the annotated program element.
   *
   * @return the expiration year of the annotated program element
   */
  int year();

  /**
   * The expiration month of the annotated program element.
   *
   * @return the expiration month of the annotated program element
   */
  Month month();

  /**
   * The expiration day-of-month of the annotated program element.
   *
   * @return the expiration day-of-month of the annotated program element
   */
  int dayOfMonth();

  /**
   * The set of owners who are responsible for deprecating the annotated program element when it expires.
   * There are no checks associated with this value; it is used for documenting accountability, and reporting.
   *
   * @return a set of owners who are responsible for deprecating the annotated program element when it expires
   */
  String[] owners() default {};
}
