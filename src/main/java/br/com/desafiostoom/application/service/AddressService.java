package br.com.desafiostoom.application.service;

import br.com.desafiostoom.application.data.dto.AddressDTO;
import br.com.desafiostoom.application.data.dto.AddressDTOResponse;
import br.com.desafiostoom.application.data.entity.Address;
import br.com.desafiostoom.application.data.mapper.AddressMapper;
import br.com.desafiostoom.application.data.repository.AddressRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    //
    private final AddressRepository repository;
    private final GeoApiContext gMapsCtx;

    public AddressService(AddressRepository repository, GeoApiContext gMapsCtx) {
        this.repository = repository;
        this.gMapsCtx = gMapsCtx;
    }

    public AddressDTOResponse create(AddressDTO req) throws IOException, InterruptedException, ApiException {
        this.validateDTO(req);
        var address = AddressMapper.fromDTO(req);
        if (address.getLatitude() == null && address.getLongitude() == null)
            this.getAddressCoordinates(address);
        return AddressMapper.toResponseDTO(this.repository.save(address));
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public AddressDTOResponse update(AddressDTO req, Long id) throws IOException, InterruptedException, ApiException {
        this.validateDTO(req);
        var addressId = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address with requested id wasn't found"))
                .getId();
        var updatedAddress = AddressMapper.fromDTO(req);
        updatedAddress.setId(id);
        if (updatedAddress.getLatitude() == null && updatedAddress.getLongitude() == null)
            this.getAddressCoordinates(updatedAddress);
        return AddressMapper.toResponseDTO(this.repository.save(updatedAddress));
    }

    public AddressDTOResponse get(Long id) {
        return AddressMapper.toResponseDTO(this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address with requested id wasn't found"))
        );
    }

    public List<AddressDTOResponse> getAll() {
        List<AddressDTOResponse> result = new ArrayList<>();
        this.repository.findAll().forEach(addr -> result.add(AddressMapper.toResponseDTO(addr)));
        return result;
    }

    private void validateDTO(AddressDTO dto) {
        if (dto.getStreetName() == null || dto.getNumber() == null || dto.getNeighbourhood() == null || dto.getCity() == null || dto.getState() == null || dto.getCountry() == null || dto.getZipcode() == null)
            throw new IllegalArgumentException("Invalid request. Not all mandatory properties are present");
    }

    //GeocodingApi.geocode(ctx, "Rua Antônio Dias 162, Belo Horizonte, MG").await()[0].geometry.location
    private void getAddressCoordinates(Address addr) throws IOException, InterruptedException, ApiException {
        var mapsQueryString = new StringBuilder(addr.getStreetName())
                .append(" ")
                .append(addr.getNumber())
                .append(", ")
                .append(addr.getNeighbourhood())
                .append(", ")
                .append(addr.getCity())
                .append(", ")
                .append(addr.getState())
                .append(", ")
                .append(addr.getCountry())
                .toString();
        var result = GeocodingApi.geocode(this.gMapsCtx, mapsQueryString).await();
        if (result.length > 0) {
            LatLng coordinates = result[0].geometry.location;
            addr.setLatitude(new BigDecimal(coordinates.lat));
            addr.setLongitude(new BigDecimal(coordinates.lng));
        }
    }

}
