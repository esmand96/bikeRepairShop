package bikerepairshop.integration;

import bikerepairshop.model.dto.ReceiptDTO;

/**
 * Provides access to the external printer used to print receipts. The actual printing
 * is omitted since external systems are out of scope for this seminar.
 */
public class PrinterIntegration {

    /**
     * Creates a new instance.
     */
    public PrinterIntegration() {
    }

    /**
     * Prints the specified receipt on the external printer.
     *
     * @param receiptDTO The receipt to print.
     */
    public void printReceipt(ReceiptDTO receiptDTO) {
    }
}