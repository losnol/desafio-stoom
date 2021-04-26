package br.com.desafiostoom.application.controller.rest;

import br.com.desafiostoom.application.data.dto.AddressDTO;
import br.com.desafiostoom.application.data.dto.AddressDTOResponse;
import br.com.desafiostoom.application.service.AddressService;
import com.google.maps.errors.ApiException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressRestController {

    private final AddressService service;

    public AddressRestController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/")
    public AddressDTOResponse create(@RequestBody AddressDTO req) throws IOException, InterruptedException, ApiException {
        return this.service.create(req);
    }

    @PutMapping("/{id}")
    public AddressDTOResponse update(@PathVariable("id") Long id, @RequestBody AddressDTO req) throws IOException, InterruptedException, ApiException {
        return this.service.update(req, id);
    }

    @GetMapping("/")
    public List<AddressDTOResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public AddressDTOResponse get(@PathVariable("id") Long id) {
        return this.service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }

}
