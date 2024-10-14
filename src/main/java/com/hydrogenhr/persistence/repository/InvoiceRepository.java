package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
}
