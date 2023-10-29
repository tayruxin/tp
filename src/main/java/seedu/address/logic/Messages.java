package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.company.Company;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "Oops! You have entered an invalid command format. "
            + "Please follow the command format below and try again! \n%1$s";

    public static final String MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX =
        "Oops! You have entered an index that is greater than the number of companies that you have. "
                + "Please try again!";

    public static final String MESSAGE_COMPANIES_LISTED_OVERVIEW = "%1$d companies listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    public static final String MESSAGE_NON_INTEGER_INDEX = "Oops! The index you have given is not an integer."
            + " Please try again with an integer!";

    public static final String MESSAGE_NON_POSITIVE_INTEGER_INDEX = "Oops! The index you have given is not a "
            + "positive integer. Please try again with a positive integer!";

    public static final String MESSAGE_EMPTY_INDEX = "Oops! The index you have given is empty. "
            + "Remember to key in an index!";

    public static final String MESSAGE_EMPTY_PREFIX = "Oops! You have not entered any information. "
            + "Remember to follow the command format below! \n%1$s";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code company} for display to the user.
     */
    public static String format(Company company) {
        final StringBuilder builder = new StringBuilder();
        builder.append(company.getName())
                .append("; Role: ")
                .append(company.getRole())
                .append("; Status: ")
                .append(company.getStatus())
                .append("; Deadline: ")
                .append(company.getDeadline())
                .append("; Recruiter Name: ")
                .append(company.getRecruiterName())
                .append("; Phone: ")
                .append(company.getPhone())
                .append("; Email: ")
                .append(company.getEmail())
                .append("; Priority: ")
                .append(company.getPriority());
        return builder.toString();
    }

    /**
     * Formats the {@code company} to only display the company's name to the user
     * with the left UI panel for viewing the company details.
     */
    public static String getCompanyName(Company company) {
        return company.getName().toString();
    }

    public static String getCompanyInfo(Company company) {
        return company.getName().toString() + " ( " + "Role: " + company.getRole().toString()
                + ", " + "Deadline: " + company.getDeadline().toString() + ")";

    }
}
