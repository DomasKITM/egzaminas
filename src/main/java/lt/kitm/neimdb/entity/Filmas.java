package lt.kitm.neimdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "filmai")
@Entity
public class Filmas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String pavadinimas;
    @Column(nullable = false, length = 500)
    private String aprasas;
    @Column(nullable = false)
    private double reitingas;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // Jei nori padaryti, kad kategorijos nebūtų galima ištrinti kol yra priregistruota knyga pridėk: nullable = false
    @JoinColumn(name = "kategorija_id", referencedColumnName = "id")
    private Kategorija kategorija;
    @ManyToMany(fetch =  FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "filmu_komentarai",
    joinColumns = {@JoinColumn(name = "filmo_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "komentaro_id", referencedColumnName = "id")})
    private Set<Komentaras> filmoKomentarai;
}
