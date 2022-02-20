package com.krinal.lloyds.interview_coding_solution.atm.client;

import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmResponse;

public interface AtmClient {
    OpenBankingAtmResponse getAllAtms();
}
