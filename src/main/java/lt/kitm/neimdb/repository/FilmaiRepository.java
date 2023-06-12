package lt.kitm.neimdb.repository;

import lt.kitm.neimdb.entity.Filmas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmaiRepository extends JpaRepository<Filmas, Long> {
    Filmas findFilmasById(long id);
    List<Filmas> findAllByPavadinimasContainingIgnoreCase(String uzklausa);
}
