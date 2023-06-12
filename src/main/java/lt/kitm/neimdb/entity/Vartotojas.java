package lt.kitm.neimdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "vartotojai")
public class Vartotojas {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String vardas;
    @Column(nullable = false)
    private String pavarde;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String slaptazodis;
    @Column(nullable = false)
    private boolean aktyvus; // Kaip kitaip išvesti veikiantis? įjungtas?

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="vartotoju_tipai",
            joinColumns={@JoinColumn(name="vartotojo_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tipo_id", referencedColumnName = "id")})
    private List<VartotojoTipas> vartotojoTipai = new ArrayList<>();
}
