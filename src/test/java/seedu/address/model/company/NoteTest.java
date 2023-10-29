package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidNote = "";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidNote));
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Remark.isValidNote(null));

        // invalid note
        assertFalse(Remark.isValidNote("")); // empty string
        assertFalse(Remark.isValidNote(" ")); // spaces only


        // valid note
        assertTrue(Remark.isValidNote("Google")); // alphabets only
        assertTrue(Remark.isValidNote("12345")); // numbers only
        assertTrue(Remark.isValidNote("3M@#$%^&{}")); // other characters
        assertTrue(Remark.isValidNote("Interview@idk-where(11/12/23 1500), Java & python")); // long note
    }

    @Test
    public void equals() {
        Remark note = new Remark("Valid note");

        // same values -> returns true
        assertTrue(note.equals(new Remark("Valid note")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5.0f));

        // different values -> returns false
        assertFalse(note.equals(new Remark("Other Valid Note")));
    }
}

