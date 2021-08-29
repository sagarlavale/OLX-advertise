package com.zensar.advertise.repository;

import com.zensar.advertise.entity.Advertise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AdvertiseRepository extends JpaRepository<Advertise, Integer>, JpaSpecificationExecutor<Advertise> {

    @Query("Select u from Advertise u where u.createdById = (:userId)")
    List<Advertise> findAllByUser(@Param("userId") Integer userId);

    @Query("Select u from Advertise u where u.id = (:postId) and u.createdById =(:userId)")
    Optional<Advertise> findByUserAndPostId(@Param("userId") Integer userId, @Param("postId") Integer postId);

}
