package lt.kitm.neimdb.repository;

import lt.kitm.neimdb.entity.Vartotojas;
import lt.kitm.neimdb.entity.VartotojoTipas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VartotojasRepository extends JpaRepository<Vartotojas, Long> {
    Vartotojas findByEmail(String email);
    Vartotojas findById(long id);
    List<Vartotojas> findAllByVartotojoTipaiContains(VartotojoTipas vartotojoTipas);
}
