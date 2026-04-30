package bikerepairshop.model.domain;
                                                                                                                          /// FRÅGOR HÄR

public class RepairOrder {
    private String date;
    private String problemDescription;
    private RepairOrderState state;
    private CustomerDetails customerDetails;
    private RepairTask[] repairTasks;                                                                                /// ta bort listan?
    private DiagnosticReport diagnosticReport;

    public RepairOrder(String date, String problemDescription, RepairOrderState state, CustomerDetails customerDetails, RepairTask[] repairTasks, DiagnosticReport diagnosticReport){
        this.date = date;
        this.problemDescription = problemDescription;
        this.state = state;
        this.repairTasks = repairTasks;
        this.diagnosticReport = diagnosticReport;
    }

      public String getDate() {
        return date;
      }

      public void setDate(String date) {
          this.date = date;
      }

      public String getProblemDescription() {
          return problemDescription;
      }

      public void setProblemDescription(String problemDescription) {
          this.problemDescription = problemDescription;
      }

      public RepairOrderState getState() {
          return state;
      }

      public void setState(RepairOrderState state) {
          this.state = state;
      }

      public CustomerDetails getCustomerDetails() {
          return customerDetails;
      }

      public void setCustomerDetails(CustomerDetails customerDetails) {
          this.customerDetails = customerDetails;
      }

      public RepairTask[] getRepairTasks() {
          return repairTasks;
      }

      public void setRepairTasks(RepairTask[] repairTasks) {
          this.repairTasks = repairTasks;
      }

      public DiagnosticReport getDiagnosticReport() {
          return diagnosticReport;
      }

      public void setDiagnosticReport(DiagnosticReport diagnosticReport) {
          this.diagnosticReport = diagnosticReport;
      }
    }
