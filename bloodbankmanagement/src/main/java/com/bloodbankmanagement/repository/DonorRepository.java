package com.bloodbankmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodbankmanagement.model.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, String>{

    List<Donor> findByDbloodgroupAndDcityIgnoreCase(String bloodgroup, String city);

}
