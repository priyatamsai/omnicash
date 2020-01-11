package com.example.omnicash.repository;

import com.example.omnicash.model.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OutletRepository extends JpaRepository<Outlet,Long> {
}
