package br.com.desafiostoom.application.data.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Address {

    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String streetName;
    private Integer number;
    private String complement;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private BigDecimal latitude;
    private BigDecimal longitude;

    protected Address() {
    }

    public Address(Long id, String streetName, Integer number, String complement, String neighbourhood, String city, String state, String country, String zipcode, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Address of(Long id, String streetName, Integer number, String neighbourhood, String city, String state, String country, String zipCode) {
        return new Address(id, streetName, number, null, neighbourhood, city, state, country, zipCode, null, null);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return this.complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbourhood() {
        return this.neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;
        var other = (Address) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
