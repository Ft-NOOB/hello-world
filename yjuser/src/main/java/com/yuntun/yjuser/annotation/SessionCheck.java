package com.yuntun.yjuser.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.servlet.http.HttpSession;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 验证HttpSession中的值
 * value : session key
 * @author GWELL
 *
 */
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { SessionCheck.Validator.class })
public @interface SessionCheck {
	String message() default "验证码错误";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default {};
	
	String value();
	
	 public static class Validator implements ConstraintValidator<SessionCheck, String> {
		@Autowired
		private HttpSession httpSession;
		private String sessionValue;

		@Override
		public void initialize(SessionCheck constraintAnnotation) {
			sessionValue = (String) httpSession.getAttribute(constraintAnnotation.value());
		}

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if(value.equals(sessionValue)) {
				return true;
			}
			return false;
		}
	}
}