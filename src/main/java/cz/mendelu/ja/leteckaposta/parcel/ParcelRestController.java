package cz.mendelu.ja.leteckaposta.parcel;

import cz.mendelu.ja.leteckaposta.utils.Response;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(path = "/api/parcel")
@RequiredArgsConstructor
class ParcelRestController {

    private final ParcelRepository parcelRepository;
    private final ParcelService parcelService;

    @Data
    private static class RequestCreateParcel {
        @NotBlank
        private String location;
        @NotNull
        private String destination;
        @Min(value = 0, message = "")
        private double weight;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    Response<Parcel> createParcel(
            @Valid @RequestBody RequestCreateParcel request,
            Principal principal
    ) {
        log.info("Principal: {}", principal);
        var parcel = parcelService.createParcel(request.location, request.destination, request.weight);
        return Response.of(parcel);
    }

}
