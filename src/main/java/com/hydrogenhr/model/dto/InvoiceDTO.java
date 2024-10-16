package com.hydrogenhr.model.dto;


import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDTO {
    private Double amountDue;
    private UUID revenueSetupId;
}
