package com.donor.donorapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "donors_applications",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "status"})
        }
        )
public class DonorApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "donor_category")
    private DonorCategory donorCategory;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status can't be null.")
    @Column(name = "status")
    private DonorStatus donorStatus;

    @Column(nullable = false)
    private String phone;

    @Column(name = "address_line1")
    private String address1;
    @Column(name = "address_line2")
    private String address2;

    private String city;
    private String state;
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String panNumber;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "registration_number")
    private String registrationNumber;

    private String website;

    @Column(columnDefinition = "Text")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    public DonorApplication(){}

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    public DonorCategory getDonorCategory() {
        return donorCategory;
    }

    public void setDonorCategory(DonorCategory donorCategory) {
        this.donorCategory = donorCategory;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public DonorStatus getDonorStatus(){
        return donorStatus;
    }

    public void setDonorStatus(DonorStatus donorStatus){
        this.donorStatus = donorStatus;
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

    public LocalDateTime getReviewedAt(){
        return reviewedAt;
    }

    public void setReviewedAt(LocalDateTime reviewedAt){
        this.reviewedAt = reviewedAt;
    }

}
