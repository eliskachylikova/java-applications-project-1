package cz.mendelu.ja.leteckaposta.parcel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final ParcelRouter parcelRouter;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, ParcelRouter parcelRouter) {
        this.parcelRepository = parcelRepository;
        this.parcelRouter = parcelRouter;
    }

    public ArrayList<String> getParcelRoute(String location, String destination) {
        ArrayList<String> route = parcelRouter.route(location, destination);
        System.out.println(route);
        return route;
    }

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
