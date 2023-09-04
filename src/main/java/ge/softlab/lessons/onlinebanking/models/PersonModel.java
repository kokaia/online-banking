package ge.softlab.lessons.onlinebanking.models;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonModel(
        Integer id,
        String personalNumber,
        String firstName,
        String lastName,
        LocalDate birthDate
) {

}
