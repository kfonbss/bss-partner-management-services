package in.gov.kfon.partner.service;

import in.gov.kfon.partner.contract.PartnerDetailRequest;
import in.gov.kfon.partner.contract.PartnerDetailResponse;
import java.util.List;
import java.util.UUID;

public interface PartnerDetailService {
  PartnerDetailResponse save(PartnerDetailRequest request);

  PartnerDetailResponse update(UUID id, PartnerDetailRequest request);

  PartnerDetailResponse getById(UUID id);

  List<PartnerDetailResponse> getAll();
}
