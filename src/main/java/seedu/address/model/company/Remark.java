package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents user's remark for a Company in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remark should not be blank";

    /*
     * The note must not consist of whitespace only,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s+$).+";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param remark A valid name.
     */
    public Remark(String remark) {
        requireNonNull(remark);
//        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        this.value = remark;
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherNote = (Remark) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
