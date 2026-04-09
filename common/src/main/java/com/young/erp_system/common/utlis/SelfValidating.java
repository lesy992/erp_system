package com.young.erp_system.common.utlis;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ConcurrentModificationException;
import java.util.Set;

public class SelfValidating<T> {

    private Validator validator;

    public SelfValidating(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);

        if(!violations.isEmpty()){
            throw new ConcurrentModificationException(String.valueOf(violations));
        }

    }
}
