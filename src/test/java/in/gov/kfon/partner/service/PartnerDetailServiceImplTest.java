package in.gov.kfon.partner.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import in.gov.kfon.partner.contract.PartnerDetailRequest;
import in.gov.kfon.partner.contract.PartnerDetailResponse;
import in.gov.kfon.partner.model.PartnerDetail;
import in.gov.kfon.partner.repository.PartnerDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

class PartnerDetailServiceImplTest {

  @InjectMocks private PartnerDetailServiceImpl service;

  @Mock private PartnerDetailRepository repository;

  private final ModelMapper modelMapper = new ModelMapper();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    service = new PartnerDetailServiceImpl(repository, modelMapper);
  }

  @Test
  void testSave() {
    PartnerDetailRequest request = PartnerDetailRequest.builder().popName("Test Partner").build();

    PartnerDetail savedEntity =
        PartnerDetail.builder()
            .id(UUID.randomUUID())
            .popName("Test Partner")
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

    when(repository.save(any(PartnerDetail.class))).thenReturn(savedEntity);

    PartnerDetailResponse response = service.save(request);

    assertNotNull(response);
    assertEquals("Test Partner", response.getPopName());
    verify(repository, times(1)).save(any(PartnerDetail.class));
  }

  @Test
  void testUpdate() {
    UUID id = UUID.randomUUID();
    PartnerDetailRequest request =
        PartnerDetailRequest.builder().popName("Updated Partner").build();

    PartnerDetail existing = PartnerDetail.builder().id(id).popName("Old Partner").build();

    when(repository.findById(id)).thenReturn(Optional.of(existing));
    when(repository.save(any(PartnerDetail.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    PartnerDetailResponse response = service.update(id, request);

    assertNotNull(response);
    assertEquals("Updated Partner", response.getPopName());
    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(existing);
  }

  @Test
  void testUpdateNotFound() {
    UUID id = UUID.randomUUID();
    PartnerDetailRequest request = new PartnerDetailRequest();

    when(repository.findById(id)).thenReturn(Optional.empty());

    EntityNotFoundException exception =
        assertThrows(EntityNotFoundException.class, () -> service.update(id, request));

    assertTrue(exception.getMessage().contains("Id not found"));
    verify(repository, times(1)).findById(id);
    verify(repository, never()).save(any());
  }

  @Test
  void testGetById() {
    UUID id = UUID.randomUUID();
    PartnerDetail entity = PartnerDetail.builder().id(id).popName("Fetched Partner").build();

    when(repository.findById(id)).thenReturn(Optional.of(entity));

    PartnerDetailResponse response = service.getById(id);

    assertNotNull(response);
    assertEquals("Fetched Partner", response.getPopName());
    verify(repository, times(1)).findById(id);
  }

  @Test
  void testGetByIdNotFound() {
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getById(id));
    verify(repository, times(1)).findById(id);
  }

  @Test
  void testGetAll() {
    PartnerDetail entity1 =
        PartnerDetail.builder().id(UUID.randomUUID()).popName("Partner A").build();
    PartnerDetail entity2 =
        PartnerDetail.builder().id(UUID.randomUUID()).popName("Partner B").build();

    when(repository.findAll()).thenReturn(List.of(entity1, entity2));

    List<PartnerDetailResponse> responses = service.getAll();

    assertEquals(2, responses.size());
    assertEquals("Partner A", responses.get(0).getPopName());
    assertEquals("Partner B", responses.get(1).getPopName());
    verify(repository, times(1)).findAll();
  }
}
