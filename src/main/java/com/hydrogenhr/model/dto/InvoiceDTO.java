package com.hydrogenhr.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDTO {
    private BigDecimal amountDue;
    private UUID revenueSetupId;
}
