package br.com.desafiostoom.application.controller.rest;

import br.com.desafiostoom.application.data.dto.AddressDTO;
import br.com.desafiostoom.application.data.dto.AddressDTOResponse;
import br.com.desafiostoom.application.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressRestController {

    private final AddressService service;

    public AddressRestController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/")
    public AddressDTOResponse create(@RequestBody AddressDTO req) {
        return this.service.create(req);
    }

    @PutMapping("/{id}")
    public AddressDTOResponse update(@PathVariable("id") Long id, @RequestBody AddressDTO req) {
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
