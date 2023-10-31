package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Role should not be blank! Please try again with a valid role.";

    public static final String MESSAGE_CONSTRAINTS_INVALID_REGEX =
            "Oops! Role should only contain alphanumeric characters and spaces! Please try again with"
                    + " a valid role.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String jobRole;

    /**
     * Constructs a {@code ApplyingRole}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(!role.isBlank(), MESSAGE_CONSTRAINTS_NON_EMPTY);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS_INVALID_REGEX);
        jobRole = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return jobRole;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherName = (Role) other;
        return jobRole.equals(otherName.jobRole);
    }

    @Override
    public int hashCode() {
        return jobRole.hashCode();
    }
}
