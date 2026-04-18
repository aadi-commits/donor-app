package com.donor.donorapp.repositories;

import com.donor.donorapp.models.DonorApplication;
import com.donor.donorapp.models.DonorStatus;
import com.donor.donorapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorApplicationRepository extends JpaRepository<DonorApplication, Long> {

    Optional<DonorApplication> findByUserIdAndDonorStatus(Long userId, DonorStatus donorStatus);

    boolean existsByUserIdAndDonorStatus(Long userId, DonorStatus status);

    List<DonorApplication> findByDonorStatus(DonorStatus donorStatus);
}
