package com.mike.annotations.JSRvalidations;

import javax.validation.constraints.NotNull;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestingConstraintValidator {

    @NotNull(message = "Name cannot be null")
    @CheckCase(CaseMode.UPPER)
    private final String licensePlate;
    
    @CheckCase(value = CaseMode.LOWER)
    public void processArn(@NonNull String arn) {
        
    }

    public static void main(String[] args) {
        TestingConstraintValidator sut = new TestingConstraintValidator("mike");
        TestingConstraintValidator sut1 = new TestingConstraintValidator("mike");
        TestingConstraintValidator sut2 = new TestingConstraintValidator(null);
        sut.processArn("mike");
        sut.processArn("MIKE");
        System.out.println("---");
    }
}
