package seedu.address.ui;

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
    private FlowPane priority;
    @FXML
    private Label note;

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
        deadline.setText("Deadline: " + company.getDeadline().toString());
        status.setText("Application status: " + company.getStatus().getDescription());
        recruiterName.setText("Name: " + company.getRecruiterName().fullName);
        priorityFlowPane(company);
        note.setText(company.getNote().note);

    }

    private void priorityFlowPane(Company company) {
        if (!company.getPriority().priority.equals("NONE")) {
            FlowPane priorityPane = new FlowPane();

            Label priorityLabel = new Label("Priority: ");
            Label priorityValue = new Label(company.getPriority().priority);

            String backgroundColor = "#444444"; // Dark gray (default)

            switch (company.getPriority().priority) {
            case "HIGH":
                backgroundColor = "#990000"; // Dark red
                break;
            case "MEDIUM":
                backgroundColor = "#FF8000"; // Dark orange
                break;
            case "LOW":
                backgroundColor = "#006400"; // Dark green
                break;
            default:
                break;
            }

            priorityValue.setStyle("-fx-background-color: " + backgroundColor);

            priorityPane.getChildren().addAll(priorityLabel, priorityValue);
            priority.getChildren().add(priorityPane);
        }
    }

}

