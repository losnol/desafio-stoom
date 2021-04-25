package br.com.desafiostoom.application.data.repository;


import br.com.desafiostoom.application.data.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
