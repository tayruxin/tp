package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.AMAZON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Role;

public class JsonAdaptedCompanyTest {
    private static final String INVALID_COMPANY_NAME = "G@ogle";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#high";

    private static final String VALID_COMPANY_NAME = AMAZON.getName().toString();
    private static final String VALID_PHONE = AMAZON.getPhone().toString();
    private static final String VALID_EMAIL = AMAZON.getEmail().toString();
    private static final String VALID_ROLE = AMAZON.getRole().toString();
    private static final String VALID_DEADLINE = AMAZON.getDeadline().toString();
    private static final String VALID_STATUS = AMAZON.getStatus().toString();
    private static final String VALID_RECRUITER_NAME = AMAZON.getRecruiterName().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = AMAZON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCompanyDetails_returnsCompany() throws Exception {
        JsonAdaptedCompany company = new JsonAdaptedCompany(AMAZON);
        assertEquals(AMAZON, company.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(INVALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE, VALID_DEADLINE,
                        VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(null, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ROLE,
                        VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, null, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ROLE,
                        VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE,
                null, VALID_ROLE, VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE,
                VALID_EMAIL, null, VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                null, VALID_STATUS, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, null, VALID_RECRUITER_NAME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ApplicationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }


    @Test
    public void toModelType_nullRecruiterName_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RecruiterName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, invalidTags);
        assertThrows(IllegalValueException.class, company::toModelType);
    }

}
