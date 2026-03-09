package com.donor.donorapp.specifications;

import com.donor.donorapp.models.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasFirstName(String fname){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("fname")),
                        "%" + fname.toLowerCase() + "%");
    }

    public static Specification<User> hasEmail(String email){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%");
    }
}
