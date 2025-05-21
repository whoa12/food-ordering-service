package com.projects.Food.Ordering.Website.repository;

import com.projects.Food.Ordering.Website.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
