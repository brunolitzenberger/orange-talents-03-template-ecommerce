package br.com.zupacademy.bruno.mercadolivre.controller.utils.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueValueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueValue {

	String message() default  "";
	
	Class<?>[] groups () default {};
	
	Class<? extends Payload>[] payload() default {};	

	String fieldName();
	
	Class<?> domainClass();
	
	
}
