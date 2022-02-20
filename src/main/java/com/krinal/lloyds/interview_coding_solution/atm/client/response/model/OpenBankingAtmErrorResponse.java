package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import lombok.Data;

@Data
public class OpenBankingAtmErrorResponse {
    private String status;
    private String title;
    private String description;
}
