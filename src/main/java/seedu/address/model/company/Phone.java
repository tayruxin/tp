package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {
    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Phone number should not be blank! Please try again with a valid phone number.";
    public static final String MESSAGE_CONSTRAINTS_VALID_REGEX =
            "Oops! Phone number should only contain numbers, and it should be at "
                    + "least 3 digits and at most 20 characters long! \n"
                    + "Please try again with a valid phone number. ";

    // The regex allows for 3 to 20 digits, but can be up to 20 characters considering formatting characters.
    public static final String VALIDATION_REGEX = "^[\\d\\s\\-\\+\\(\\)]{3,20}$";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(!phone.isBlank(), MESSAGE_CONSTRAINTS_NON_EMPTY);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS_VALID_REGEX);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Standardizes a given phone number by stripping away all formatting characters
     * such as spaces, dashes, parentheses, and the plus sign. This method allows for
     * different formats of the same phone number to be considered equal.
     *
     * @param number The phone number to be standardized.
     * @return The standardized phone number without formatting characters.
     */
    private static String standardizeNumber(String number) {
        return number.replaceAll("[\\s\\-\\+\\(\\)]", "");
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return standardizeNumber(value).equals(standardizeNumber(otherPhone.value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
