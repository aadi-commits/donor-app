package com.donor.donorapp.dto;

import com.donor.donorapp.models.DonorStatus;

public class DonorApplicationResponse {

    private Long id;
    private DonorStatus status;

    public DonorApplicationResponse(Long id, DonorStatus status){
        this.id = id;
        this.status = status;
    }

    public Long getId(){
        return id;
    }

    public DonorStatus getStatus(){
        return status;
    }
}
