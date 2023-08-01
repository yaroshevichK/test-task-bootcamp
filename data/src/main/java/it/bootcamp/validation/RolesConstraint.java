package it.bootcamp.validation;

import it.bootcamp.model.Role;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static it.bootcamp.Constants.MESSAGE_ROLES;

@Documented
@Constraint(validatedBy = RolesValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesConstraint {
    String message() default MESSAGE_ROLES;

    Class<?>[] groups() default {};

    Class<? extends Role>[] payload() default {};
}
