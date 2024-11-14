package com.revature.Project1.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //this annotation can only be applied to methods
@Retention(RetentionPolicy.RUNTIME) //This annotation will be available at runtime
public @interface ManagerOnly {
}
