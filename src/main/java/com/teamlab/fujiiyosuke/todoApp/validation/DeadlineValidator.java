package com.teamlab.fujiiyosuke.todoApp.validation;

import com.teamlab.fujiiyosuke.todoApp.constraints.Deadline;
import org.thymeleaf.util.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.TimeZone;

/**
 * 締め切りのValidatorクラス
 * @author fujii
 */
public class DeadlineValidator implements ConstraintValidator<Deadline, Date> {
    /**
     * Initializer
     * @param constraintAnnotation
     */
    @Override
    public void initialize(Deadline constraintAnnotation) {}

    /**
     * Validator
     * @param date 入力された日付
     * @param context コンテキスト
     * @return 入力された日付が当日以降かどうか
     */
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) {
            // nullの場合はNotNullで判定したいため、こちらのバリデーションは通す
            return true;
        }
        Date now = DateUtils.createToday(TimeZone.getDefault()).getTime();
        return date.after(now) || date.equals(now);
    }
}
