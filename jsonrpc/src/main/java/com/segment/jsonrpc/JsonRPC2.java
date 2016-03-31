package com.segment.jsonrpc;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Make a JSON-RPC 2.0 request.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface JsonRPC2 {
    /**
     * The name of RPC value being invoked by this call.
     */
    String value() default "";

    /**
     * Define if the RPC query use named parameters.
     */
    boolean namedParameters() default true;

    /**
     * Define if the RPC query is a notification (No id)
     */
    boolean notification() default false;
}
