# ExpAnn (Expiration Annotation)
**Annotate code with an expiration date, and get a warning when it expires**

Code is not meant to live forever, unless it goes interstellar like Voyager 1, because there's always a better alternative that comes along and deprecates old code. Code, just like most products, should have an expiration date; Rewrites of an expired module are inevitable, but the timeline of such process is rarely declared in advance; Code expiration date should be explicitly declared when adding code for the following purposes (to name a few):
* temporary workarounds (e.g., until the next release of a library provides the missing feature)
* creating a feature that will be A/B-tested; it may not stick around
* building a minimum viable product
* beta APIs

Shipping perishable code without an expiration detection mechanism is a recipe for software development bloat; expired code that doesn't get exercised in the real world is a nest for undetected bugs; maintaining expired code is a tax that no one should pay.

One approach to declare expiration date of a program element is a TODO to remove it later, but it looks informal and it's hard to check at build-time; Annotations, on the other hand, provide a formal mechanism to achieve better results. ExpAnn (Expiration Annotation) allows you to annotate code with an expiration date, and produces a warning when it expires; it provides Java developers an annotation that:
* provides a declarable expiration date that's checked at build-time, and triggers a mandatory warning when the annotated code expires
* declares owners who are responsible for deprecating the expired code
* could be extended and/or processed differently (e.g., send an email to owners when the annotated code expires)
* has a precise target: CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, and TYPE
* could be reflectively accessed at runtime
* shows up in javadoc

Here's an example of how to use ExpAnn:

```java
@Expiration(year = 2015, month = Month.APRIL, dayOfMonth = 22, owners = "elgeish")
class Perishable {
...
```

The following is the respective warning:
```bash
Warning:(12, 1) java: Perishable expired: [Expiration date: 2015-04-22, owners: [elgeish]]
```

The Java compiler should auto-discover the ExpAnn annotation processor, or you can configure it using its fully-qualified name: com.expann.annotation.processing.ExpirationProcessor
