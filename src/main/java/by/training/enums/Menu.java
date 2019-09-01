package by.training.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Menu {

    SELECT ("Select transaction"),
    CHECK_BALANCE("1 - Verify the amount"),
    GET_CASH("2 - Cash/withdraw"),
    SET_CASH("3 - Transfer"),
    RETURN_CARD("4 - Return card"),
    CONTINUE("Continue?"),
    YES("1 - Yes button"),
    NO("2 - No button");

    private String type;

}