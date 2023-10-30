package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.SortOrder;
import seedu.address.model.Model;

/**
 * Sorts and lists all companies in the address book to the user by their deadlines.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS_ASCENDING = "Sorted all companies by their deadlines in ascending order";
    public static final String MESSAGE_SUCCESS_DESCENDING = "Sorted all companies by their deadlines in descending "
            + "order";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all companies by their deadlines in ascending "
            + "or descending order.\n"
            + "Parameters: ascending or descending (case-insensitive)\n"
            + "Example: " + COMMAND_WORD + " ascending";

    private final SortOrder sortOrder;

    /**
     * Creates a SortCommand to sort the companies by their deadlines.
     *
     * @param sortOrder the order in which the companies are sorted.
     */
    public SortCommand(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortCompaniesByDeadline(sortOrder);
        return new CommandResult(sortOrder == SortOrder.ASCENDING ? MESSAGE_SUCCESS_ASCENDING
                : MESSAGE_SUCCESS_DESCENDING);
    }
}
