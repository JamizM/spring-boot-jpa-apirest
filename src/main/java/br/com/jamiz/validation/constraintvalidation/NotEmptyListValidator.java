package br.com.jamiz.validation.constraintvalidation;

import br.com.jamiz.validation.NotEmptyList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {
    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty(); //lista na nao estando vazia e nem null
        //vai dazer se o objeto é valido
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    //dentro do operador diamante, colocamos qual é a anotation que será
    // validada depois colocamos é o tipo de dado que será validade
}
