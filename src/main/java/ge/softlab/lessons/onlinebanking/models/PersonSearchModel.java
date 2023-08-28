package ge.softlab.lessons.onlinebanking.models;

public record PersonSearchModel(
        String personalNumber,
        String firstName,
        String lastName,
        Integer ageFrom,
        Integer ageTo,
        String iban
) {
}
