package com.teamlab.fujiiyosuke.todoApp.constraints;

import com.teamlab.fujiiyosuke.todoApp.validation.DeadlineValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * 期限用のバリデーション用アノテーション
 * @author fujii
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {DeadlineValidator.class})
@ReportAsSingleViolation
public @interface Deadline {
    String message() default "{com.teamlab.fujiiyosuke.validator.constraints.Deadline.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        Deadline[] value();
    }
}