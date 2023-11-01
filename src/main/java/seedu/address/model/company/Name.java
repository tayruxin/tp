package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Company's name should not be blank. Please try again with a valid company name.";

    public static final String MESSAGE_CONSTRAINTS_INVALID_REGEX =
            "Oops! Company's name should only contain alphanumeric characters and spaces! Please try again with"
                    + " a valid company name.";

    /*
     * This regular expression allows names to start with an alphanumeric character
     * (a letter or digit) and can be followed by zero or more alphanumeric characters
     * or spaces. Names must start with an alphanumeric character.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(!name.isBlank(), MESSAGE_CONSTRAINTS_NON_EMPTY);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS_INVALID_REGEX);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
