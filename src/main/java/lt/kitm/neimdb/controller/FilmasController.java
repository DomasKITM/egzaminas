package lt.kitm.neimdb.controller;

import lt.kitm.neimdb.entity.Filmas;
import lt.kitm.neimdb.entity.Komentaras;
import lt.kitm.neimdb.service.KategorijaService;
import lt.kitm.neimdb.service.FilmaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class FilmasController {
    @Autowired
    private FilmaiService filmaiService;
    @Autowired
    private KategorijaService kategorijaService;

    @GetMapping("/filmai")
    public String filmai(Model model, Principal principal) {
        model.addAttribute("visi_filmai", this.filmaiService.visiFilmai());
        model.addAttribute("vaizdas", "filmai");
        return "filmai";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/filmai/prideti")
    public String pridetiFilmaGet(Model model) {
        model.addAttribute("filmas", new Filmas());
        model.addAttribute("visos_kategorijos", kategorijaService.visosKategorijos());
        return "filmas-prideti";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @PostMapping("/filmai/prideti")
    public String pridetiFilmaPost(@ModelAttribute Filmas filmas, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
                this.filmaiService.pridetiFilma(filmas);
                redirectAttributes.addFlashAttribute("zinute", "Filmas sėkmingai pridėta");
                redirectAttributes.addFlashAttribute("zinutesKlase", "teigiama-zinute");
                return "redirect:/filmai/prideti";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/filmai/redaguoti/{id}")
    public String redaguotiFilma(@PathVariable long id, Model model) {
        Filmas filmas = this.filmaiService.rastiFilma(id);
        if (filmas != null) {
            model.addAttribute("filmas", filmas);
            model.addAttribute("visos_kategorijos", kategorijaService.visosKategorijos());
            return "filmas-redaguoti";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/filmai/filmas/{id}")
    public String perziuretiFilma(@PathVariable long id, Model model) {
        Filmas filmas = this.filmaiService.rastiFilma(id);
        if (filmas != null) {
            model.addAttribute("filmas", filmas);
            model.addAttribute("komentaras_objektas", new Komentaras());
            return "filmas";
        } else {
            return "error/404";
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @PostMapping("/filmai/redaguoti")
    public String atnaujintiFilmoDuomenis(@ModelAttribute Filmas filmas, RedirectAttributes redirectAttributes) {
            this.filmaiService.pridetiFilma(filmas);
            return "redirect:/filmai";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/filmai/trinti")
    public String trintiFilma(long id, RedirectAttributes redirectAttributes) {
        Filmas filmas = this.filmaiService.rastiFilma(id);
            this.filmaiService.istrintiFilma(id);
            return "redirect:/filmai";
    }

    @RequestMapping("/filmai/paieska")
    public String filmoPaieska(@RequestParam(value = "uzklausa") String uzklausa, Model model) {
        model.addAttribute("paieskos_uzklausa", String.format("Paieškos \"%1$s\" rezultatai:", uzklausa));
        model.addAttribute("visi_filmai", this.filmaiService.ieskotiFilmo(uzklausa));
        return "filmai";
    }
}
