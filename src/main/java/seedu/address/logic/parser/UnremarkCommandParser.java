package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Remark;


/**
 * Parses input arguments and creates a new {@code RemarkCommand} object.
 */
public class UnremarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            Remark remark = new Remark("");
            return new RemarkCommand(index, remark);

        } catch (ParseException pe) {
            throw new ParseException(String.format(pe.getMessage()), pe);
        }
    }
}
