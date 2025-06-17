package br.xksoberbado.apiconsumer.domain;

import java.time.LocalDate;

public record Person(String name,
                     LocalDate birthdate,
                     Gender gender) {

    enum Gender {
        MALE, FEMALE
    }
}
