package com.krinal.lloyds.interview_coding_solution.atm;

import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmDetail;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.service.DefaultAtmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtmControllerTest {
    @Mock
    private DefaultAtmService defaultAtmService;

    private AtmController atmController;

    @BeforeEach
    public void setupTests() {
        atmController = new AtmController(defaultAtmService);
    }

    @Test
    public void getAllAtms_shouldReturnAllAtmsReturnedFromDownstream() {
        //Given
        final var identification = "123";
        final var atmList = List.of(new AtmDetail());
        final var atmResponseFromDownstream = new AtmResponse(identification, atmList);
        when(defaultAtmService.getAllAtms()).thenReturn(atmResponseFromDownstream);

        //When
        final var getAllAtmsResponseEntity = atmController.getAllAtms();

        //Then
        assertThat(getAllAtmsResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getAllAtmsResponseEntity.getBody()).isEqualTo(atmResponseFromDownstream);
    }
}
