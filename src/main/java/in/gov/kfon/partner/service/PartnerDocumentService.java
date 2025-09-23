package in.gov.kfon.partner.service;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface PartnerDocumentService {
  String uploadDocument(UUID id, String type, MultipartFile file);

  String deleteDocument(UUID id, String type);
}
