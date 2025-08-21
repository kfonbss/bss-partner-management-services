package in.gov.kfon.bss.controller;

import in.gov.kfon.bss.dto.PartnerDetailRequest;
import in.gov.kfon.bss.dto.PartnerDetailResponse;
import in.gov.kfon.bss.dto.Response;
import in.gov.kfon.bss.service.PartnerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner-detail")
@RequiredArgsConstructor
public class PartnerDetailController {

    private final PartnerDetailService service;

    @PostMapping("/save")
    public ResponseEntity<Response<PartnerDetailResponse>> save(@RequestBody PartnerDetailRequest request) {
        PartnerDetailResponse response = service.save(request);
        return ResponseEntity.ok(new Response<>(response, "Partner detail saved successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response<PartnerDetailResponse>> update(@PathVariable Long id, @RequestBody PartnerDetailRequest request) {
        PartnerDetailResponse response = service.update(id, request);
        return ResponseEntity.ok(new Response<>(response, "Partner detail updated successfully"));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Response<PartnerDetailResponse>> getById(@PathVariable Long id) {
        PartnerDetailResponse response = service.getById(id);
        return ResponseEntity.ok(new Response<>(response, "Partner detail fetched successfully"));
    }
    @GetMapping("/fetch-all")
    public ResponseEntity<Response<List<PartnerDetailResponse>>> getAll() {
        List<PartnerDetailResponse> responseList = service.getAll();
        return ResponseEntity.ok(new Response<>(responseList, "All partner details fetched successfully"));
    }
}