package it.bootcamp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static it.bootcamp.Constants.ADMIN_ROLE;
import static it.bootcamp.Constants.CUSTOMER_ROLE;
import static it.bootcamp.Constants.SALE_ROLE;
import static it.bootcamp.Constants.SECURE_API_ROLE;

public class RolesValidator implements ConstraintValidator<RolesConstraint, String> {
    @Override
    public boolean isValid(String name,
                           ConstraintValidatorContext cxt) {
        return name.equals(ADMIN_ROLE) || name.equals(SALE_ROLE)
                || name.equals(CUSTOMER_ROLE) || name.equals(SECURE_API_ROLE);
    }
}
