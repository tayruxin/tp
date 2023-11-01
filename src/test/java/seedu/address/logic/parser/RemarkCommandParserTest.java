package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.company.Remark;

public class RemarkCommandParserTest {
    private RemarkCommandParser parser = new RemarkCommandParser();
    private final String nonEmptyRemark = "Some remark.";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + nonEmptyRemark;
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_COMPANY, new Remark(nonEmptyRemark));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        Index targetIndex = INDEX_FIRST_COMPANY;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);

        // no remark
        expectedMessage = Remark.MESSAGE_CONSTRAINTS;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        assertParseFailure(parser, userInput, expectedMessage);

        // no prefix and remark
        expectedMessage = RemarkCommand.MESSAGE_NO_REMARK;
        userInput = targetIndex.getOneBased() + "";
        assertParseFailure(parser, userInput, expectedMessage);

    }
}
