package bikerepairshop.view.handler;


import bikerepairshop.controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import bikerepairshop.model.dto.RepairTaskDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * FXML handler for the technician bikerepairshop.view.
 *
 * ASSUMPTIONS about your bikerepairshop.model (adjust imports/getters if different):
 *   - RepairOrder has getPhoneNumber(), getBikeModel(), getProblemDescription()
 *   - Task has getDescription() and getPrice()
 *
 * Flow:
 *   1. On init / tab shown: load all newly-created repair orders into left table.
 *   2. Click a row -> that RepairOrder becomes currentRepairOrder and shows on the right.
 *   3. Technician fills in diagnosis and adds tasks (locally).
 *   4. On Submit: bikerepairshop.controller saves everything and marks order as ready; list refreshes.
 */
public class TechnicianHandler implements ViewHandler<Controller> {

    private Controller controller;
    private PresentNewlyCreatedRepairOrderDTO currentRepairOrder;

    // --- Left panel: order list ---
    @FXML private TableView<PresentNewlyCreatedRepairOrderDTO> orderTable;
    @FXML private TableColumn<PresentNewlyCreatedRepairOrderDTO, String> orderPhoneCol;
    @FXML private TableColumn<PresentNewlyCreatedRepairOrderDTO, String> orderBikeModelCol;

    // --- Right panel: order details ---
    @FXML private VBox orderDetailCard;
    @FXML private Label phoneLabel;
    @FXML private Label bikeModelLabel;
    @FXML private Label problemLabel;
    @FXML private TextArea diagnosisArea;

    // --- Right panel: task table ---
    @FXML private TableView<RepairTaskDTO> taskTable;
    @FXML private TableColumn<RepairTaskDTO, String> taskDescCol;
    @FXML private TableColumn<RepairTaskDTO, String> taskPriceCol;
    @FXML private TextField taskDescField;
    @FXML private TextField taskPriceField;

    @FXML private Label statusLabel;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    private void initialize() {
        // Map RepairOrder fields to left table columns
        orderPhoneCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRepairOrderId()));
        orderBikeModelCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getBikeModel()));

        // Map Task fields to right task table columns
        taskDescCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDescription()));
        taskPriceCol.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getCost())));

        // Selection listener - click a row to open that order
        orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectOrder(newSel);
            }
        });

    }

    // ---- Loading ----

    /**
     * Public so MainApp can call it when the Technician tab becomes visible.
     * Reloads all newly-created repair orders from the backend.
     */
    public void refreshOrderList() {
        System.out.println("--- refreshOrderList ---");
        System.out.println("orderTable = " + orderTable);
        System.out.println("orderPhoneCol = " + orderPhoneCol);
        System.out.println("orderBikeModelCol = " + orderBikeModelCol);

        hideOrder();

        //List<PresentNewlyCreatedRepairOrderDTO> orders = bikerepairshop.controller.findALlNewlyCreatedOrders();
        /*
        System.out.println("Got " + orders.size() + " orders");
        for (PresentNewlyCreatedRepairOrderDTO o : orders) {
            System.out.println("  phone=" + o.getPhoneNumber() + ", bikerepairshop.model=" + o.getBikeModel());
        }

        orderTable.getItems().setAll(orders);
        orderTable.refresh();
        System.out.println("Table items size after setAll: " + orderTable.getItems().size());

         */
    }

    /**
     * Called when the user clicks a row in the left list.
     * The clicked RepairOrder becomes the current one being edited.
     */
    private void selectOrder(PresentNewlyCreatedRepairOrderDTO order) {
        clearStatus();
        this.currentRepairOrder = order;

        phoneLabel.setText(order.getPhoneNumber());
        bikeModelLabel.setText(order.getBikeModel());
        problemLabel.setText(order.getProblemDescription());

        // Newly created orders have no diagnosis/tasks yet - start blank
        diagnosisArea.clear();
        taskTable.getItems().clear();

        orderDetailCard.setVisible(true);
        orderDetailCard.setManaged(true);
    }

    // ---- Button actions ----

    @FXML
    private void onAddTask() {
        if (currentRepairOrder == null) return;

        String desc = taskDescField.getText().trim();
        String priceText = taskPriceField.getText().trim();

        if (desc.isEmpty() || priceText.isEmpty()) {
            showStatus("Please fill in both task description and price.", "error-message");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            showStatus("Price must be a number.", "error-message");
            return;
        }

        RepairTaskDTO task = new RepairTaskDTO(desc, price);
        taskTable.getItems().add(task);

        taskDescField.clear();
        taskPriceField.clear();
        showStatus("Task added.", "success-message");
    }

    @FXML
    private void onRemoveTask() {
        RepairTaskDTO selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("Select a task to remove.", "error-message");
            return;
        }
        taskTable.getItems().remove(selected);
        showStatus("Task removed.", "success-message");
    }

    @FXML
    private void onSubmitOrder() {
        if (currentRepairOrder == null) return;

        String diagnosis = diagnosisArea.getText().trim();
        if (diagnosis.isEmpty()) {
            showStatus("Please enter a diagnosis before submitting.", "error-message");
            return;
        }

        if (taskTable.getItems().isEmpty()) {
            showStatus("Please add at least one task before submitting.", "error-message");
            return;
        }

        List<RepairTaskDTO> tasks = List.copyOf(taskTable.getItems());
        String phone = currentRepairOrder.getPhoneNumber();
        LocalDateTime estimatedRepairTime = LocalDateTime.now().plusDays(3);
      //  bikerepairshop.controller.addDiagnosisAndTasks(phone, diagnosis,estimatedRepairTime, tasks);

        showStatus("Order for " + phone + " submitted and marked as ready.", "success-message");
        refreshOrderList();
    }

    // ---- View state helpers ----

    private void hideOrder() {
        currentRepairOrder = null;
        orderDetailCard.setVisible(false);
        orderDetailCard.setManaged(false);
        phoneLabel.setText("");
        bikeModelLabel.setText("");
        problemLabel.setText("");
        diagnosisArea.clear();
        taskTable.getItems().clear();
        taskDescField.clear();
        taskPriceField.clear();
    }

    private void showStatus(String message, String styleClass) {
        statusLabel.setText(message);
        statusLabel.getStyleClass().setAll(styleClass);
    }

    private void clearStatus() {
        statusLabel.setText("");
    }
}