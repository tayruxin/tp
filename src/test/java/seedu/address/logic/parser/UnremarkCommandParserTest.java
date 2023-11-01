package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.company.Remark;

public class UnremarkCommandParserTest {
    private UnremarkCommandParser parser = new UnremarkCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + " ";
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_COMPANY, new Remark("No remarks"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_REMOVE_USAGE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.REMOVE_COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.REMOVE_COMMAND_WORD + " ", expectedMessage);


    }
}
