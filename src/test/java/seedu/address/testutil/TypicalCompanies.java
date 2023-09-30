package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECRUITER_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECRUITER_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.company.Company;

/**
 * A utility class containing a list of {@code Company} objects to be used in tests.
 */
public class TypicalCompanies {

    public static final Company ALICE = new CompanyBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withRole("Software Engineer").withDeadline("2023-10-10").withStatus("PA")
            .withRecruiterName("John Doe").withTags("friends").build();
    public static final Company BENSON = new CompanyBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withRole("Software Engineer").withDeadline("2023-10-10").withStatus("PA")
            .withRecruiterName("Jane Doe").withTags("owesMoney", "friends").build();
    public static final Company CARL = new CompanyBuilder().withName("Carl Kurz").withEmail("heinz@example.com")
            .withPhone("95352563").withRole("Data Engineer").withDeadline("2023-12-11").withStatus("PA")
            .withRecruiterName("Jim Doe").build();
    public static final Company DANIEL = new CompanyBuilder().withName("Daniel Meier").withEmail("cornelia@example.com")
            .withPhone("87652533").withRole("Data Engineer").withDeadline("2023-03-12").withStatus("PA")
            .withRecruiterName("Tim Doe").withTags("friends").build();
    public static final Company ELLE = new CompanyBuilder().withName("Elle Meyer").withEmail("werner@example.com")
            .withPhone("9482224").withRole("UX Designer").withDeadline("2023-09-10").withStatus("PA")
            .withRecruiterName("Tom Doe").build();
    public static final Company FIONA = new CompanyBuilder().withName("Fiona Kunz").withEmail("lydia@example.com")
            .withPhone("9482427").withRole("UI Designer").withDeadline("2023-08-10").withStatus("PA")
            .withRecruiterName("Mary Doe").build();
    public static final Company GEORGE = new CompanyBuilder().withName("George Best").withEmail("anna@example.com")
            .withPhone("9482442").withRole("Web Developer").withDeadline("2023-05-10").withStatus("PA")
            .withRecruiterName("Timmy Doe").build();

    // Manually added
    public static final Company HOON = new CompanyBuilder().withName("Hoon Meier").withEmail("stefan@example.com")
            .withPhone("8482424").withRole("Mobile App Developer").withDeadline("2023-11-11").withStatus("PA")
            .withRecruiterName("Harry Styles").build();
    public static final Company IDA = new CompanyBuilder().withName("Ida Mueller").withEmail("hans@example.com")
            .withPhone("8482131").withRole("Software Engineer").withDeadline("2023-12-12").withStatus("PA")
            .withRecruiterName("Inez Lim").build();

    // Manually added - Company's details found in {@code CommandTestUtil}
    public static final Company AMY = new CompanyBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRole(VALID_ROLE_AMY).withDeadline(VALID_DEADLINE_AMY)
            .withStatus(VALID_STATUS_AMY).withRecruiterName(VALID_RECRUITER_NAME_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Company BOB = new CompanyBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withRole(VALID_ROLE_BOB).withDeadline(VALID_DEADLINE_BOB)
            .withStatus(VALID_STATUS_BOB).withRecruiterName(VALID_RECRUITER_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    private TypicalCompanies() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical companies.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Company company : getTypicalCompanies()) {
            ab.addCompany(company);
        }
        return ab;
    }

    public static List<Company> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
