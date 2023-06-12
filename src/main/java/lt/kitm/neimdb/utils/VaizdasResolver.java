package lt.kitm.neimdb.utils;

public class VaizdasResolver {
    public static String vaizdasVartotojaiResolver(String vaizdas) {
        if (vaizdas.equals("vartotojai")) {
            return "redirect:/vartotojai";
        } else {
            return "redirect:/" + vaizdas;
        }
    }
}
