package com.dbh.ilps_service.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoanRegResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String custName;

    private Integer fileNo;
}
