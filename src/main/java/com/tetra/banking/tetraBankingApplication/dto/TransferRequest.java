package com.tetra.banking.tetraBankingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private String senderAccountNumber;
    private String senderEmail;
    private String recipientName;
    private String recipientAccountNumber;
    private String recipientEmail;
    private BigDecimal amount;
}
