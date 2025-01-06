package com.dbh.ilps_service.loan_reg.entity;
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
@Table(name="LOAN_REG" , schema = "SONET")
public class LoanReg implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "FILE_NO")
    private Integer fileNo;

    @Column(name = "MARK_FLD")
    private String markFld;

    @Column(name = "SR_NO")
    private Integer srNo;

    @Column(name = "PROP_NO")
    private String propNo;

    @Column(name = "PROC_BRN")
    private String procBrn;

    @Column(name = "LOAN_GRP")
    private Integer loanGrp;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "REQ_DT")
    private Date reqDt;

    @Column(name = "LOAN_PURP")
    private String loanPurp;

    @Column(name = "ORIGIN_PLC")
    private String originPlc;

    @Column(name = "SERV_PLC")
    private String servPlc;

    @Column(name = "FILE_STS")
    private String fileSts;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "STS_DATE")
    private Date stsDate;

    @Column(name = "SCH_KNOWN")
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

    @Column(name = "SPL_ARRNG")
    private String splArrng;

    @Column(name = "PROP_TYP")
    private String propTyp;

    @Column(name = "PROP_CST")
    private Integer propCst;

    @Column(name = "FILE_INC")
    private Float fileInc;

    @Column(name = "TOT_DISB")
    private Integer totDisb;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "DISB_DATE")
    private Date disbDate;

    @Column(name = "NAME")
    private String custName;

    @Column(name = "SANC_AMT")
    private Integer sanctionAmount;

    @Column(name = "AMT_REQ")
    private Integer amountRequested = 0;

    @Column(name = "AMT_RECOM")
    private Integer amountRecommended = 0;

    @Column(name = "AMT_APP")
    private Integer amountApproved;

    @Column(name = "IIR")
    private Float iir;

    @Column(name = "FOIR")
    private Float foir;

    @Column(name = "LCR")
    private Float lcr;

    @Column(name = "EMI")
    private Integer emi;

    @Column(name = "TERM")
    private Integer term;

    @Column(name = "ROI")
    private BigDecimal roi;

    @Column(name = "FEE_RECBLE")
    private BigDecimal  feeReceivable;

    @Column(name = "LOAN_TYPE")
    private String loanType;

    @Column(name = "APPR_BY")
    private String apprBy;

    @Column(name = "PERC_CONST")
    private Integer percConst;

    @Column(name = "DOC_RECD")
    private String docRecd;

    @Column(name = "APPROV1_BY")
    private String approve1By;

    @Column(name = "APPROV2_BY")
    private String approve2By;

    @Column(name = "DISB_M_NO")
    private Integer disbMno;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "DISB_M_DT")
    private Date disbmDate;

    @Column(name = "DISB_NO")
    private Integer disbNo;

    @Column(name = "TOT_LACDSB")
    private Integer totLacDsb;

    @Column(name = "DSB_FX_AMT")
    private Integer dsbFxAmt;

    @Column(name = "DSB_RECO")
    private Integer dsbReco;

    @Column(name = "ARH_SPREAD")
    private Double arhSpread;

    @Column(name = "PLR_SERIES")
    private String plrSeries;

    @Column(name = "PRDCITY")
    private Integer prdcity;

    @Column(name = "SANC_EMI")
    private Integer sancEmi;

    @Column(name = "SANC_ROI")
    private Integer sancRoi;

    @Column(name = "SANC_TERM")
    private Integer sancTerm;

    @Column(name = "PFILE_NO")
    private Integer pfileNo;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "DDATE")
    private Date dDate;

    @Column(name = "BRK_CODE")
    private Integer brkCode;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "ACC_DT")
    private Date accDate;

    @Column(name = "ACC_BY")
    private String accBy;

    @Column(name = "SEC_TYPE")
    private String secType;

    @Column(name = "LST_UPD_BY")
    private String lstUpdBy;

    @Column(name = "LOAN_CAT")
    private String loanCat;

    @Column(name = "GRACE_PRD")
    private Integer gracePrd;

    @Column(name = "AFEE_PER")
    private Double afeePer;

    @Column(name = "LFEE_PER")
    private Double lfeePer;

    @Column(name = "PROP_TYP_O")
    private String propTypo;

    @Column(name = "OFFER_TYPE")
    private String offerType;

    @Column(name = "ARHL_IND")
    private String arhlInd;

    @Column(name = "PCC")
    private String pcc;

    @Column(name = "E1")
    private String e1;

    @Column(name = "E2")
    private String e2;

    @Column(name = "E3")
    private String e3;

    @Column(name = "E4")
    private String e4;

    @Column(name = "EMP_CD")
    private String empCd;

    @Column(name = "DEP_CD")
    private String depCd;

    @Column(name = "bt")
    private Integer bt;

    @Column(name = "E_STATUS")
    private String eStatus;

    @Column(name = "F_ENTRY_BY")
    private String fEntryBy;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "F_ENTRY_DT")
    private Date fEntryDate;

    @Column(name = "S_ENTRY_BY")
    private String sEntryBy;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "S_ENTRY_DT")
    private Date sEntryDate;

    @Column(name = "C_BY")
    private String cBy;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "C_DT")
    private Date cDate;

    @Column(name = "MIS_MATCH")
    private String misMatch;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "SCAN_DT")
    private Date scanDate;

    @Column(name = "EMP_TYPE")
    private String empType;

    @Column(name = "TPA_RP")
    private Double tpaRp;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "BASE_ROI")
    private Double baseroi;

    @Column(name = "MARGIN")
    private Double  margin;

    @Column(name = "MARGIN1")
    private Double  margin1;

    @Column(name = "DSPD_CD")
    private Integer dspdCd;

    @Column(name = "EMP_REF")
    private String empRef;

    @Column(name = "TEL_CD")
    private String telCd;

    @Column(name = "TEL_RN")
    private String telRn;

    @Column(name = "DSP_BRAN")
    private Integer dspBran;

    @Column(name = "EMAIL_REQ")
    private String emailReq;

    @Column(name = "HUB")
    private Integer hub;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "OFF_CH_DT")
    private Date offChDate;

    @Column(name = "PROC_BRN1")
    private Integer procBran1;

    @Column(name = "OUT_DSPL")
    private String outDspl;

    @Column(name = "LA_REF_ID")
    private String laRefId;

    @Column(name = "LN_TYPE")
    private String lnType;

    @Column(name = "CORP_DET")
    private String corpDetail;

    @Column(name = "PGP_PAY_MODE")
    private Integer pgpPayMode;
}
