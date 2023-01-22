package cz.mendelu.ja.leteckaposta.country;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class City {

    @Column(name = "capital")
    private String name;

    @Column(name = "capital_lat")
    private double lat;

    @Column(name = "capital_lng")
    private double lng;
}
