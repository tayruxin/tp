package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCompanyDescriptor(DESC_GOOGLE);
        assertTrue(DESC_GOOGLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GOOGLE.equals(DESC_GOOGLE));

        // null -> returns false
        assertFalse(DESC_GOOGLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_GOOGLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_GOOGLE.equals(DESC_TIKTOK));

        // different name -> returns false
        EditCommand.EditCompanyDescriptor editedAmy = new EditCompanyDescriptorBuilder(DESC_GOOGLE)
                .withName(VALID_NAME_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_GOOGLE).withPhone(VALID_PHONE_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_GOOGLE).withEmail(VALID_EMAIL_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_GOOGLE).withTags(VALID_TAG_HIGH).build();
        assertFalse(DESC_GOOGLE.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCommand.EditCompanyDescriptor editCompanyDescriptor = new EditCommand.EditCompanyDescriptor();
        String expected = EditCommand.EditCompanyDescriptor.class.getCanonicalName() + "{name="
                + editCompanyDescriptor.getName().orElse(null) + ", role="
                + editCompanyDescriptor.getRole().orElse(null) + ", deadline="
                + editCompanyDescriptor.getDeadline().orElse(null) + ", status="
                + editCompanyDescriptor.getStatus().orElse(null) + ", recruiter name="
                + editCompanyDescriptor.getRecruiterName().orElse(null) + ", phone="
                + editCompanyDescriptor.getPhone().orElse(null) + ", email="
                + editCompanyDescriptor.getEmail().orElse(null) + ", tags="
                + editCompanyDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editCompanyDescriptor.toString());
    }
}
