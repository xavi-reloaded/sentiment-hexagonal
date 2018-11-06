/**
 * 
 */
package io.xavihidalgo.sentiment.annotation;

import java.lang.annotation.*;

/**
 * Marks this package as the root of the application layer.
 * 
 * This layer (usually very thin) delegates to the domain layer to realize each
 * applicative use-case. It typically decides when to start/stop transactions
 * around a full use-case.
 * 
 * @author Cyrille.Martraire
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.PACKAGE)
public @interface ApplicationLayer {

    String[] domainLayer() default {};
}
