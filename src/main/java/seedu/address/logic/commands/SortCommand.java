package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts and lists all companies in the address book to the user by their deadlines.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Sorted all companies by their deadlines";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortCompaniesByDeadline();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
