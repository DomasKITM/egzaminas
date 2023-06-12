package lt.kitm.neimdb.service;

import lt.kitm.neimdb.entity.Filmas;
import lt.kitm.neimdb.repository.FilmaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmaiService {
    @Autowired
    private FilmaiRepository filmaiRepository;

    public List<Filmas> visiFilmai() {
        List<Filmas> tt = this.filmaiRepository.findAll();
        System.out.println(tt);
        return new ArrayList<>(tt);
    }

    public void pridetiFilma(Filmas filmas) {
        if (filmas.getAprasas().length() > 500) {
            filmas.setAprasas(filmas.getAprasas().substring(0, 500));
        }
        this.filmaiRepository.save(filmas);
    }

    public Filmas rastiFilma(long id) {
        return this.filmaiRepository.findFilmasById(id);
    }

    public void istrintiFilma(long id) {
        this.filmaiRepository.deleteById(id);
    }

    /**
     * Atnaujina knygą
     * Iš tikro naudojama ta pati funkcija kaip ir pridėdant naują knygą.
     * @param filmas knyga, kurią reikia atnaujinti
     */
    public void atnaujintiFilma(Filmas filmas) {
        this.pridetiFilma(filmas);
    }

    /**
     * Ieško filmo pavadinime pagal duotą užklausą. Ignoruojo didžiasas raides.
     * @param uzklausa užklausą, pagal kurią ieškoti knygos
     * @return filmų sarašas, kuriu pavadinimas atitinka užklausą
     */
    public List<Filmas> ieskotiFilmo(String uzklausa) {
        return this.filmaiRepository.findAllByPavadinimasContainingIgnoreCase(uzklausa);
    }
}
