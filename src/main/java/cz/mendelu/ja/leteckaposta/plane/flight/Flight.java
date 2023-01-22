package cz.mendelu.ja.leteckaposta.plane.flight;

import cz.mendelu.ja.leteckaposta.country.City;
import cz.mendelu.ja.leteckaposta.parcel.Parcel;
import cz.mendelu.ja.leteckaposta.plane.Plane;
import lombok.Data;

import java.util.Collection;

@Data
public class Flight {
    
    private final Plane plane;
    private final String number;
    private final City from;
    private final City destination;
    private final Collection<Parcel> parcels;
}
