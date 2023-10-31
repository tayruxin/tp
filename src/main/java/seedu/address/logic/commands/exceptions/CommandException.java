package seedu.address.logic.commands.exceptions;

import seedu.address.logic.commands.Command;
import seedu.address.model.company.Company;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Represents an error which occurs during execution of a {@link Command}.
     */
    public static class DuplicateCompanyException extends CommandException {

        /**
         * Constructs a new {@code DuplicateCompanyException} with the specified detail {@code message}.
         * @param company
         */
        public DuplicateCompanyException(Company company) {
            super("Duplicate Entry Detected"
                    + "\n" + "You already have another entry "
                    + "for the company " + company.getName() + " with the role "
                    + company.getRole() + "."
                    + "\n" + "Here are the details of the existing entry: "
                    + "\n" + company.styledOutput()
            );
        }
    }
}
