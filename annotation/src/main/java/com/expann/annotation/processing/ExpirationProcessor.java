package com.expann.annotation.processing;

import com.expann.annotation.Expiration;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * A processor for the {@link Expiration} annotation.
 *
 * @author Mohamed El-Geish
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.expann.annotation.Expiration")
public final class ExpirationProcessor extends AbstractProcessor {
  /**
   * Processes the {@link Expiration} annotations on program elements originating from the prior round.
   *
   * @param annotations the annotation types requested to be processed
   * @param roundEnv    environment for information about the current and prior round
   * @return whether or not the set of annotation types are claimed by this processor
   * @apiNote always returns {@code false} to allow subsequent processors to process expiration annotations
   * (e.g., a processor that groups expired program elements by owner)
   */
  @Override
  public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
    for (final Element element : roundEnv.getElementsAnnotatedWith(Expiration.class)) {
      final ExpirationProxy expirationProxy = ExpirationProxy.wrap(element.getAnnotation(Expiration.class));

      if (expirationProxy.isExpired()) {
        processingEnv.getMessager().printMessage(
            Diagnostic.Kind.MANDATORY_WARNING,
            element.getSimpleName() + " expired: " + expirationProxy.toString(),
            element);
      }
    }

    return false;
  }
}
