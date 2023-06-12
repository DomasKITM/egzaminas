package lt.kitm.neimdb.repository;

import lt.kitm.neimdb.entity.VartotojoTipas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VartotojoTipasRepository extends JpaRepository<VartotojoTipas, Long> {
    VartotojoTipas findByPavadinimas(String pavadinimas);
}
