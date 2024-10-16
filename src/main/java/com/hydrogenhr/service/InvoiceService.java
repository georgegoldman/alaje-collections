package com.hydrogenhr.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.hydrogenhr.model.dto.InvoiceDTO;
import com.hydrogenhr.persistence.entity.Invoice;

public interface InvoiceService {
    List<Invoice> getAllInvoices();

    public Optional<Invoice> getInvoiceById(Long id);

    Invoice createInvoice(InvoiceDTO invoice);


}
