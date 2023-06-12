package lt.kitm.neimdb.controller;

import lt.kitm.neimdb.entity.Komentaras;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KomentarasController {
    @PostMapping("/komentaras/prideti")
    public String pridetiKomentara(@ModelAttribute Komentaras komentaras, RedirectAttributes redirectAttributes) {
        System.out.println(komentaras);
        return "filmai";
    }
}
