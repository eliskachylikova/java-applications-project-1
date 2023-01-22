package cz.mendelu.ja.leteckaposta.parcel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;

    // servicam psat dokumentaci
    // nepouzivat slova ktera jsou v nazvu te metody a napsat k cemu to je, ne co to dela a jak
    /**
     * Register new customer order as {@link Parcel} in the system.
     *
     * @param location todo
     * @param destination todo
     * @param weight todo
     * @return todo
     */
    @Transactional
    Parcel createParcel(
            String location,
            String destination,
            double weight) { // v nasem pripade nevhodne pouzit RequestCreatedParcel
        var parcel = new Parcel();
        parcel.setId(UUID.randomUUID());
        parcel.setDestination(destination);
        parcel.setLocation(location);
        parcel.setWeight(weight);
        return parcelRepository.save(parcel); // create - protoze tam neni id, kdyby tam bylo id, je to update
    }

}
