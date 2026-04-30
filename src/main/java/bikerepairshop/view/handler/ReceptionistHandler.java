package bikerepairshop.view.handler;

import bikerepairshop.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import bikerepairshop.model.dto.CustomerDetailsDTO;

/**
 * FXML handler for the receptionist bikerepairshop.view.
 * All bikerepairshop.controller calls are left as TODOs — fill them in with your Controller.
 */
public class ReceptionistHandler implements ViewHandler<Controller> {

    private Controller controller;

    // --- FXML bindings ---
    @FXML private TextField phoneField;
    @FXML private VBox customerInfoCard;
    @FXML private Label nameLabel;
    @FXML private Label phoneLabel;
    @FXML private Label emailLabel;
    @FXML private Label brandLabel;
    @FXML private Label serialLabel;
    @FXML private Label typeLabel;
    @FXML private TextArea problemArea;
    @FXML private Label statusLabel;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void onSearch() {
        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            showStatus("Please enter a phone number.", "error-message");
            return;
        }

        clearStatus();
        CustomerDetailsDTO customer = controller.findCustomer(phone);
        if (customer == null) {
            hideCustomer();
            showStatus("No customer found with that phone number.", "info-message");
        }
        else
            showCustomer(customer);
    }

    @FXML
    private void onCreateOrder() {
        String problem = problemArea.getText().trim();
        if (problem.isEmpty()) {
            showStatus("Please describe the problem.", "error-message");
            return;
        }

        String phone = phoneLabel.getText();
        //bikerepairshop.controller.enterCustomerDescription(phone, problem);

        problemArea.clear();
        showStatus("Repair order created successfully!", "success-message");
    }

    // ---- UI helper methods (call these from your bikerepairshop.controller bikerepairshop.integration) ----

    /**
     * Populates customer info card and makes it visible.
     */
    public void showCustomer(CustomerDetailsDTO customer) {
        nameLabel.setText(customer.getName());
        phoneLabel.setText(customer.getPhoneNumber());
        emailLabel.setText(customer.getEmail());
        serialLabel.setText(customer.getBikeSerialNumber());
        typeLabel.setText(customer.getBikeModel());
        customerInfoCard.setVisible(true);
        customerInfoCard.setManaged(true);
    }

    /**
     * Hides the customer info card.
     */
    public void hideCustomer() {
        customerInfoCard.setVisible(false);
        customerInfoCard.setManaged(false);
    }

    private void showStatus(String message, String styleClass) {
        statusLabel.setText(message);
        statusLabel.getStyleClass().setAll(styleClass);
    }

    private void clearStatus() {
        statusLabel.setText("");
    }
}
