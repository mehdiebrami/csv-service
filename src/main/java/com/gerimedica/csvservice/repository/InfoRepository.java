package com.gerimedica.csvservice.repository;

import java.util.Optional;

import com.gerimedica.csvservice.domain.Info;
import com.gerimedica.csvservice.service.model.InfoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
	Optional<InfoModel> findByCode(String code);
}
