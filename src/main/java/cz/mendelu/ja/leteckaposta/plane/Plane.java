package cz.mendelu.ja.leteckaposta.plane;

import lombok.Data;

@Data
public class Plane {

    private final String code;
    private final double carryingCapacity;
    private final double flyDistance;

}
