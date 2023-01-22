package cz.mendelu.ja.leteckaposta.parcel;

import cz.mendelu.ja.leteckaposta.country.City;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    private UUID id;

    private String destination;

    private String location;

    private double weight;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastModified;

    // package protected constructor
    protected Parcel() {}
}
