package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hydrogenhr.persistence.entity.Invoice;
import com.hydrogenhr.persistence.repository.InvoiceRepository;
import com.hydrogenhr.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    
    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        return invoiceRepository.findById(id).map(invoice -> {
            invoice.setAmountDue(updatedInvoice.getAmountDue());
            invoice.setRevenueSetup(updatedInvoice.getRevenueSetup());
            return invoiceRepository.save(invoice);
        }).orElseThrow(() -> new RuntimeException("Invoice not found with id " + id));
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

}
