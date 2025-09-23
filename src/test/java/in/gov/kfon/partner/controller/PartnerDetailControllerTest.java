package in.gov.kfon.partner.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.gov.kfon.partner.contract.PartnerDetailRequest;
import in.gov.kfon.partner.contract.PartnerDetailResponse;
import in.gov.kfon.partner.service.PartnerDetailService;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PartnerDetailController.class})
@ExtendWith(SpringExtension.class)
class PartnerDetailControllerTest {

  private MockMvc mockMvc;

  @MockBean private PartnerDetailService service;

  @Autowired private PartnerDetailController controller;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void testSave() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    var request = PartnerDetailRequest.builder().popName("Test Partner").build();

    var response =
        PartnerDetailResponse.builder().id(UUID.randomUUID()).popName("Test Partner").build();

    Mockito.when(service.save(any(PartnerDetailRequest.class))).thenReturn(response);

    mockMvc
        .perform(
            post("/api/partner-detail/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.popName").value("Test Partner"))
        .andExpect(jsonPath("$.message").value("Partner detail saved successfully"));
  }

  @Test
  void testUpdate() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    UUID id = UUID.randomUUID();

    var request = PartnerDetailRequest.builder().popName("Updated Partner").build();

    var response = PartnerDetailResponse.builder().id(id).popName("Updated Partner").build();

    Mockito.when(service.update(eq(id), any(PartnerDetailRequest.class))).thenReturn(response);

    mockMvc
        .perform(
            put("/api/partner-detail/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.popName").value("Updated Partner"))
        .andExpect(jsonPath("$.message").value("Partner detail updated successfully"));
  }

  @Test
  void testGetById() throws Exception {
    UUID id = UUID.randomUUID();

    var response = PartnerDetailResponse.builder().id(id).popName("Fetched Partner").build();

    Mockito.when(service.getById(id)).thenReturn(response);

    mockMvc
        .perform(get("/api/partner-detail/fetch/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.popName").value("Fetched Partner"))
        .andExpect(jsonPath("$.message").value("Partner detail fetched successfully"));
  }

  @Test
  void testGetAll() throws Exception {
    var resp1 = PartnerDetailResponse.builder().id(UUID.randomUUID()).popName("Partner A").build();

    var resp2 = PartnerDetailResponse.builder().id(UUID.randomUUID()).popName("Partner B").build();

    Mockito.when(service.getAll()).thenReturn(List.of(resp1, resp2));

    mockMvc
        .perform(get("/api/partner-detail/fetch-all"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data[0].popName").value("Partner A"))
        .andExpect(jsonPath("$.data[1].popName").value("Partner B"))
        .andExpect(jsonPath("$.message").value("All partner details fetched successfully"));
  }
}
