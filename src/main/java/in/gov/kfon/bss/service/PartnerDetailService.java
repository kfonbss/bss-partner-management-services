package in.gov.kfon.bss.service;
import in.gov.kfon.bss.dto.PartnerDetailRequest;
import in.gov.kfon.bss.dto.PartnerDetailResponse;
import in.gov.kfon.bss.model.PartnerDetail;
import in.gov.kfon.bss.repository.PartnerDetailRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerDetailService {

    private final PartnerDetailRepository repository;
    private final ModelMapper modelMapper;

    public PartnerDetailResponse save(PartnerDetailRequest request) {
        PartnerDetail entity = modelMapper.map(request, PartnerDetail.class);
        PartnerDetail saved = repository.save(entity);
        return modelMapper.map(saved, PartnerDetailResponse.class);
    }

    public PartnerDetailResponse update(Long id, PartnerDetailRequest request) {
        PartnerDetail existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PartnerDetail not found"));
        modelMapper.map(request, existing);
        PartnerDetail updated = repository.save(existing);
        return modelMapper.map(updated, PartnerDetailResponse.class);
    }

    public PartnerDetailResponse getById(Long id) {
        PartnerDetail entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PartnerDetail not found"));
        return modelMapper.map(entity, PartnerDetailResponse.class);
    }
    public List<PartnerDetailResponse> getAll() {
        List<PartnerDetail> entities = repository.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, PartnerDetailResponse.class))
                .collect(Collectors.toList());
    }
}
