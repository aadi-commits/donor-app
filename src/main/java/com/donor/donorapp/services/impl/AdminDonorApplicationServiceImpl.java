package com.donor.donorapp.services.impl;

import com.donor.donorapp.dto.ApiResponse;
import com.donor.donorapp.exceptions.BadRequestException;
import com.donor.donorapp.exceptions.ResourceNotFoundException;
import com.donor.donorapp.models.Donor;
import com.donor.donorapp.models.DonorApplication;
import com.donor.donorapp.models.DonorStatus;
import com.donor.donorapp.repositories.DonorApplicationRepository;
import com.donor.donorapp.repositories.DonorRepository;
import com.donor.donorapp.services.AdminDonorApplicationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminDonorApplicationServiceImpl implements AdminDonorApplicationService {

    private final DonorApplicationRepository donorApplicationRepository;
    private final DonorRepository donorRepository;

    public AdminDonorApplicationServiceImpl(
            DonorApplicationRepository donorApplicationRepository,
            DonorRepository donorRepository) {

        this.donorApplicationRepository = donorApplicationRepository;
        this.donorRepository = donorRepository;
    }

    @Override
    public List<DonorApplication> getPendingApplication() {
        return donorApplicationRepository.findByDonorStatus(DonorStatus.PENDING);
    }

    @Override
    @Transactional
    public ApiResponse<Void> approveApplication(Long id) {

        DonorApplication application = donorApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if(application.getDonorStatus() != DonorStatus.PENDING) {
            throw new BadRequestException("Application already exists");
        }

        if(donorRepository.findByUserId(application.getUser().getId()).isPresent()){
            throw new BadRequestException("User already approved as donor");
        }

        // update application
        application.setDonorStatus(DonorStatus.APPROVED);
        application.setReviewedAt(LocalDateTime.now());
        donorApplicationRepository.save(application);

        //create donor
        Donor donor = new Donor();

        donor.setUser(application.getUser());
        donor.setDonorCategory(application.getDonorCategory());
        donor.setPhone(application.getPhone());

        donor.setAddress1(application.getAddress1());
        donor.setAddress2(application.getAddress2());
        donor.setCity(application.getCity());
        donor.setState(application.getState());
        donor.setCountry(application.getCountry());
        donor.setPostalCode(application.getPostalCode());

        donor.setDateOfBirth(application.getDateOfBirth());
        donor.setPanNumber(application.getPanNumber());

        donor.setOrganizationName(application.getOrganizationName());
        donor.setRegistrationNumber(application.getRegistrationNumber());
        donor.setWebsite(application.getWebsite());

        donor.setNotes(application.getNotes());

        donorRepository.save(donor);

        return new ApiResponse<>(
                true,
                "Application approved",
                null);
    }

    @Override
    public ApiResponse<Void> rejectApplication(Long id) {

        DonorApplication application = donorApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if(application.getDonorStatus() != DonorStatus.PENDING){
            throw new RuntimeException("Application already processed");
        }

        application.setDonorStatus(DonorStatus.REJECTED);
        application.setReviewedAt(LocalDateTime.now());

        donorApplicationRepository.save(application);

        return new ApiResponse<>(
                true,
                "Application rejected",
                null
        );
    }
}
