package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hydrogenhr.model.dto.InvoiceDTO;
import com.hydrogenhr.persistence.entity.Invoice;
import com.hydrogenhr.persistence.entity.RevenueSetup;
import com.hydrogenhr.persistence.repository.InvoiceRepository;
import com.hydrogenhr.service.InvoiceService;
import com.hydrogenhr.service.RevenueSetupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final RevenueSetupService revenueSetupService;
    
    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Optional<RevenueSetup> revenueSetup = revenueSetupService.getRevenueSetupById(invoiceDTO.getRevenueSetupId());

        Invoice invoice = Invoice.builder()
        .amountDue(invoiceDTO.getAmountDue())
        .revenueSetup(revenueSetup.get())
        .build();
        return invoiceRepository.save(invoice);
    }




}
