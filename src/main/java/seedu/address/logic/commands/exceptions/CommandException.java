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
     * Represents an error when a duplicate company is added. Duplicate company refers to
     * companies with the same name field and the same role field.
     */
    public static class DuplicateCompanyException extends CommandException {
        private String expectedMessage = "";

        /**
         * Constructs a new {@code DuplicateCompanyException} with the specified detail {@code message}.
         * This is to be used for user interaction for the EDIT command.
         */
        public DuplicateCompanyException(Company company, int index) {
            super("Duplicate Entry Detected"
                    + "\n" + "You already have another entry "
                    + "for the company " + company.getName() + " with the role "
                    + company.getRole() + " at index " + (index + 1) + "."
            );
            expectedMessage = "Duplicate Entry Detected"
                    + "\n" + "You already have another entry "
                    + "for the company " + company.getName() + " with the role "
                    + company.getRole() + " at index " + (index + 1) + ".";
        }

        /**
         * Constructs a new {@code DuplicateCompanyException} with the specified detail {@code message}.
         * This is to be used for user interaction for the ADD Command.
         */
        public DuplicateCompanyException(Company company, int index, String allChangedFields) {
            super(allChangedFields.isEmpty()
                    ? ("Duplicate Entry Detected"
                    + "\n" + "You already have another entry with the exact same details "
                    + "for the company " + company.getName() + " with the role "
                    + company.getRole() + " at index " + (index + 1) + ".") : ("Duplicate Entry Detected"
                    + "\n" + "You already have another entry "
                    + "for the company " + company.getName() + " with the role "
                    + company.getRole() + " at index " + (index + 1) + "."
                    + "\n" + "Perhaps you meant to use the edit command instead?"
                    + " Type: " + "edit " + (index + 1) + " " + allChangedFields)
            );
            expectedMessage = allChangedFields.isEmpty()
                    ? ("Duplicate Entry Detected"
                            + "\n" + "You already have another entry with the exact same details "
                            + "for the company " + company.getName() + " with the role "
                            + company.getRole() + " at index " + (index + 1) + ".") : ("Duplicate Entry Detected"
                    + "\n" + "You already have another entry "
                    + "for the company " + company.getName() + " with the role "
                    + company.getRole() + " at index " + (index + 1) + "."
                    + "\n" + "Perhaps you meant to use the edit command instead?"
                    + " Type: " + "edit " + (index + 1) + " " + allChangedFields);
        }

        /**
         * Returns the expected message for the exception.
         * @return
         */
        public String getExpectedMessage() {
            return expectedMessage;
        }
    }
}
