package lt.kitm.neimdb.repository;

import lt.kitm.neimdb.entity.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {
    boolean existsKategorijaByPavadinimas(String pavadinimas);
    Kategorija findKategorijaById(long id);
}
