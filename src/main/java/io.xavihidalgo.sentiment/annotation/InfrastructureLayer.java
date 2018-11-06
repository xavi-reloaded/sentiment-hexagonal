/**
 * 
 */
package io.xavihidalgo.sentiment.annotation;

import java.lang.annotation.*;

/**
 * Marks this package as the root of the infrastructure layer.
 * 
 * This layer typically provides implementations of technical concerns such as
 * persistence, transactions, and other aspects.
 * 
 * @author Cyrille.Martraire
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.PACKAGE)
public @interface InfrastructureLayer {

}
