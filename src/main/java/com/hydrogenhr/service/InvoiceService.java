package com.hydrogenhr.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.hydrogenhr.persistence.entity.Invoice;

public interface InvoiceService {
    List<Invoice> getAllInvoices();

    public Optional<Invoice> getInvoiceById(Long id);

    Invoice createInvoice(Invoice invoice);

    Invoice updateInvoice(Long id, Invoice updatedInvoice);

    void deleteInvoice(Long id);
}
