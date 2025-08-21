package in.gov.kfon.bss.service;

import in.gov.kfon.bss.model.PartnerDetail;
import in.gov.kfon.bss.repository.PartnerDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PartnerDocumentService {

    private final PartnerDetailRepository partnerDetailRepository;

    private static final String UPLOAD_DIR = "/var/www/html/uploads/";

    private static final Map<String, String> FILE_PREFIX_MAP = Map.of(
            "agreementcopy", "KL.agree",
            "bankpasscopy", "KL.bankpass",
            "incometaxproof", "KL.pancard",
            "gstindoc", "KL.gstindoc",
            "cbldoc", "KL.cbldoc",
            "aadhaarcopy", "KL.aadhaar"
    );

    public String uploadDocument(Long partnerId, String type, MultipartFile file) {
        PartnerDetail partner = partnerDetailRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        String key = type.toLowerCase();
        if (!FILE_PREFIX_MAP.containsKey(key)) {
            throw new RuntimeException("Invalid document type: " + type);
        }

        try {
            String extension = getExtension(file.getOriginalFilename());
            String fileName = FILE_PREFIX_MAP.get(key) + "." + partnerId + "." + extension;

            Path uploadPath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(uploadPath.getParent());
            Files.write(uploadPath, file.getBytes());

            setFieldValue(partner, key, fileName);
            partnerDetailRepository.save(partner);

            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        }
    }

    public void deleteDocument(Long partnerId, String type) {
        PartnerDetail partner = partnerDetailRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        String key = type.toLowerCase();
        String fileName = getFieldValue(partner, key);

        if (fileName == null) {
            throw new RuntimeException("No file found for type: " + type);
        }

        try {
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.deleteIfExists(filePath);
            setFieldValue(partner, key, null);
            partnerDetailRepository.save(partner);
        } catch (Exception e) {
            throw new RuntimeException("File deletion failed: " + e.getMessage(), e);
        }
    }

    private void setFieldValue(PartnerDetail partner, String type, String value) {
        switch (type) {
            case "agreementcopy": partner.setAgreementcopy(value); break;
            case "bankpasscopy": partner.setBankpasscopy(value); break;
            case "incometaxproof": partner.setIncometaxproof(value); break;
            case "gstindoc": partner.setGstindoc(value); break;
            case "cbldoc": partner.setCbldoc(value); break;
            case "aadhaarcopy": partner.setAadhaarcopy(value); break;
        }
    }

    private String getFieldValue(PartnerDetail partner, String type) {
        switch (type) {
            case "agreementcopy": return partner.getAgreementcopy();
            case "bankpasscopy": return partner.getBankpasscopy();
            case "incometaxproof": return partner.getIncometaxproof();
            case "gstindoc": return partner.getGstindoc();
            case "cbldoc": return partner.getCbldoc();
            case "aadhaarcopy": return partner.getAadhaarcopy();
            default: return null;
        }
    }

    private String getExtension(String filename) {
        return (filename != null && filename.contains(".")) ?
                filename.substring(filename.lastIndexOf('.') + 1) : "png";
    }
}