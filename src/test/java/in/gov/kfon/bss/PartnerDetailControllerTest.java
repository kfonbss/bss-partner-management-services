package in.gov.kfon.bss;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.gov.kfon.bss.controller.PartnerDetailController;
import in.gov.kfon.bss.dto.PartnerDetailRequest;
import in.gov.kfon.bss.dto.PartnerDetailResponse;
import in.gov.kfon.bss.service.PartnerDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartnerDetailController.class)
public class PartnerDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartnerDetailService service;

    @Autowired
    private ObjectMapper objectMapper;

    private PartnerDetailRequest request;
    private PartnerDetailResponse response;

    @BeforeEach
    void setup() {
        request = PartnerDetailRequest.builder()
                .partnerid(1L)
                .partnercompanyname("Albetech")
                .state("Kerala")
                .pin(682001)
                .build();

        response = PartnerDetailResponse.builder()
                .partnerid(1L)
                .partnercompanyname("Albetech")
                .state("Kerala")
                .pin(682001)
                .build();
    }

    @Test
    void testSavePartnerDetail() throws Exception {
        when(service.save(request)).thenReturn(response);

        mockMvc.perform(post("/api/partner-detail/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.partnerid").value(1L));
    }

    @Test
    void testUpdatePartnerDetail() throws Exception {
        Long partnerId = 1L;
        when(service.updateById(partnerId, request)).thenReturn(response);

        mockMvc.perform(put("/api/partner-detail/update/" + partnerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.partnercompanyname").value("Albetech"));
    }

    @Test
    void testFetchById() throws Exception {
        Long partnerId = 1L;
        when(service.getById(partnerId)).thenReturn(response);

        mockMvc.perform(get("/api/partner-detail/fetch/" + partnerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.partnercompanyname").value("Albetech"));
    }

    @Test
    void testFetchAll() throws Exception {
        when(service.getAll()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/api/partner-detail/fetch-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload[0].partnercompanyname").value("Albetech"));
    }
}