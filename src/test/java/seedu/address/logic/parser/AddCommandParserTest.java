package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECRUITER_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECRUITER_NAME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.RECRUITER_NAME_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LOW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECRUITER_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECRUITER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCompanies.GOOGLE;
import static seedu.address.testutil.TypicalCompanies.TIKTOK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Company;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Role;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CompanyBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Company expectedCompany = new CompanyBuilder(TIKTOK).withTags(VALID_TAG_LOW).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + TAG_DESC_LOW, new AddCommand(expectedCompany));


        // multiple tags - all accepted
        Company expectedCompanyMultipleTags = new CompanyBuilder(TIKTOK).withTags(VALID_TAG_LOW, VALID_TAG_HIGH)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK
                        + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK + TAG_DESC_HIGH + TAG_DESC_LOW,
                new AddCommand(expectedCompanyMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedCompanyString = NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK + TAG_DESC_LOW;

        // multiple names
        assertParseFailure(parser, NAME_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple roles
        assertParseFailure(parser, ROLE_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple deadlines
        assertParseFailure(parser, DEADLINE_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // multiple statuses
        assertParseFailure(parser, STATUS_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // multiple recruiter names
        assertParseFailure(parser, RECRUITER_NAME_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RECRUITER_NAME));

        // multiple fields repeated
        assertParseFailure(parser, NAME_DESC_GOOGLE + PHONE_DESC_GOOGLE + EMAIL_DESC_GOOGLE + ROLE_DESC_GOOGLE
                + DEADLINE_DESC_GOOGLE + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ROLE, PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_RECRUITER_NAME));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        //invalid deadline
        assertParseFailure(parser, INVALID_DEADLINE_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        //invalid status
        assertParseFailure(parser, INVALID_STATUS_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        //invalid recruiter name
        assertParseFailure(parser, INVALID_RECRUITER_NAME_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RECRUITER_NAME));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedCompanyString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedCompanyString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedCompanyString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid role
        assertParseFailure(parser, validExpectedCompanyString + INVALID_ROLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        //invalid deadline
        assertParseFailure(parser, validExpectedCompanyString + INVALID_DEADLINE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        //invalid status
        assertParseFailure(parser, validExpectedCompanyString + INVALID_STATUS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        //invalid recruiter name
        assertParseFailure(parser, validExpectedCompanyString + INVALID_RECRUITER_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RECRUITER_NAME));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Company expectedCompany = new CompanyBuilder(GOOGLE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_GOOGLE + PHONE_DESC_GOOGLE + EMAIL_DESC_GOOGLE + ROLE_DESC_GOOGLE
                        + DEADLINE_DESC_GOOGLE + STATUS_DESC_GOOGLE + RECRUITER_NAME_DESC_GOOGLE,
                new AddCommand(expectedCompany));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + VALID_PHONE_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + VALID_EMAIL_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing role prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + VALID_ROLE_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + VALID_DEADLINE_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing status prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + VALID_STATUS_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing recruiter name prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + VALID_RECRUITER_NAME_TIKTOK, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_TIKTOK + VALID_PHONE_TIKTOK + VALID_EMAIL_TIKTOK + VALID_ROLE_TIKTOK
                + VALID_DEADLINE_TIKTOK + VALID_STATUS_TIKTOK + VALID_RECRUITER_NAME_TIKTOK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + TAG_DESC_HIGH, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_TIKTOK + INVALID_PHONE_DESC + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + TAG_DESC_HIGH, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + INVALID_EMAIL_DESC
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + TAG_DESC_HIGH, Email.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + INVALID_ROLE_DESC + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + TAG_DESC_HIGH, Role.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + INVALID_DEADLINE_DESC + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + TAG_DESC_HIGH, Deadline.MESSAGE_CONSTRAINTS);
      
        // invalid status
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + INVALID_STATUS_DESC + RECRUITER_NAME_DESC_TIKTOK
                + TAG_DESC_HIGH + TAG_DESC_LOW, ApplicationStatus.MESSAGE_CONSTRAINTS);

        // invalid recruiter name
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + INVALID_RECRUITER_NAME_DESC
                + TAG_DESC_HIGH, RecruiterName.MESSAGE_CONSTRAINTS);
      
        // invalid tag
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + INVALID_TAG_DESC + VALID_TAG_LOW, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_PHONE_DESC + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK
                + RECRUITER_NAME_DESC_TIKTOK, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK + TAG_DESC_HIGH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
