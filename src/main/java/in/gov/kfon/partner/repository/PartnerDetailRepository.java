package in.gov.kfon.partner.repository;

import in.gov.kfon.partner.model.PartnerDetail;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerDetailRepository extends JpaRepository<PartnerDetail, UUID> {}
