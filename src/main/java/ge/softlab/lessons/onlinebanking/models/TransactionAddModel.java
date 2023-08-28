package ge.softlab.lessons.onlinebanking.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionAddModel(

        @NotNull
        Integer creditAccountId,

        @NotNull
        Integer debitAccountId,

        @NotNull(message = "null is not allowed")
        @Min(value = 1, message = "min value is 1")
        Double amount,

        @NotBlank
        String comment
) {
}
