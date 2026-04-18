package com.donor.donorapp.controllers;

import com.donor.donorapp.dto.ApiResponse;
import com.donor.donorapp.services.AdminDonorApplicationService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminDonorApplicationController {

    private final AdminDonorApplicationService adminDonorApplicationService;
    public AdminDonorApplicationController(
            AdminDonorApplicationService adminDonorApplicationService){
        this.adminDonorApplicationService = adminDonorApplicationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/donor-applications/pending")
    public ResponseEntity<?> getPendingApplications(){

        return ResponseEntity.ok(adminDonorApplicationService.getPendingApplication());

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @PutMapping("/donor-applications/{id}/approve")
    public ResponseEntity<ApiResponse<Void>> approveApplication(@PathVariable Long id) {

        return ResponseEntity.ok(
                adminDonorApplicationService.approveApplication(id)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/donor-applications/{id}/reject")
    public ResponseEntity<ApiResponse<Void>> rejectApplication(@PathVariable Long id){

        return ResponseEntity.ok(adminDonorApplicationService.rejectApplication(id));
    }
}
