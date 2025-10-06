package in.gov.kfon.partner.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "partnerdetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "partnerid")
  private Long partnerId;

  @Column(name = "partnercompanyname", length = 150)
  private String partnerCompanyName;

  @Column(name = "companyregistrationno", length = 64)
  private String companyRegistrationNo;

  @Column(name = "servicetaxno", length = 64)
  private String serviceTaxNo;

  @Column(name = "incometaxno", length = 45)
  private String incomeTaxNo;

  @Column(name = "vatno", length = 45)
  private String vatNo;

  @Column(name = "state", length = 64)
  private String state;

  @Column(name = "pin")
  private Integer pin;

  @Column(name = "registrationdate")
  private LocalDate registrationDate;

  @Column(name = "agreementno", length = 100)
  private String agreementNo;

  @Column(name = "agreementdate")
  private LocalDate agreementDate;

  @Column(name = "keycontactname", length = 45)
  private String keyContactName;

  @Column(name = "keycontactnumber")
  private Long keyContactNumber;

  @Column(name = "keycontactemail", length = 128)
  private String keyContactEmail;

  @Column(name = "lastupdate")
  private LocalDate lastUpdate;

  @Column(name = "isactive", nullable = false)
  private Integer isActive = 1;

  @Column(name = "statecode", length = 5)
  private String stateCode;

  @Column(name = "ptype", length = 25)
  private String ptype;

  @Column(name = "address", length = 300)
  private String address;

  @Column(name = "agreementcopy", length = 256)
  private String agreementCopy;

  @Column(name = "bankpasscopy", length = 256)
  private String bankPassCopy;

  @Column(name = "incometaxproof", length = 256)
  private String incomeTaxProof;

  @Column(name = "bank_acholder", length = 128)
  private String bankAcHolder;

  @Column(name = "bank_actype", length = 45)
  private String bankAcType;

  @Column(name = "bank_name", length = 128)
  private String bankName;

  @Column(name = "bank_branch", length = 128)
  private String bankBranch;

  @Column(name = "bank_acno", length = 64)
  private String bankAcNo;

  @Column(name = "bank_ifsc", length = 64)
  private String bankIfsc;

  @Column(name = "companynature", length = 64)
  private String companyNature;

  @Column(name = "STCode", length = 3)
  private String stCode;

  @Column(name = "address_line1", length = 120)
  private String addressLine1;

  @Column(name = "address_line2", length = 120)
  private String addressLine2;

  @Column(name = "city", length = 64)
  private String city;

  @Column(name = "pincode", length = 10)
  private String pincode;

  @Column(name = "statename", length = 64)
  private String stateName;

  @Column(name = "district", length = 64)
  private String district;

  @Column(name = "gstin", length = 45)
  private String gstin;

  @Column(name = "service_description", length = 180)
  private String serviceDescription;

  @Column(name = "SAC", length = 45)
  private String sac;

  @Column(name = "gstindoc", length = 120)
  private String gstinDoc;

  @Column(name = "gst_verfied", nullable = false)
  private Boolean gstVerified = false;

  @Column(name = "taxpayertype", nullable = false)
  private Boolean taxPayerType = false;

  @Column(name = "gstdeclartionstatus", nullable = false)
  private Boolean gstDeclarationStatus = false;

  @Column(name = "subonrecharge")
  private Boolean subOnRecharge = true;

  @Column(name = "mspverified")
  private Short mspVerified = 1;

  @Column(name = "remarks", length = 100)
  private String remarks;

  @Column(name = "mspverfieddate")
  private LocalDateTime mspVerifiedDate;

  @Column(name = "bankverfieddate")
  private LocalDateTime bankVerifiedDate;

  @Column(name = "lastrenewed_agreementdate")
  private LocalDate lastRenewedAgreementDate;

  @Column(name = "updated_by", length = 20)
  private String updatedBy;

  @Column(name = "lastrenewed_agreementcopy", length = 256)
  private String lastRenewedAgreementCopy;

  @Column(name = "loc_type")
  private Short locType;

  @Column(name = "gst_status")
  private Short gstStatus;

  @Column(name = "catagory", length = 30)
  private String category;

  @Column(name = "vas_enabled", length = 45)
  private String vasEnabled;

  @Column(name = "cbldoc", length = 256)
  private String cblDoc;

  @Column(name = "territory_name", length = 45)
  private String territoryName;

  @Column(name = "ptnrattid", length = 100)
  private String ptnrAttId;

  @Column(name = "ptnrlang", length = 100)
  private String ptnrLang;

  @Column(name = "pop_name", length = 50)
  private String popName;

  @Column(name = "pop_pincode", length = 50)
  private String popPincode;

  @Column(name = "subonlinerechargeikon")
  private Boolean subOnlineRechargeIkon = true;

  @Column(name = "subonlinerechargehdfc")
  private Boolean subOnlineRechargeHdfc = true;

  @Column(name = "updated_service_area")
  private Boolean updatedServiceArea = false;

  @Column(name = "lnk_es_status", length = 20)
  private String lnkEsStatus = "Not_Delivered";

  @Column(name = "lnk_es_lnktype", length = 20)
  private String lnkEsLnkType;

  @Column(name = "lnk_es_date")
  private LocalDate lnkEsDate;

  @Column(name = "frc_recieved", length = 20)
  private String frcReceived = "No";

  @Column(name = "frc_paymt_date")
  private LocalDate frcPaymtDate;

  @Column(name = "reasfor_nlink_delivery", length = 200)
  private String reasForNlinkDelivery;

  @Column(name = "brief_remarks", length = 200)
  private String briefRemarks;

  @Column(name = "is_active")
  private Boolean isActiveFlag = true;

  @Column(name = "allowfreecon")
  private Boolean allowFreeCon = false;

  @Column(name = "enable_acs")
  private Integer enableAcs;

  @Column(name = "aadhaar_number", length = 15)
  private String aadhaarNumber;

  @Column(name = "aadhaarcopy", length = 100)
  private String aadhaarCopy;

  @Column(name = "olt_provider", length = 20)
  private String oltProvider;

  @Column(name = "alternate_phone")
  private Long alternatePhone;

  @Column(name = "enable_ews_service")
  private Boolean enableEwsService = false;

  @Column(name = "create_date")
  private LocalDateTime createDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;
}
