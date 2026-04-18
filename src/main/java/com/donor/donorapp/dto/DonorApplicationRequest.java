package com.donor.donorapp.dto;

import com.donor.donorapp.models.DonorCategory;
import com.donor.donorapp.models.DonorStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DonorApplicationRequest {

    @NotNull(message = "Donor category is required.")
    private DonorCategory donorCategory;

    private DonorStatus donorStatus;

    @NotBlank(message = "Phone is required.")
    private String phone;

    @NotBlank(message = "address line 1 is required.")
    private String address1;
    @NotBlank(message = "address line 2 is required.")
    private String address2;
    @NotBlank(message = "City is required.")
    private String city;
    @NotBlank(message = "State is required.")
    private String state;
    @NotBlank(message = "Country is required.")
    private String country;
    @NotBlank(message = "Pincode is required.")
    private String postalCode;

    private LocalDate dateOfBirth;

    private String panNumber;

    private String organizationName;

    private String registrationNumber;

    private String website;

    @NotBlank(message = "Notes are required.")
    private String notes;

    public DonorApplicationRequest() {}

    public DonorCategory getDonorCategory() {
        return donorCategory;
    }

    public void setDonorCategory(DonorCategory donorCategory) {
        this.donorCategory = donorCategory;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
