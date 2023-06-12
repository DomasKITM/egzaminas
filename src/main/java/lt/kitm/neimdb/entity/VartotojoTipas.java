package lt.kitm.neimdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipas")
public class VartotojoTipas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String pavadinimas;
    @ManyToMany(mappedBy = "vartotojoTipai")
    private List<Vartotojas> vartotojai;
}
