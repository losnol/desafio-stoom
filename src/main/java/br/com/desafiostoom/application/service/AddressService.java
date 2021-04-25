package br.com.desafiostoom.application.service;

import br.com.desafiostoom.application.data.dto.AddressDTO;
import br.com.desafiostoom.application.data.dto.AddressDTOResponse;
import br.com.desafiostoom.application.data.mapper.AddressMapper;
import br.com.desafiostoom.application.data.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public AddressDTOResponse create(AddressDTO req) {
        this.validateDTO(req);
        var address = AddressMapper.fromDTO(req);
        return AddressMapper.toResponseDTO(this.repository.save(address));
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public AddressDTOResponse update(AddressDTO req, Long id) {
        this.validateDTO(req);
        var addressId = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address with requested id wasn't found"))
                .getId();
        var updatedAddress = AddressMapper.fromDTO(req);
        updatedAddress.setId(id);
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

}