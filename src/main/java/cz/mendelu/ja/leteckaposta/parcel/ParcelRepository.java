package cz.mendelu.ja.leteckaposta.parcel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ParcelRepository extends CrudRepository<Parcel, UUID> {
}
