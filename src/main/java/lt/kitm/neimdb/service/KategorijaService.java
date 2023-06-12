package lt.kitm.neimdb.service;

import lt.kitm.neimdb.entity.Kategorija;
import lt.kitm.neimdb.repository.KategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KategorijaService {
    @Autowired
    private KategorijaRepository kategorijaRepository;

    public List<Kategorija> visosKategorijos() {
        return new ArrayList<>(this.kategorijaRepository.findAll());
    }

    public void pridetiKategorija(Kategorija kategorija) {
        this.kategorijaRepository.save(kategorija);
    }

    /**
     * Tikrina ar tokiu pavadinimu kategorija jau egzistuoja
     * @param kategorija kategorijos objektas, kurios pavadinimas tikrinamas
     * @return true - kategorija tokiu pavadinimu jau egzistuoja
     */
    public boolean kategorijaEgzistuoja(Kategorija kategorija) {
        return this.kategorijaRepository.existsKategorijaByPavadinimas(kategorija.getPavadinimas());
    }

    /**
     * Ieško kategorijos pagal id
     * @param id kategorijos id, kurios ieškoti
     * @return rastos kategorijos objektas
     */
    public Kategorija rastiKategorija(long id) {
        return this.kategorijaRepository.findKategorijaById(id);
    }

    public void atnaujintiKategorija(Kategorija kategorija) {
        this.pridetiKategorija(kategorija);
    }

    public void trintiKategorija(long id) {
        this.kategorijaRepository.deleteById(id);
    }
}
