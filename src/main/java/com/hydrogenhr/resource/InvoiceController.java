package com.hydrogenhr.resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.model.dto.InvoiceDTO;
import com.hydrogenhr.persistence.entity.Invoice;
import com.hydrogenhr.persistence.entity.RevenueSetup;
import com.hydrogenhr.persistence.entity.Transaction;
import com.hydrogenhr.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final RevenueSetupController revenueSetupController;

    // Get all invoices
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    // Get an invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    // Create a new invoice
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        ResponseEntity<RevenueSetup> revenue = revenueSetupController.getRevenueSetupById(invoiceDTO.getRevenueSetupId());

        if (!revenue.getStatusCode().is2xxSuccessful() || revenue.getBody() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(null);
        }



        Invoice newInvoice = Invoice.builder()
        .amountDue(invoiceDTO.getAmountDue())
        .revenueSetup(revenue.getBody())
        .build();

        Invoice invoice2 = invoiceService.createInvoice(newInvoice);

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(invoice2);
    }


}
