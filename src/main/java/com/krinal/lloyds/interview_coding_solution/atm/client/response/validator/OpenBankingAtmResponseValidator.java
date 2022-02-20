package com.krinal.lloyds.interview_coding_solution.atm.client.response.validator;

import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Component
@Slf4j
public class OpenBankingAtmResponseValidator implements ResponseValidator<OpenBankingAtmResponse> {
    @Override
    public boolean isBeanValid(OpenBankingAtmResponse openBankingAtmResponse) throws InvalidResponseException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        final var constraintViolations = validator.validate(openBankingAtmResponse);
        if (constraintViolations.size() > 0) {
            log.error("OpenBankingAtmResponse violates validations rules.");
            constraintViolations.forEach(
                    violation -> log.error(violation.getMessage())
            );
            throw new InvalidResponseException("Unable to process the retrieved list of ATMs. Please try later.");
        }
        return true;
    }
}
