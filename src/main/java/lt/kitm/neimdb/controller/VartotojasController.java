package lt.kitm.neimdb.controller;

import jakarta.validation.Valid;
import lt.kitm.neimdb.entity.Vartotojas;
import lt.kitm.neimdb.service.VartotojasService;
import lt.kitm.neimdb.utils.VaizdasResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VartotojasController {
    @Autowired
    private VartotojasService vartotojasService;

    @GetMapping("/registracija")
    public String registracija(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            Vartotojas vartotojas = new Vartotojas();
            model.addAttribute("vartotojas", vartotojas);
            return "registracija";
        } else {
            return "redirect:/pagrindinis";
        }

    }

    @PostMapping("/registracija")
    public String registracija(@Valid @ModelAttribute("vartotojas") Vartotojas vartotojas, BindingResult result, Model model) {
        Vartotojas esamasVartotojas = this.vartotojasService.rastiVartotojaPagalEmail(vartotojas.getEmail());
        if (esamasVartotojas != null) {
            result.rejectValue("email", null, "Vartotojas su tokiu el. pašto adresu jau egzistuoja");
        }
        if (result.hasErrors()) {
            model.addAttribute("vartotojas", vartotojas);
            return "registracija";
        }
        this.vartotojasService.irasytiVartotoja(vartotojas);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/vartotojai")
    public String vartotojai(Model model) {
        model.addAttribute("visi_vartotojai", this.vartotojasService.visiVartotojai());
        model.addAttribute("vaizdas", "vartotojai");
        return "vartotojai";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/vartotojas/{id}")
    public String vartotojas(@PathVariable long id, Model model) {
        model.addAttribute("vartotojas", this.vartotojasService.rastiVartotojaPagalId(id));
        model.addAttribute("vaizdas", "vartotojas/" + id);
        return "vartotojas";
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/vartotojas/uzblokuoti")
    public String uzblokuotiVartotoja(long id, String vaizdas) {
        Vartotojas vartotojas = this.vartotojasService.rastiVartotojaPagalId(id);
        vartotojas.setAktyvus(false);
        vartotojasService.atnaujintiVartotoja(vartotojas);
        return VaizdasResolver.vaizdasVartotojaiResolver(vaizdas);
    }

    @PreAuthorize("hasRole('ADMINISTRATORIUS')")
    @GetMapping("/vartotojas/atblokuoti")
    public String atblokuotiVartotoja(long id, String vaizdas) {
        Vartotojas vartotojas = this.vartotojasService.rastiVartotojaPagalId(id);
        vartotojas.setAktyvus(true);
        vartotojasService.atnaujintiVartotoja(vartotojas);
        return VaizdasResolver.vaizdasVartotojaiResolver(vaizdas);
    }
}
