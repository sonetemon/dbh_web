package com.dbh.ilps_service.loan_approval.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="LOAN_REG_PEND" , schema = "ILPS")

public class LoanRequestPending implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "FILE_NO", precision=15)
    private Integer fileNo;

    @Column(name = "MARK_FLD", length=1)
    private String markFld;

    @Column(name = "SLS_PER", length=4000)
    private String slsPer;

    @Column(name = "SLS_TYP", length=4000)
    private String slsType;

    @Column(name = "DAY_DIF")
    private Integer dayDifference;

    @Column(name = "SR_NO", precision=10)
    private Integer srNo;

    @Column(name = "PROP_NO", length=10)
    private String propNo;

    @Column(name = "PROC_BRN", length=4)
    private String procBrn;

    @Column(name = "LOAN_GRP", precision=4)
    private Integer loanGroup;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "REQ_DT")
    private Date requestDate;

    @Column(name = "LOAN_PURP", length=6)
    private String loanPurpose;

    @Column(name = "ORIGIN_PLC", length=10)
    private String originPlc;

    @Column(name = "SERV_PLC", length=6)
    private String servPlc;

    @Column(name = "FILE_STS", length=15)
    private String fileSts;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "STS_DATE")
    private Date stsDate;

    @Column(name = "SCH_KNOWN", length=4)
    private String schKnown;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "F_DSB_DT")
    private Date fDsbDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "OFFER_DATE")
    private Date offerDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "SANC_DATE")
    private Date sancDate;

    @Column(name = "SPL_ARRNG", length=4)
    private String splArrng;

    @Column(name = "PROP_TYP", length=1)
    private String propTyp;

    @Column(name = "PROP_CST", precision=15)
    private Integer propCst;

    @Column(name = "FILE_INC", precision=126)
    private Float fileInc;

    @Column(name = "TOT_DISB", precision=15)
    private Integer totDisb;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "DISB_DATE")
    private Date disbDate;

    @Column(name = "NAME", length=60)
    private String customerName;

    @Column(name = "SANC_AMT", precision=15)
    private Integer sanctionAmount;

    @Column(name = "AMT_REQ", precision=15)
    private Integer amountRequested = 0;

    @Column(name = "AMT_RECOM", precision=15)
    private Integer amountRecommended = 0;

    @Column(name = "AMT_APP", precision=15)
    private Integer amountApproved;

    @Column(name = "IIR", precision=126)
    private Float iir;

    @Column(name = "FOIR", precision=126)
    private Float foir;

    @Column(name = "LCR", precision=126)
    private Float lcr;

    @Column(name = "EMI", precision=8)
    private Integer emi;

    @Column(name = "TERM", precision=4)
    private Integer term;

    @Column(name = "ROI", precision = 6, scale = 2)
    private BigDecimal roi;

    @Column(name = "FEE_RECBLE", precision = 15, scale = 2)
    private BigDecimal  feeReceivable;

    @Column(name = "LOAN_TYPE", length=10)
    private String loanType;

    @Column(name = "APPR_BY", length=10)
    private String apprBy;

    @Column(name = "PERC_CONST", precision = 3)
    private Integer percConst;

    @Column(name = "DOC_RECD", length=1)
    private String docRecd;

    @Column(name = "APPROV1_BY", length=10)
    private String approve1By;

    @Column(name = "APPROV2_BY", length=10)
    private String approve2By;

    @Column(name = "DISB_M_NO",precision = 10)
    private Integer disbMno;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "DISB_M_DT")
    private Date disbmDate;

    @Column(name = "DISB_NO", precision = 2)
    private Integer disbNo;

    @Column(name = "TOT_LACDSB", precision = 15)
    private Integer totLacDsb;

    @Column(name = "DSB_FX_AMT", precision = 15)
    private Integer dsbFxAmt;

    @Column(name = "DSB_RECO", precision = 10)
    private Integer dsbReco;

    @Column(name = "ARH_SPREAD", precision = 6, scale = 2)
    private BigDecimal arhSpread;

    @Column(name = "PLR_SERIES", length=10)
    private String plrSeries;

    @Column(name = "PRDCITY", precision = 2)
    private Integer prdcity;

    @Column(name = "SANC_EMI", precision = 8)
    private Integer sancEmi;

    @Column(name = "SANC_ROI", precision = 6, scale = 2)
    private BigDecimal sancRoi;

    @Column(name = "SANC_TERM", precision = 4)
    private Integer sancTerm;

    @Column(name = "PFILE_NO", precision = 10)
    private Integer pfileNo;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "DDATE")
    private Date dDate;

    @Column(name = "BRK_CODE", precision = 6)
    private Integer brkCode;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "ACC_DT")
    private Date accDate;

    @Column(name = "ACC_BY", length=6)
    private String accBy;

    @Column(name = "SEC_TYPE", length=11)
    private String secType;

    @Column(name = "LST_UPD_BY", length=6)
    private String lstUpdBy;

    @Column(name = "LOAN_CAT", length=1)
    private String loanCat;

    @Column(name = "GRACE_PRD", precision = 5)
    private Integer gracePrd;

    @Column(name = "AFEE_PER", precision = 5, scale = 2)
    private BigDecimal afeePer;

    @Column(name = "LFEE_PER", precision = 5, scale = 2)
    private BigDecimal lfeePer;

    @Column(name = "PROP_TYP_O", length=1)
    private String propTypo;

    @Column(name = "OFFER_TYPE", length=3)
    private String offerType;

    @Column(name = "ARHL_IND", length=1)
    private String arhlInd;

    @Column(name = "PCC", length=1)
    private String pcc;

    @Column(name = "E1", length=1)
    private String e1;

    @Column(name = "E2", length=1)
    private String e2;

    @Column(name = "E3", length=10)
    private String e3;

    @Column(name = "E4", length=10)
    private String e4;

    @Column(name = "EMP_CD", length=10)
    private String empCd;

    @Column(name = "DEP_CD", length=10)
    private String depCd;

    @Column(name = "DEV_TYPE", length=4000)
    private String devType;

    @Column(name = "BOM_REQ", precision = 1)
    private Integer bomRequest;

    @Column(name = "HOC_REQ", precision = 1)
    private Integer hocRequest;

    @Column(name = "HOP_REQ", precision = 1)
    private Integer hopRequest;

    @Column(name = "MD_REQ", precision = 1)
    private Integer mdRequest;

    @Column(name = "HOPN_REQ", precision = 1)
    private Integer hopnRequest;

    @Column(name = "FAPC_REQ", precision = 1)
    private Integer fapcRequest;

    @Column(name = "E1_REM", length = 4000)
    private String e1Remarks;

    @Column(name = "RECO_DISB", precision = 3)
    private Integer recoDisb;

    @Column(name = "RECO_BY", length = 6)
    private Integer recommendedBy;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "VALID")
    private Date valid;

    @Column(name = "MRCD", precision = 126)
    private Double mrcd;

    @Column(name = "BAA_REQ")
    private Integer baaReq;

    @Column(name = "SCN_NM")
    private Integer scnNm;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "EDD")
    private Date edd;

    @Column(name = "PPD1", length = 4000)
    private String pdd1;

    @Column(name = "PRIORITY", length = 4)
    private String priority;

    @Column(name = "HUB", precision = 2)
    private Integer hub;

    @Column(name = "E2_REM", length = 1500)
    private String e2Remarks;

    @Column(name = "PROC_BRN1", precision = 4)
    private Integer procBrn1;

    @Column(name = "PARA_DESC", length = 1500)
    private String paraDesc;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "REQ_TAT")
    private Date reqTat;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "APRV_TDD")
    private Date aprvTdd;

}
