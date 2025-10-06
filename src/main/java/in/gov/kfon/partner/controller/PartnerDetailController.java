package in.gov.kfon.partner.controller;

import in.gov.kfon.partner.contract.PartnerDetailRequest;
import in.gov.kfon.partner.contract.PartnerDetailResponse;
import in.gov.kfon.partner.contract.Response;
import in.gov.kfon.partner.service.PartnerDetailService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner-detail")
@RequiredArgsConstructor
public class PartnerDetailController {

  private final PartnerDetailService service;

  @PostMapping("/save")
  public ResponseEntity<Response<PartnerDetailResponse>> save(
      @RequestBody PartnerDetailRequest request) {
    var response = service.save(request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(Response.created(response, "Partner detail saved successfully"));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Response<PartnerDetailResponse>> update(
      @PathVariable UUID id, @RequestBody PartnerDetailRequest request) {
    var response = service.update(id, request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(Response.ok(response, "Partner detail updated successfully"));
  }

  @GetMapping("/fetch/{id}")
  public ResponseEntity<Response<PartnerDetailResponse>> getById(@PathVariable UUID id) {
    var response = service.getById(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(Response.ok(response, "Partner detail fetched successfully"));
  }

  @GetMapping("/fetch-all")
  public ResponseEntity<Response<List<PartnerDetailResponse>>> getAll() {
    var response = service.getAll();
    return ResponseEntity.status(HttpStatus.OK)
        .body(Response.ok(response, "All partner details fetched successfully"));
  }
}
