package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.company.Company;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyListCard.fxml";

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
    private Label id;
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

    /**
     * Creates a {@code CompanyCode} with the given {@code Company} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getName().fullName);
        role.setText(company.getRole().jobRole);
        deadline.setText(company.getDeadline().toString());
        status.setText(company.getStatus().getDescription());
        priorityFlowPane(company);
    }

    private void priorityFlowPane(Company company) {
        if (!company.getPriority().priority.equals("NONE")) {
            FlowPane priorityPane = new FlowPane();

            Label priorityValue = new Label(company.getPriority().priority);

            String backgroundColor;

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
                backgroundColor = "#444444"; // Dark gray (default)
                break;
            }

            priorityValue.setStyle("-fx-background-color: " + backgroundColor);

            priorityPane.getChildren().addAll(priorityValue);
            priority.getChildren().add(priorityPane);
        }
    }

}
