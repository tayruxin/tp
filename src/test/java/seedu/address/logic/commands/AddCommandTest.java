package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.META;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.SortOrder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.company.Company;
import seedu.address.model.company.Name;
import seedu.address.model.company.Role;
import seedu.address.testutil.CompanyBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_companyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCompanyAdded modelStub = new ModelStubAcceptingCompanyAdded();
        Company validCompany = new CompanyBuilder().build();

        CommandResult commandResult = new AddCommand(validCompany).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.getCompanyName(validCompany)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCompany), modelStub.companiesAdded);
    }

    //Duplicate tests, considered duplicate if same company name, same role and deadline
    @Test
    public void execute_fullyDuplicatedCompany_throwsCommandException() {
        Company validCompany = new CompanyBuilder().build();
        AddCommand addCommand = new AddCommand(validCompany);
        ModelStub modelStub = new ModelStubWithCompany(validCompany);

        assertThrows(CommandException.DuplicateCompanyException.class,
                new CommandException.DuplicateCompanyException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                        modelStub.getFilteredCompanyList().indexOf(validCompany),
                        validCompany.listAllChangedFields(
                                modelStub.getDuplicateCompany(validCompany)))).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_onlyDifferentCompanyName_doesNotThrowCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder.withName("Google").build();
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStubWithCompany modelStub = new ModelStubWithCompany(validCompany);

        assertDoesNotThrow(() -> addCommand.execute(modelStub),
                new CommandException.DuplicateCompanyException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getFilteredCompanyList().indexOf(validCompany),
                        validCompany.listAllChangedFields(
                                modelStub.getDuplicateCompany(validCompany)))
                ).getMessage());
    }

    @Test
    public void execute_failureOnlyDifferentRoles_doesNotThrowCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder.withRole("UI UX Designer").build();
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStub modelStub = new ModelStubWithCompany(validCompany);

        assertDoesNotThrow(() -> addCommand.execute(modelStub),
                new CommandException.DuplicateCompanyException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getFilteredCompanyList().indexOf(validCompany),
                        validCompany.listAllChangedFields(
                                modelStub.getDuplicateCompany(validCompany)))
                ).getMessage());
    }

    @Test
    public void execute_successOnlySameCompanyAndSameRole_throwsCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withPriority("HIGH")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .build();
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStub modelStub = new ModelStubWithCompany(validCompany);
        CommandException.DuplicateCompanyException thrownException =
                Assertions.assertThrows(CommandException.DuplicateCompanyException.class, (
                        ) -> addCommand.execute(modelStub));

        //assumes the caller of listAllChangedFields() is correct
        assertThrows(CommandException.DuplicateCompanyException.class,
                new CommandException.DuplicateCompanyException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(validCompany,
                                modelStub.getFilteredCompanyList().indexOf(validCompany),
                                duplicateCompany.listAllChangedFields(
                                        modelStub.getDuplicateCompany(validCompany)))).getMessage(), (
                ) -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_failureOnlySameRole_doesNotThrowsCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withName("Google")
                .build();
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStub modelStub = new ModelStubWithCompany(validCompany);

        assertDoesNotThrow(() -> addCommand.execute(modelStub),
                new CommandException.DuplicateCompanyException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(
                        validCompany,
                        modelStub.getDuplicateIndex(validCompany),
                        validCompany.listAllChangedFields(
                                modelStub.getDuplicateCompany(validCompany)))
                ).getMessage());
    }

    @Test
    public void execute_failureOnlySameCompany_doesNotThrowsCommandException() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        Company validCompany = companyBuilder.build();
        Company duplicateCompany = companyBuilder
                .withEmail("hello@gmail.com")
                .withPhone("89004789")
                .withDeadline("12-10-2015")
                .withPriority("LOW")
                .withRecruiterName("Cameron")
                .withStatus("PA")
                .withRole("UI UX Designer")
                .build();
        AddCommand addCommand = new AddCommand(duplicateCompany);
        ModelStub modelStub = new ModelStubWithCompany(validCompany);

        assertDoesNotThrow(() -> addCommand.execute(modelStub),
                new CommandException.DuplicateCompanyException(
                        Messages.getErrorMessageForDuplicateCompanyAddCommand(
                        validCompany,
                        modelStub.getDuplicateIndex(validCompany),
                        validCompany.listAllChangedFields(
                                modelStub.getDuplicateCompany(validCompany)))
                ).getMessage());
    }

    @Test
    public void equals() {
        Company meta = new CompanyBuilder().withName("Meta").build();
        Company tiktok = new CompanyBuilder().withName("Tiktok").build();
        AddCommand addMetaCommand = new AddCommand(meta);
        AddCommand addTiktokCommand = new AddCommand(tiktok);

        // same object -> returns true
        assertTrue(addMetaCommand.equals(addMetaCommand));

        // same values -> returns true
        AddCommand addMetaCommandCopy = new AddCommand(meta);
        assertTrue(addMetaCommand.equals(addMetaCommandCopy));

        // different types -> returns false
        assertFalse(addMetaCommand.equals(1));

        // null -> returns false
        assertFalse(addMetaCommand.equals(null));

        // different company -> returns false
        assertFalse(addMetaCommand.equals(addTiktokCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(META);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + META + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Company target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentViewedCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getCurrentViewedCompany() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void updateCurrentViewedCompany(Predicate<Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearCompanyDetailPanel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkDelete(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortCompaniesByDeadline(SortOrder sortOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterCompaniesByStatus(Predicate<Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Company getDuplicateCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getDuplicateIndex(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void findCompanies(Predicate<Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single company.
     */
    private class ModelStubWithCompany extends ModelStub {
        private final Company company;

        ModelStubWithCompany(Company company) {
            requireNonNull(company);
            this.company = company;
        }

        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return this.company.isSameCompany(company);
        }

        @Override
        public Company getDuplicateCompany(Company otherCompany) {
            requireNonNull(company);
            //Defensive Programming
            Name otherName = otherCompany.getName();
            Role otherRole = otherCompany.getRole();

            assert(otherName != null);
            assert(otherRole != null);

            boolean condition = otherCompany != null
                    && otherCompany.getName() != null
                    && otherCompany.getName().equals(company.getName())
                    && otherCompany.getRole() != null
                    && otherCompany.getRole().equals(company.getRole());

            return condition ? company : null;
        }

        @Override
        public void addCompany(Company company) {

        }

        @Override
        public void setCurrentViewedCompany(Company company) {

        }

        @Override
        public int getDuplicateIndex(Company company) {
            return 0;
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            return new AddressBook().getCompanyList();
        }
    }

    /**
     * A Model stub that always accept the company being added.
     */
    private class ModelStubAcceptingCompanyAdded extends ModelStub {
        final ArrayList<Company> companiesAdded = new ArrayList<>();
        final ArrayList<Company> companiesToView = new ArrayList<>();


        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return companiesAdded.stream().anyMatch(company::isSameCompany);
        }

        @Override
        public void addCompany(Company company) {
            requireNonNull(company);
            companiesAdded.add(company);
        }

        @Override
        public void setCurrentViewedCompany(Company company) {
            requireNonNull(company);
            companiesToView.add(company);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
