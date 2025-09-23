package in.gov.kfon.partner.service;

import in.gov.kfon.partner.model.PartnerDetail;
import in.gov.kfon.partner.repository.PartnerDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PartnerDocumentServiceImpl implements PartnerDocumentService {

  private final PartnerDetailRepository partnerDetailRepository;

  private static final String UPLOAD_DIR = "/var/www/html/uploads/";

  private static final String AGREEMENT_COPY = "agreementcopy";
  private static final String BANK_PASS_COPY = "bankpasscopy";
  private static final String INCOME_TAX_PROOF = "incometaxproof";
  private static final String GSTIN_DOC = "gstindoc";
  private static final String CBL_DOC = "cbldoc";
  private static final String AADHAAR_COPY = "aadhaarcopy";

  private static final Map<String, String> FILE_PREFIX_MAP =
      Map.of(
          AGREEMENT_COPY, "KL.agree",
          BANK_PASS_COPY, "KL.bankpass",
          INCOME_TAX_PROOF, "KL.pancard",
          GSTIN_DOC, "KL.gstindoc",
          CBL_DOC, "KL.cbldoc",
          AADHAAR_COPY, "KL.aadhaar");

  public String uploadDocument(UUID id, String type, MultipartFile file) {
    PartnerDetail partner =
        partnerDetailRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Partner not found"));

    String key = type.toLowerCase();
    if (!FILE_PREFIX_MAP.containsKey(key)) {
      throw new EntityNotFoundException("Invalid document type: " + type);
    }

    try {
      String extension = getExtension(file.getOriginalFilename());
      String fileName = FILE_PREFIX_MAP.get(key) + "." + id + "." + extension;

      Path uploadPath = Paths.get(UPLOAD_DIR, fileName);
      Files.createDirectories(uploadPath.getParent());
      Files.write(uploadPath, file.getBytes());

      setFieldValue(partner, key, fileName);
      partnerDetailRepository.save(partner);

      return fileName;
    } catch (IOException e) {
      throw new IllegalStateException("File upload failed: " + e.getMessage(), e);
    }
  }

  public String deleteDocument(UUID id, String type) {
    PartnerDetail partner =
        partnerDetailRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Partner not found"));

    String key = type.toLowerCase();
    String fileName = getFieldValue(partner, key);

    if (fileName == null) {
      throw new EntityNotFoundException("No file found for type: " + type);
    }

    try {
      Path filePath = Paths.get(UPLOAD_DIR, fileName);
      Files.deleteIfExists(filePath);
      setFieldValue(partner, key, null);
      partnerDetailRepository.save(partner);
    } catch (IOException e) {
      throw new IllegalStateException("File deletion failed: " + e.getMessage(), e);
    }
    return fileName;
  }

  private void setFieldValue(PartnerDetail partner, String type, String value) {
    switch (type) {
      case AGREEMENT_COPY -> partner.setAgreementCopy(value);
      case BANK_PASS_COPY -> partner.setBankPassCopy(value);
      case INCOME_TAX_PROOF -> partner.setIncomeTaxProof(value);
      case GSTIN_DOC -> partner.setGstinDoc(value);
      case CBL_DOC -> partner.setCblDoc(value);
      case AADHAAR_COPY -> partner.setAadhaarCopy(value);
      default -> throw new IllegalArgumentException("Unsupported document type: " + type);
    }
  }

  private String getFieldValue(PartnerDetail partner, String type) {
    return switch (type) {
      case AGREEMENT_COPY -> partner.getAgreementCopy();
      case BANK_PASS_COPY -> partner.getBankPassCopy();
      case INCOME_TAX_PROOF -> partner.getIncomeTaxProof();
      case GSTIN_DOC -> partner.getGstinDoc();
      case CBL_DOC -> partner.getCblDoc();
      case AADHAAR_COPY -> partner.getAadhaarCopy();
      default -> null;
    };
  }

  private String getExtension(String filename) {
    if (filename == null || !filename.contains(".")) {
      return "png";
    }
    return filename.substring(filename.lastIndexOf('.') + 1);
  }
}
