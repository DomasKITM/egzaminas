package lt.kitm.neimdb.controller;

import lt.kitm.neimdb.entity.Kategorija;
import lt.kitm.neimdb.entity.Filmas;
import lt.kitm.neimdb.service.KategorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class KategorijaController {
    @Autowired
    private KategorijaService kategorijaService;

    @GetMapping("/kategorijos")
    public String kategorijos(Model model) {
        model.addAttribute("kategorija", new Kategorija());
        model.addAttribute("visos_kategorijos", this.kategorijaService.visosKategorijos());
        return "kategorijos";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @PostMapping("/kategorijos/prideti")
    public String pridetiKategorija(@ModelAttribute Kategorija kategorija, RedirectAttributes redirectAttributes) {
        kategorija.setPavadinimas(kategorija.getPavadinimas().substring(0, 1).toUpperCase() + kategorija.getPavadinimas().substring(1).toLowerCase());
        if (this.kategorijaService.kategorijaEgzistuoja(kategorija)) {
            redirectAttributes.addFlashAttribute("zinute", String.format("Kategorija \"%1$s\" jau egzistuoja", kategorija.getPavadinimas()));
            redirectAttributes.addFlashAttribute("zinutesKlase", "neigiama-zinute");
            return "redirect:/kategorijos";
        } else {
            this.kategorijaService.pridetiKategorija(kategorija);
            redirectAttributes.addFlashAttribute("zinute", String.format("Kategorija \"%1$s\" sėkmingai pridėta", kategorija.getPavadinimas()));
            redirectAttributes.addFlashAttribute("zinutesKlase", "teigiama-zinute");
            return "redirect:/kategorijos";
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/kategorijos/redaguoti/{id}")
    public String redaguotiKategorija(@PathVariable long id, Model model) {
        Kategorija kategorija = this.kategorijaService.rastiKategorija(id);
        if (kategorija != null) {
            model.addAttribute("kategorija", kategorija);
            return "kategorija-redaguoti";
        } else {
            return "error/404";
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @PostMapping("/kategorijos/redaguoti")
    public String atnaujintiKategorijosDuomenis(Kategorija kategorija) {
        this.kategorijaService.atnaujintiKategorija(kategorija);
        return "redirect:/kategorijos";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/kategorijos/trinti")
    public String trintiKategorija(long id, RedirectAttributes redirectAttributes) {
        Kategorija kategorija = kategorijaService.rastiKategorija(id);
        for (Filmas filmas : kategorija.getFilmai()) {
            filmas.setKategorija(null);
        }
        this.kategorijaService.trintiKategorija(id);
        redirectAttributes.addFlashAttribute("zinute", String.format("Kategorija \"%1$s\" sėkmingai ištrinta", kategorija.getPavadinimas()));
        redirectAttributes.addFlashAttribute("zinutesKlase", "teigiama-zinute");
        return "redirect:/kategorijos";
    }

    /**
     * Parodo visas knygas iš kategorijos
     * @param id kategorijos id
     * @return knygos vaizdą su knygomis iš pasirinktos kategorijos
     */
    @GetMapping("/kategorija/{id}")
    public String rodytiKategorijosFilmai(@PathVariable long id, Model model, Principal principal) {
        Kategorija kategorija = this.kategorijaService.rastiKategorija(id);
        model.addAttribute("visi_filmai", kategorija.getFilmai());
        model.addAttribute("vaizdas", "kategorija/" + id);
        model.addAttribute("kategorijos_filmai", String.format("Kategorijos \"%1$s\" filmai:", kategorija.getPavadinimas()));
        return "filmai";
    }
}
