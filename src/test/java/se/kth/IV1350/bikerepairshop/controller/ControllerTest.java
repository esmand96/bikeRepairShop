package se.kth.IV1350.bikerepairshop.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.kth.IV1350.bikerepairshop.model.dto.CustomerDetailsDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import se.kth.IV1350.bikerepairshop.model.dto.ReceiptDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.service.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.kth.IV1350.bikerepairshop.TestUtil.*;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private Service service;

    @InjectMocks
    private Controller controller;

    @Test
    void findCustomer_shouldDelegateToService_andReturnTheResult() {
        String phoneNumber = CUSTOMER_PHONE;
        CustomerDetailsDTO expectedDto = mock(CustomerDetailsDTO.class);
        when(service.findCustomerByPhoneNumber(phoneNumber)).thenReturn(expectedDto);

        CustomerDetailsDTO result = controller.findCustomer(phoneNumber);

        assertSame(expectedDto, result, "Controller should return the DTO from Service");
        verify(service).findCustomerByPhoneNumber(phoneNumber);
    }

    @Test
    void enterCustomerDescription_shouldDelegateToService_withBothArguments() {
        String consultationId = CONSULTATION_ID;
        String problemDescription = ORDER_PROBLEM_DESCRIPTION;

        controller.enterCustomerDescription(consultationId, problemDescription);

        verify(service).createRepairOrder(consultationId, problemDescription);
    }

    @Test
    void getAllNewlyCreatedRepairOrders_shouldReturnListFromService() {
        List<PresentNewlyCreatedRepairOrderDTO> expectedList = List.of(mock(PresentNewlyCreatedRepairOrderDTO.class));
        when(service.getAllNewlyCreatedRepairOrders()).thenReturn(expectedList);

        List<PresentNewlyCreatedRepairOrderDTO> result = controller.getAllNewlyCreatedRepairOrders();

        assertSame(expectedList, result, "Controller should return the list from Service");
        verify(service).getAllNewlyCreatedRepairOrders();
    }

    @Test
    void enterDiagnosticReportAndRepairTasks_shouldDelegateToService_withAllArguments() {
        String repairOrderId = ORDER_ID;
        String description = ORDER_PROBLEM_DESCRIPTION;
        List<RepairTaskDTO> tasks = List.of(mock(RepairTaskDTO.class));
        LocalDateTime estimatedRepairTime = LocalDateTime.now();

        controller.enterDiagnosticReportAndRepairTasks(repairOrderId, description, tasks, estimatedRepairTime);

        verify(service).enterDiagnosticReportAndRepairTasks(repairOrderId, description, tasks, estimatedRepairTime);
    }

    @Test
    void getAllReadyForApprovalOrders_shouldReturnListFromService() {
        List<PresentRepairOrderForApprovalDTO> expectedList = List.of(mock(PresentRepairOrderForApprovalDTO.class));
        when(service.getAllReadyForApprovalOrders()).thenReturn(expectedList);

        List<PresentRepairOrderForApprovalDTO> result = controller.getAllReadyForApprovalOrders();

        assertSame(expectedList, result, "Controller should return the list from Service");
        verify(service).getAllReadyForApprovalOrders();
    }

    @Test
    void approveRepairOrder_shouldDelegateToService() {
        String repairOrderId = ORDER_ID;

        controller.approveRepairOrder(repairOrderId);

        verify(service).approveRepairOrder(repairOrderId);
    }

    @Test
    void getReceipt_shouldDelegateToService_andReturnReceiptDTO() {
        String repairOrderId = ORDER_ID;
        ReceiptDTO expectedReceipt = mock(ReceiptDTO.class);
        when(service.getReceipt(repairOrderId)).thenReturn(expectedReceipt);

        ReceiptDTO result = controller.getReceipt(repairOrderId);

        assertSame(expectedReceipt, result, "Controller should return the ReceiptDTO from Service");
        verify(service).getReceipt(repairOrderId);
    }
}