package in.gov.kfon.bss.controller;
import in.gov.kfon.bss.dto.Response;
import in.gov.kfon.bss.service.PartnerDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/partner/document")
@RequiredArgsConstructor
public class PartnerDocumentController {

    private final PartnerDocumentService documentService;

    @PostMapping("/upload/{partnerId}")
    public ResponseEntity<Response<String>> uploadDocument(
            @PathVariable Long partnerId,
            @RequestParam("type") String type,
            @RequestParam("file") MultipartFile file) {

        String fileName = documentService.uploadDocument(partnerId, type, file);
        return ResponseEntity.ok(new Response<>(fileName, "File uploaded successfully"));
    }

    @DeleteMapping("/delete/{partnerId}")
    public ResponseEntity<Response<String>> deleteDocument(
            @PathVariable Long partnerId,
            @RequestParam("type") String type) {

        documentService.deleteDocument(partnerId, type);
        return ResponseEntity.ok(new Response<>(null, "Document deleted successfully"));
    }
}

