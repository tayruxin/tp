package seedu.address.model.company;

import static java.util.Objects.requireNonNull;

/**
 * Represents user's remark for a Company in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Oops! Remark should not be empty. Please try again!";

    /*
     * The remark must not consist of whitespace only,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s+$).+";

    public final String value;

    private boolean isDeleted = false;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        this.value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Set the isDeleted boolean to true.
     */
    public void deleteRemark() {
        this.isDeleted = true;
    }

    public boolean getIsDeleted() {
        return this.isDeleted;
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

        Remark otherremark = (Remark) other;
        return value.equals(otherremark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
