package lt.kitm.neimdb.service;

import lt.kitm.neimdb.entity.Vartotojas;
import lt.kitm.neimdb.entity.VartotojoTipas;
import lt.kitm.neimdb.repository.VartotojasRepository;
import lt.kitm.neimdb.repository.VartotojoTipasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VartotojasService {
    @Autowired
    private VartotojasRepository vartotojasRepository;
    @Autowired
    private VartotojoTipasRepository vartotojoTipasRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Įrašo vartotoją į DB. Prieš tai užkoduoja perduotą vartojo slaptažodį
     * @param vartotojas Vartotojo objektas, kurį reikia įrašyti į DB
     */
    public void irasytiVartotoja(Vartotojas vartotojas) {
        vartotojas.setSlaptazodis(passwordEncoder.encode(vartotojas.getSlaptazodis()));
        VartotojoTipas vartotojoTipas = vartotojoTipasRepository.findByPavadinimas("ROLE_SKAITYTOJAS");
        vartotojas.setVartotojoTipai(Arrays.asList(new VartotojoTipas[]{vartotojoTipas}));
        vartotojas.setAktyvus(true);
        this.vartotojasRepository.save(vartotojas);
    }

    /**
     * Suranda vartotoją pagal email adresą
     * @param email vartotojo email adresas
     * @return grąžina vartotojo objektą, jei toks vartotojas yra duomenų bazėje, null - jei vartotojas nebuvo rastas
     */
    public Vartotojas rastiVartotojaPagalEmail(String email) {
        return this.vartotojasRepository.findByEmail(email);
    }

    public Vartotojas rastiVartotojaPagalId(long id) {
        return this.vartotojasRepository.findById(id);
    }

    /**
     * Grąžina tik vartotojus, kurių tipas yra 'SKAITYTOJAS'
     * @return skaitytojų sąrašas
     */
    public List<Vartotojas> visiVartotojai() {
        return vartotojasRepository.findAllByVartotojoTipaiContains(vartotojoTipasRepository.findByPavadinimas("ROLE_SKAITYTOJAS"));
    }

    /**
     * Atnaujina vartotojo informaciją naudodamas perduotą objektą.
     * Objektas turi turėti ID su jau egzistuojančiu vartotoju.
     * Negalima naudoti irasytiVartotojo funkcijos, nes ji prideda papildomos informacijos, kurio keisti negalima
     * @param vartotojas vartotojo objektas su atnaujint informacija
     */
    public void atnaujintiVartotoja(Vartotojas vartotojas) {
        this.vartotojasRepository.save(vartotojas);
    }
}
