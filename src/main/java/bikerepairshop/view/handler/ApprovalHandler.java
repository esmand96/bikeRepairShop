package bikerepairshop.view.handler;


import bikerepairshop.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import bikerepairshop.model.dto.ReceiptDTO;
import bikerepairshop.model.dto.RepairTaskDTO;

/**
 * FXML handler for the recepti
 * on approval bikerepairshop.view.
 *
 * Flow:
 *   1. On init / tab shown: load all READY_FOR_APPROVAL repair orders.
 *   2. Click a row -> full details (incl. diagnosis, tasks, estimated time) shown right.
 *   3. Receptionist picks Approve or Reject, clicks Submit.
 *   4. Approve  -> bikerepairshop.controller.approveRepairOrder(id) + bikerepairshop.controller.getReceipt(id) + popup.
 *      Reject   -> bikerepairshop.controller.rejectRepairOrder(id).
 *   5. List refreshes so the handled order disappears.
 */
public class ApprovalHandler implements ViewHandler<Controller> {

    private Controller controller;
    private PresentRepairOrderForApprovalDTO currentRepairOrder;

    // --- Left: order list ---
    @FXML private TableView<PresentRepairOrderForApprovalDTO> orderTable;
    @FXML private TableColumn<PresentRepairOrderForApprovalDTO, String> orderPhoneCol;
    @FXML private TableColumn<PresentRepairOrderForApprovalDTO, String> orderBikeModelCol;

    // --- Right: order details ---
    @FXML private VBox orderDetailCard;
    @FXML private Label phoneLabel;
    @FXML private Label customerLabel;
    @FXML private Label bikeModelLabel;
    @FXML private Label problemLabel;
    @FXML private Label diagnosisLabel;
    @FXML private Label estimatedTimeLabel;

    @FXML private TableView<RepairTaskDTO> taskTable;
    @FXML private TableColumn<RepairTaskDTO, String> taskDescCol;
    @FXML private TableColumn<RepairTaskDTO, String> taskPriceCol;

    @FXML private RadioButton approveRadio;
    @FXML private RadioButton rejectRadio;

    @FXML private Label statusLabel;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    private void initialize() {
        /*
        orderPhoneCol.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().get()));
        orderBikeModelCol.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getBikeModel()));

        taskDescCol.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getDescription()));
        taskPriceCol.setCellValueFactory(data ->
            new SimpleStringProperty(String.valueOf(data.getValue().getPrice())));

        orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectOrder(newSel);
            }
        });

         */
        refreshOrderList();
    }

    /**
     * Called from MainApp when the Approval tab becomes visible.
     */
    public void refreshOrderList() {
        hideOrder();

        // TODO: bikerepairshop.controller call - get all READY_FOR_APPROVAL repair orders
       // List<PresentRepairOrderForApprovalDTO> orders = bikerepairshop.controller.findAllReadyForApprovalOrders();

       // orderTable.getItems().setAll(orders);
    }

    private void selectOrder(PresentRepairOrderForApprovalDTO order) {
        clearStatus();
        this.currentRepairOrder = order;

        phoneLabel.setText(order.getCustomerPhoneNumber());
        /*
        bikeModelLabel.setText(order.getBikeModel());
        problemLabel.setText(order.getProblemDescription());
        diagnosisLabel.setText(order.getDiagnosis());
        estimatedTimeLabel.setText(order.getEstimatedRepairTime());
         */

       // taskTable.getItems().setAll(order.getProposedRepairTasks());

        // Reset decision
        approveRadio.setSelected(false);
        rejectRadio.setSelected(false);

        orderDetailCard.setVisible(true);
        orderDetailCard.setManaged(true);
    }

    @FXML
    private void onSubmitDecision() {
        if (currentRepairOrder == null) return;

        boolean approve = approveRadio.isSelected();
        boolean reject  = rejectRadio.isSelected();

        if (!approve && !reject) {
            showStatus("Please select Approve or Reject.", "error-message");
            return;
        }

        String orderId = currentRepairOrder.getRepairOrderId();

        if (approve) {
            // TODO: bikerepairshop.controller calls
            //bikerepairshop.controller.approveRepairOrder(orderId);
           // ReceiptDTO receipt = bikerepairshop.controller.getReceipt(orderId);

           // openReceiptPopup(receipt);
            showStatus("Order " + orderId + " approved. Receipt printed.", "success-message");
        } else {
            // TODO: bikerepairshop.controller call
          //  bikerepairshop.controller.rejectRepairOrder(orderId);
            showStatus("Order " + orderId + " rejected.", "info-message");
        }

        refreshOrderList();
    }

    private void openReceiptPopup(ReceiptDTO receipt) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/receipt.fxml"));
            VBox root = loader.load();

            ReceiptHandler handler = loader.getController();
            handler.setReceipt(receipt);

            Stage popup = new Stage();
            popup.setTitle("Receipt");
            popup.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            popup.setScene(scene);
            handler.setStage(popup);
            popup.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException("Failed to open receipt popup", e);
        }
    }

    // ---- View state helpers ----

    private void hideOrder() {
        currentRepairOrder = null;
        orderDetailCard.setVisible(false);
        orderDetailCard.setManaged(false);
        phoneLabel.setText("");
        customerLabel.setText("");
        bikeModelLabel.setText("");
        problemLabel.setText("");
        diagnosisLabel.setText("");
        estimatedTimeLabel.setText("");
        taskTable.getItems().clear();
        approveRadio.setSelected(false);
        rejectRadio.setSelected(false);
    }

    private void showStatus(String message, String styleClass) {
        statusLabel.setText(message);
        statusLabel.getStyleClass().setAll(styleClass);
    }

    private void clearStatus() {
        statusLabel.setText("");
    }
}
