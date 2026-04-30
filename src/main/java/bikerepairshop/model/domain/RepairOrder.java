package bikerepairshop.model.domain;

import java.util.List;


public class RepairOrder {
    private String date;
    private String problemDescription;
    private RepairOrderState state;
    private CustomerDetails customerDetails;
    private List<RepairTask> repairTasks;
    private DiagnosticReport diagnosticReport;
    private String id;

    public RepairOrder(String date, String problemDescription, RepairOrderState state, CustomerDetails customerDetails, List<RepairTask> repairTasks, DiagnosticReport diagnosticReport, String id){
        this.date = date;
        this.problemDescription = problemDescription;
        this.state = state;
        this.customerDetails = customerDetails;
        this.repairTasks = repairTasks;
        this.diagnosticReport = diagnosticReport;
        this.id = id;
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

      public List<RepairTask> getRepairTasks() {
          return repairTasks;
      }

      public void setRepairTasks(List<RepairTask> repairTasks) {
          this.repairTasks = repairTasks;
      }

      public DiagnosticReport getDiagnosticReport() {
          return diagnosticReport;
      }

      public void setDiagnosticReport(DiagnosticReport diagnosticReport) {
          this.diagnosticReport = diagnosticReport;
      }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
