package br.com.jamiz.validation;


import br.com.jamiz.validation.constraintvalidation.NotEmptyListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //verificada em tempo de execucao
@Target(ElementType.FIELD) //aonde podemos colocar a annotation, no qual é emcima de um campo
@Constraint(validatedBy = NotEmptyListValidator.class) //annotation de validação, diz para validar o campo
public @interface NotEmptyList {
    String message() default "a lista pode ser vazia";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
