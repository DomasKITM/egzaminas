package lt.kitm.neimdb.service;

import lt.kitm.neimdb.entity.Komentaras;
import lt.kitm.neimdb.repository.KomentarasRepository;

public class KomentarasService {
    private KomentarasRepository komentarasRepository;

    public void pridetiKomentara(Komentaras komentaras) {
        this.komentarasRepository.save(komentaras);
    }
}
