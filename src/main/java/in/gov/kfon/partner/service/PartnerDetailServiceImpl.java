package in.gov.kfon.partner.service;

import in.gov.kfon.partner.contract.PartnerDetailRequest;
import in.gov.kfon.partner.contract.PartnerDetailResponse;
import in.gov.kfon.partner.model.PartnerDetail;
import in.gov.kfon.partner.repository.PartnerDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartnerDetailServiceImpl implements PartnerDetailService {

  private final PartnerDetailRepository repository;
  private final ModelMapper modelMapper;

  @Override
  public PartnerDetailResponse save(PartnerDetailRequest request) {
    PartnerDetail entity = modelMapper.map(request, PartnerDetail.class);
    entity.setCreateDate(LocalDateTime.now());
    entity.setUpdateDate(LocalDateTime.now());
    PartnerDetail saved = repository.save(entity);
    return modelMapper.map(saved, PartnerDetailResponse.class);
  }

  @Override
  public PartnerDetailResponse update(UUID id, PartnerDetailRequest request) {
    PartnerDetail existing =
        repository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
    modelMapper
        .typeMap(PartnerDetailRequest.class, PartnerDetail.class)
        .addMappings(m -> m.skip(PartnerDetail::setId));
    modelMapper.map(request, existing);

    existing.setUpdateDate(LocalDateTime.now());

    PartnerDetail updatedEntity = repository.save(existing);

    return modelMapper.map(updatedEntity, PartnerDetailResponse.class);
  }

  @Override
  @Transactional(readOnly = true)
  public PartnerDetailResponse getById(UUID id) {
    PartnerDetail details =
        repository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Id Not Found " + id));
    return modelMapper.map(details, PartnerDetailResponse.class);
  }

  @Override
  @Transactional(readOnly = true)
  public List<PartnerDetailResponse> getAll() {
    return repository.findAll().stream()
        .map(entity -> modelMapper.map(entity, PartnerDetailResponse.class))
        .toList();
  }
}
