package com.dbh.ilps_service.loan_approval.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserBranch implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMPL_NO", precision=126)
    private Long emplNo;

    @Column(name = "EMPSRTNAME", length=10)
    private String empShortName;

    @Column(name = "BR_CODE", precision=3)
    private Integer brCode;

    @Column(name = "SW", length=15)
    private String sw;

    @Column(name = "M_BR", length=1)
    private String mBr;
}
