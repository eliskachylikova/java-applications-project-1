package cz.mendelu.ja.leteckaposta.parcel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Calculates the route through which the parcel will travel.
     * @param location country where the parcel currently is
     * @param destination country where the parcel wants to be
     * @return order of countries in which the parcel is going to travel
     */
    public ArrayList<String> getParcelRoute(String location, String destination) {
        return parcelRouter.route(location, destination);
    }

    /**
     * Calculates the path through which all parcels that are currently in the database will travel.
     * @return list of routes = the order of countries through which each package will travel
     */
    public List<ArrayList<String>> getAllRoutes() {
        var parcels = this.getAll();
        return parcels
                .stream()
                .map(p -> this.getParcelRoute(p.getLocation(), p.getDestination()))
                .toList();
    }

    /**
     * Register new customer order as {@link Parcel} in the system.
     *
     * @param location where the parcel is
     * @param destination where the parcel wants to go
     * @param weight of the parcel in kg
     * @return created parcel
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

    /**
     * Inserts into the database the list of parcels that are provided in a csv file.
     * @param csvFile with location, destination and weight of parcels
     * @param jdbcURL database url
     * @param dbUser database username
     * @param dbPassword database password
     */
    public void importParcelsCSV(String csvFile, String jdbcURL, String dbUser, String dbPassword) {
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO parcels(id, created, destination, last_modified, location, weight) VALUES (?,?,?,?,?,?)")) {

            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pstmt.setObject(1, UUID.randomUUID());
                pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                pstmt.setString(3, values[0]);
                pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                pstmt.setString(5, values[1]);
                pstmt.setDouble(6, Double.parseDouble(values[2]));
                pstmt.executeUpdate();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all parcels from database.
     * @return list of parcels
     */
    public List<Parcel> getAll() {
        return (List<Parcel>) parcelRepository.findAll();
    }


}
