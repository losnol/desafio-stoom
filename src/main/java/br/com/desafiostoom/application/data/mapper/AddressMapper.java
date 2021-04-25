package br.com.desafiostoom.application.data.mapper;

import br.com.desafiostoom.application.data.dto.AddressDTO;
import br.com.desafiostoom.application.data.dto.AddressDTOResponse;
import br.com.desafiostoom.application.data.entity.Address;

public final class AddressMapper {

    private AddressMapper() {
    }

    public static Address fromDTO(AddressDTO dto) {
        return new Address(null, dto.getStreetName(), dto.getNumber(), dto.getComplement(), dto.getNeighbourhood(), dto.getCity(), dto.getState(), dto.getCountry(), dto.getZipcode(), dto.getLatitude(), dto.getLatitude());
    }

    public static AddressDTO toDTO(Address address) {
        return new AddressDTO(address.getStreetName(), address.getNumber(), address.getComplement(), address.getNeighbourhood(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode(), address.getLatitude(), address.getLatitude());
    }

    public static AddressDTOResponse toResponseDTO(Address address) {
        return new AddressDTOResponse(address.getStreetName(), address.getNumber(), address.getComplement(), address.getNeighbourhood(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode(), address.getLatitude(), address.getLatitude(), address.getId());
    }
}
