package ge.softlab.lessons.onlinebanking.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionAddModel(

    @NotNull
    @Schema(description = "ანგარიშის ID რომელიც ახორცილებს გადარიცხვას", defaultValue = "0")
    Integer creditAccountId,

    @NotNull
    @Schema(description = "ანგარიშის ID, რომელზეც ჩაირიცხება", defaultValue = "0")
    Integer debitAccountId,

    @NotNull(message = "null is not allowed")
    @Min(value = 1, message = "min value is 1")
    @Max(10_000)
    @Schema(description = "გადასარიცხი თანხა", defaultValue = "1")
    Double amount,

    @NotBlank
    @Schema(description = "კომენტარი", defaultValue = "პირადი გადარიცხვა ბანკში")
    String comment
) {
}
