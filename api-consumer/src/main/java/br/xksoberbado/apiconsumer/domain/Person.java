package br.xksoberbado.apiconsumer.domain;

import java.time.LocalDate;

public record Person(String name,
                     LocalDate birthdate,
                     Gender gender) {

    public enum Gender {
        MALE, FEMALE
    }
}
