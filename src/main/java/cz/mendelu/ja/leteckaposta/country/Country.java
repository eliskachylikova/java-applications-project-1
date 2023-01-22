package cz.mendelu.ja.leteckaposta.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "countries")
class Country {

    @Id
    @Column(length = 3)
    private String cca3;

    @Embedded
    private City capital;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "boarders",
            joinColumns = { @JoinColumn(name = "country_cca3") },
            inverseJoinColumns = { @JoinColumn(name = "neighbour_cca3") }
    )
    @JsonIgnore
    private Collection<Country> borders;



}
