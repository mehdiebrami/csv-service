package com.grmdca.csvservice.repository;

import java.util.Optional;

import com.grmdca.csvservice.domain.Info;
import com.grmdca.csvservice.service.model.InfoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
	Optional<InfoModel> findByCode(String code);
}
