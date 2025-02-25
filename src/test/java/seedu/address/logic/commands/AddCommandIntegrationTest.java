package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
    @Test
    public void execute_newCompany_success() {
        Company validCompany = new CompanyBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addCompany(validCompany);

        assertCommandSuccess(new AddCommand(validCompany), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(validCompany)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCompany_throwsCommandException() {
        Company companyInList = model.getAddressBook().getCompanyList().get(0);
        assertCommandFailure(new AddCommand(companyInList), model,
                new CommandException.DuplicateException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(
                        companyInList,
                        model.getDuplicateIndexFromOriginalAddressbook(companyInList),
                        companyInList.listAllChangedFields(model.getDuplicateCompany(companyInList)),
                                true)
                ).getMessage());
    }
}
