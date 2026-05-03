package bikerepairshop.view.handler;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import bikerepairshop.model.dto.DiagnosticReportDTO;
import bikerepairshop.model.dto.ReceiptDTO;
import bikerepairshop.model.dto.common.RepairTaskDTO;

/**
 * Handler for the receipt popup.
 *
 * Not a ViewHandler<Controller> — it doesn't need the application bikerepairshop.controller,
 * it only renders a Receipt object passed in via setReceipt().
 *
 * ASSUMES Receipt has these getters. Rename in the mapping below if yours differ.
 */
public class ReceiptHandler {

    @FXML private Label orderIdLabel;
    @FXML private Label customerLabel;
    @FXML private Label phoneLabel;
    @FXML private Label emailLabel;

    @FXML private Label bikeBrandLabel;
    @FXML private Label bikeModelLabel;
    @FXML private Label bikeSerialLabel;
    @FXML private Label bikeTypeLabel;

    @FXML private Label problemLabel;
    @FXML private Label diagnosisLabel;
    @FXML private Label estimatedTimeLabel;

    @FXML private TableView<RepairTaskDTO> taskTable;
    @FXML private TableColumn<RepairTaskDTO, String> taskDescCol;
    @FXML private TableColumn<RepairTaskDTO, String> taskPriceCol;

    @FXML private Label totalLabel;

    private Stage stage;

    @FXML
    private void initialize() {
        taskDescCol.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getDescription()));
        taskPriceCol.setCellValueFactory(data ->
            new SimpleStringProperty(String.valueOf(data.getValue().getCost())));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Populate the receipt bikerepairshop.view from the given Receipt.
     * Adjust the getter names here if your Receipt bikerepairshop.model is named differently.
     */
    public void setReceipt(ReceiptDTO receipt) {

        customerLabel.setText(receipt.getName());
        phoneLabel.setText(receipt.getPhoneNumber());
        emailLabel.setText(receipt.getEmail());

        bikeBrandLabel.setText(receipt.getBikeBrand());
        bikeModelLabel.setText(receipt.getBikeModel());
        bikeSerialLabel.setText(receipt.getBikeSerialNumber());
        DiagnosticReportDTO reportDTO = receipt.getDiagnosticReport();
        problemLabel.setText(receipt.getProblemDescription());
        diagnosisLabel.setText(reportDTO.getDescription());
        estimatedTimeLabel.setText(reportDTO.getEstimatedRepairTime().toString());
        taskTable.getItems().setAll(receipt.getRepairTasks());

        double total = receipt.getTotalCost();

        totalLabel.setText(String.format("%.2f SEK", total));
    }

    @FXML
    private void onPrint() {
        // Simulated print — just close the popup
        close();
    }

    @FXML
    private void onClose() {
        close();
    }

    private void close() {
        if (stage != null) stage.close();
    }
}
