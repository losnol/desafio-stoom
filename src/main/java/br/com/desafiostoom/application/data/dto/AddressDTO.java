package br.com.desafiostoom.application.data.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class AddressDTO {

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

    public AddressDTO() {
    }

    public AddressDTO(String streetName, Integer number, String complement, String neighbourhood, String city, String state, String country, String zipcode, BigDecimal latitude, BigDecimal longitude) {
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
        return country;
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
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AddressDTO)) return false;
        var other = (AddressDTO) obj;
        return Objects.equals(this.streetName, other.streetName) && Objects.equals(this.number, other.number) && Objects.equals(this.complement, other.complement) && Objects.equals(this.neighbourhood, other.neighbourhood) && Objects.equals(this.city, other.city) && Objects.equals(this.state, other.state) && Objects.equals(this.country, other.country) && Objects.equals(this.zipcode, other.zipcode) && Objects.equals(this.latitude, other.latitude) && Objects.equals(this.longitude, other.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.streetName, this.number, this.complement, this.neighbourhood, this.city, this.state, this.country, this.zipcode, this.latitude, this.longitude);
    }

}
