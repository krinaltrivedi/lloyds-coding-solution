package com.krinal.lloyds.interview_coding_solution.atm.client.response.validator;

public interface ResponseValidator<T> {
    boolean isBeanValid(T t);
}
