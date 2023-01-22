package cz.mendelu.ja.leteckaposta.parcel;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ParcelShellComponent {

    private final ParcelService parcelService;

    @ShellMethod("Insert parcels")
    void insertParcels() {
        parcelService.importParcelsCSV("parcels.csv","jdbc:postgresql://localhost:6543/postgres","postgres","secret");
    }

}
