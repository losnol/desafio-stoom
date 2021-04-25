package br.com.desafiostoom.application.data.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class AddressDTOResponse extends AddressDTO {

    private Long id;

    public AddressDTOResponse(String streetName, Integer number, String complement, String neighbourhood, String city, String state, String country, String zipcode, BigDecimal latitude, BigDecimal longitude, Long id) {
        super(streetName, number, complement, neighbourhood, city, state, country, zipcode, latitude, longitude);
        this.id = id;
    }

    public AddressDTOResponse() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        var other = (AddressDTOResponse) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.id);
    }
}
