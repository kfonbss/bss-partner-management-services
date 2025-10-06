package in.gov.kfon.partner.controller;

import in.gov.kfon.partner.contract.Response;
import in.gov.kfon.partner.service.PartnerDocumentService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/partner-document")
@RequiredArgsConstructor
public class PartnerDocumentController {

  private final PartnerDocumentService documentService;

  @PostMapping("/upload/{id}")
  public ResponseEntity<Response<String>> uploadDocument(
      @PathVariable UUID id,
      @RequestParam("type") String type,
      @RequestParam("file") MultipartFile file) {

    String fileName = documentService.uploadDocument(id, type, file);
    return ResponseEntity.ok(Response.ok(fileName, "File uploaded successfully"));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Response<String>> deleteDocument(
      @PathVariable UUID id, @RequestParam("type") String type) {

    String fileName = documentService.deleteDocument(id, type);
    return ResponseEntity.ok(Response.ok(fileName, "Document deleted successfully"));
  }
}
