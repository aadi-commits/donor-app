package com.donor.donorapp.services;

import com.donor.donorapp.dto.ApiResponse;
import com.donor.donorapp.models.DonorApplication;

import java.util.List;

public interface AdminDonorApplicationService {

    List<DonorApplication> getPendingApplication();

    ApiResponse<Void> approveApplication(Long id);

    ApiResponse<Void> rejectApplication(Long id);
}
