package com.donor.donorapp.controllers;

import com.donor.donorapp.dto.ApiResponse;
import com.donor.donorapp.dto.DonorApplicationRequest;
import com.donor.donorapp.dto.DonorApplicationResponse;
import com.donor.donorapp.exceptions.BadRequestException;
import com.donor.donorapp.exceptions.ResourceNotFoundException;
import com.donor.donorapp.models.DonorApplication;
import com.donor.donorapp.models.DonorStatus;
import com.donor.donorapp.models.User;
import com.donor.donorapp.repositories.DonorApplicationRepository;
import com.donor.donorapp.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("/donor")
public class DonorApplicationController {

    private final DonorApplicationRepository donorApplicationRepository;
    private final UserRepository userRepository;

    public DonorApplicationController(DonorApplicationRepository donorApplicationRepository,
                                      UserRepository userRepository) {
        this.donorApplicationRepository = donorApplicationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<DonorApplicationResponse>> applyDonor(
            @RequestParam Long userId,
            @Valid @RequestBody DonorApplicationRequest request){

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        boolean exists = donorApplicationRepository
                .existsByUserIdAndDonorStatus(userId, DonorStatus.PENDING);

        if(exists){
            throw new BadRequestException("A pending donor application already exists");
        }

        DonorApplication application = new DonorApplication();

        application.setUser(user);
        application.setDonorCategory(request.getDonorCategory());
        application.setDonorStatus(DonorStatus.PENDING);

        application.setPhone(request.getPhone());
        application.setAddress1(request.getAddress1());
        application.setAddress2(request.getAddress2());
        application.setState(request.getState());
        application.setCity(request.getCity());
        application.setCountry(request.getCountry());
        application.setPostalCode(request.getPostalCode());

        application.setDateOfBirth(request.getDateOfBirth());
        application.setPanNumber(request.getPanNumber());

        application.setOrganizationName(request.getOrganizationName());
        application.setRegistrationNumber(request.getRegistrationNumber());
        application.setWebsite(request.getWebsite());

        application.setNotes(request.getNotes());

        donorApplicationRepository.save(application);

        DonorApplicationResponse response =
                new DonorApplicationResponse(application.getUser().getId(), application.getDonorStatus());
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Donor application submitted successfully",
                        response
                )
        );
    }

    @DeleteMapping("/apply")
    public ResponseEntity<ApiResponse<Void>> cancelApplication(@RequestParam Long userId){

        Optional<DonorApplication> applicationOptional =
                donorApplicationRepository.findByUserIdAndDonorStatus(userId, DonorStatus.PENDING);

        if (applicationOptional.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            false,
                            "No pending application found",
                            null)
            );
        }

        DonorApplication application = applicationOptional.get();

        application.setDonorStatus(DonorStatus.CANCELLED);

        donorApplicationRepository.save(application);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Application cancelled successfully",
                        null
                )
        );
    }
}
