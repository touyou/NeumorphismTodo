package com.teamlab.fujiiyosuke.todoApp.validation;

import com.teamlab.fujiiyosuke.todoApp.constraints.Deadline;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DeadlineValidator implements ConstraintValidator<Deadline, Date> {
    @Override
    public void initialize(Deadline constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        Date now = new Date();
        return date.after(now);
    }
}
