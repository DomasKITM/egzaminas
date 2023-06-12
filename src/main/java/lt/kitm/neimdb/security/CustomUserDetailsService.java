package lt.kitm.neimdb.security;

import lt.kitm.neimdb.entity.Vartotojas;
import lt.kitm.neimdb.entity.VartotojoTipas;
import lt.kitm.neimdb.repository.VartotojasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private VartotojasRepository vartotojasRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Vartotojas vartotojas = vartotojasRepository.findByEmail(email);
        if (vartotojas != null) {
            return new CustomUserDetails(vartotojas.getId(), vartotojas.getEmail(), vartotojas.getSlaptazodis(), vartotojas.getVardas(), vartotojas.getPavarde(), true, true, true, vartotojas.isAktyvus(), mapTipaiToAuthorities(vartotojas.getVartotojoTipai()));
        } else {
            throw new UsernameNotFoundException("Tokio klaidingas vartotojo vardas arba slaptažodis");
        }
    }

    private Collection<? extends GrantedAuthority> mapTipaiToAuthorities(Collection<VartotojoTipas> tipai) {
        Collection<? extends GrantedAuthority> mapTipai = tipai.stream()
                .map(tipas -> new SimpleGrantedAuthority(tipas.getPavadinimas()))
                .collect(Collectors.toList());
        return mapTipai;
    }
}
