package com.krinal.lloyds.interview_coding_solution.atm.converter;

public interface ResponseConverter<F, T> {
    T convert(F f);
}
