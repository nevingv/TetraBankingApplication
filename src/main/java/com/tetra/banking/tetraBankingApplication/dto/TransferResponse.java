package com.tetra.banking.tetraBankingApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferResponse {
    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;
}
