package ge.softlab.lessons.onlinebanking.models;

import java.time.LocalDate;

public record PersonModel(
        Integer id,
        String personalNumber,
        String firstName,
        String lastName,
        LocalDate birthDate
) {

}
