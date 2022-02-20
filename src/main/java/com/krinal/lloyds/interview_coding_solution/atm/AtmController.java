package com.krinal.lloyds.interview_coding_solution.atm;

import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.ErrorInfo;
import com.krinal.lloyds.interview_coding_solution.atm.service.AtmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AtmController {
    private final AtmService atmService;

    public AtmController(final AtmService atmService) {
        this.atmService = atmService;
    }

    @Operation(summary = "retrieves details of every ATM in the Lloyds Banking Group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of all ATMs",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AtmResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorInfo.class)) }),
            @ApiResponse(responseCode = "500", description = "error occurred on the server while processing",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorInfo.class)) }) })
    @GetMapping("/v1.0.0/atms")
    public ResponseEntity<AtmResponse> getAllAtms() {
        log.info("Received request to get all ATMs");
        final var atmResponse = atmService.getAllAtms();

        log.info("Sending list of ATMs to client. Identification: [{}], Number of ATMS: [{}]]",
                atmResponse.getIdentification(),
                atmResponse.getAtms().size());

        return ResponseEntity.ok(atmResponse);

    }
}
