package com.dbh.ilps_service.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoanRegRequest {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    private String custName;

    @NotNull
    private Integer fileNo;


}
