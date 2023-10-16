package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.company.Company;

/**
 * An UI component that displays detailed information of a {@code Company}.
 */
public class CompanyDetailCard extends UiPart<Region> {

    private static final String FXML = "CompanyDetailCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Company company;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label role;
    @FXML
    private Label deadline;
    @FXML
    private Label status;
    @FXML
    private Label recruiterName;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CompanyCode} with the given {@code Company} and index to display.
     */
    public CompanyDetailCard(Company company) {
        super(FXML);
        this.company = company;
        name.setText(company.getName().fullName);
        phone.setText("Phone: " + company.getPhone().value);
        email.setText("Email: " + company.getEmail().value);
        role.setText("Role: " + company.getRole().jobRole);
        deadline.setText("Deadline: " + company.getDeadline().value);
        status.setText("Application status: " + company.getStatus().getDescription());
        recruiterName.setText("Name: " + company.getRecruiterName().fullName);

        company.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

}

