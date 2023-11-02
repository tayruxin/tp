package seedu.address.model.company;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Company's application status  in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidApplicationStatus(String)}
 */
public class ApplicationStatus {
    public static final String PENDING_APPLICATION =
            "^(PA|PENDAPP|PENDINGAPP|PENDINGAPPLICATION|PENDING APPLICATION|P\\sA|PENDING\\sA)$";

    public static final String PENDING_INTERVIEW =
            "^(PI|PENDINT|PENDINGINT|PENDINGINTERVIEW|PENDING INTERVIEW|P\\sI|PENDING\\sI)$";

    public static final String PENDING_OUTCOME =
            "^(PO|PENDOUT|PENDINGOUT|PENDINGOUTCOME|PENDING OUTCOME|P\\sO|PENDING\\sO)$";

    public static final String ACCEPTED = "^(A|ACC|ACCEPT|ACPT|ACCEPTED)$";

    public static final String REJECTED = "^(R|REJ|REJECT|REJECTED)$";

    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Application status should not be blank! Please try again with a valid application status.";
    public static final String MESSAGE_CONSTRAINTS_VALID_STATUS =
            "Oops! You have entered an invalid application status! \n"
                    + "Valid statuses: PA (Pending Application), PI (Pending Interview), PO (Pending Outcome), "
                    + "A (Accepted), R (Rejected). \n"
                    + "Please try again with a valid application status.";

    /**
     * The application status of a company.
     */
    public enum ApplicationStatusEnum {
        PA("PA", "PENDING APPLICATION"),
        PI("PI", "PENDING INTERVIEW"),
        PO("PO", "PENDING OUTCOME"),
        A("A", "ACCEPTED"),
        R("R", "REJECTED");

        private final String code;
        private final String description;

        ApplicationStatusEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public final ApplicationStatusEnum status;


    /**
     * Constructs an {@code ApplicationStatus} based on the given status string.
     * The constructor supports a range of status inputs for flexibility, allowing
     * for both short codes and various longer forms. This includes allowing
     * infinite spaces between letters such as 'P' and 'O'. Refer to the class
     * documentation for a detailed list of supported status inputs.
     *
     * @param status A string representation of the application status.
     * @throws IllegalArgumentException If the provided status string is invalid.
     */
    public ApplicationStatus(String status) throws IllegalArgumentException {
        requireNonNull(status);
        // Convert to uppercase and replace multiple spaces with a single space
        status = status.toUpperCase().replaceAll("\\s+", " ").trim();

        if (status.matches(PENDING_APPLICATION)) {
            this.status = ApplicationStatusEnum.PA;
        } else if (status.matches(PENDING_INTERVIEW)) {
            this.status = ApplicationStatusEnum.PI;
        } else if (status.matches(PENDING_OUTCOME)) {
            this.status = ApplicationStatusEnum.PO;
        } else if (status.matches(ACCEPTED)) {
            this.status = ApplicationStatusEnum.A;
        } else if (status.matches(REJECTED)) {
            this.status = ApplicationStatusEnum.R;
        } else {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_VALID_STATUS);
        }
    }

    /**
     * Returns true if a given string is a valid application status.
     */
    public static boolean isValidApplicationStatus(String test) {
        test = test.toUpperCase().replaceAll("\\s+", " ").trim();
        return test.matches(PENDING_APPLICATION + "|" + PENDING_INTERVIEW + "|"
                + PENDING_OUTCOME + "|" + ACCEPTED + "|" + REJECTED);
    }


    @Override
    public String toString() {
        return status.getCode();
    }

    public String getDescription() {
        return status.getDescription();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationStatus)) {
            return false;
        }

        ApplicationStatus otherApplicationStatus = (ApplicationStatus) other;
        return status.equals(otherApplicationStatus.status);
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}

