package seedu.address.model.company;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Company's application status  in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidApplicationStatus(String)}
 */
public class ApplicationStatus {

    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY = "Oops! Application status should not be empty! "
            + "Please try again with a valid application status.";
    public static final String MESSAGE_CONSTRAINTS_VALID_STATUS =
            "Oops! You have entered an invalid application status! \n"
                    + "Valid statuses: PA (Pending Application), PI (Pending Interview), PO (Pending Outcome), "
                    + "A (Accepted), R (Rejected). \n"
                    + "Please try again with a valid applications status.";

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
     * Constructs a {@code ApplicationStatus}.
     * It allows for a flexible comparison, including partial input and
     * partial word string matches.
     *
     * <p>
     * Handled statuses:
     * </p>
     * <ul>
     *   <li><b>PA (Pending Application):</b>
     *       <ul>
     *           <li>PENDAPP</li>
     *           <li>PENDINGAPP</li>
     *           <li>PENDING APPLICATION</li>
     *           <li>P A</li>
     *           <li>PENDING A</li>
     *       </ul>
     *   </li>
     *   <li><b>PI (Pending Interview):</b>
     *       <ul>
     *           <li>PENDINT</li>
     *           <li>PENDINGINT</li>
     *           <li>PENDING INTERVIEW</li>
     *           <li>P I</li>
     *           <li>PENDING I</li>
     *       </ul>
     *   </li>
     *   <li><b>PO (Pending Outcome):</b>
     *       <ul>
     *           <li>PENDOUT</li>
     *           <li>PENDINGOUT</li>
     *           <li>PENDING OUTCOME</li>
     *           <li>P O</li>
     *           <li>PENDING O</li>
     *       </ul>
     *   </li>
     *   <li><b>A (Accepted):</b>
     *       <ul>
     *           <li>ACC</li>
     *           <li>ACCEPT</li>
     *           <li>ACPT</li>
     *           <li>ACCEPTED</li>
     *       </ul>
     *   </li>
     *   <li><b>R (Rejected):</b>
     *       <ul>
     *           <li>REJ</li>
     *           <li>REJECT</li>
     *           <li>REJECTION</li>
     *       </ul>
     *   </li>
     * </ul>
     * <p>
     * And infinite spaces between letters such as P and O.
     * </p>
     *
     * @param status A valid application status.
     * @throws IllegalArgumentException if given status is invalid.
     */
    public ApplicationStatus(String status) throws IllegalArgumentException {
        requireNonNull(status);
        status = status.toUpperCase().replaceAll("\\s+", " ").trim();
        // Convert to uppercase and replace multiple spaces with a single space

        if (status.matches("^(PA|PENAPP|PENDINGAPP|PENDINGAPPLICATION|P\\sA|PENDING\\sA)$")) {
            this.status = ApplicationStatusEnum.PA;
        } else if (status.matches("^(PI|PENDINT|PENDINGINT|PENDINGINTERVIEW|P\\sI|PENDING\\sI)$")) {
            this.status = ApplicationStatusEnum.PI;
        } else if (status.matches("^(PO|PENDOUT|PENDINGOUT|PENDINGOUTCOME|P\\sO|PENDING\\sO)$")) {
            this.status = ApplicationStatusEnum.PO;
        } else if (status.matches("^(A|ACC|ACCEPT|ACPT|ACCEPTED)$")) {
            this.status = ApplicationStatusEnum.A;
        } else if (status.matches("^(R|REJ|REJECT|REJECTION)$")) {
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
        return test.matches("^(PA|PENAPP|PENDINGAPP"
                + "|PENDINGAPPLICATION|P\\sA|PENDING\\sA|PI|PENDINT|"
                + "PENDINGINT|PENDINGINTERVIEW|P\\sI|PENDING\\sI|PO|PENDOUT|"
                + "PENDINGOUT|PENDINGOUTCOME|P\\sO|PENDING\\sO|A|ACC|ACCEPT|ACPT|"
                + "ACCEPTED|R|REJ|REJECT|REJECTION)$");
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

