package com.gerimedica.csvservice.service.impl;

import java.util.List;

import com.gerimedica.csvservice.domain.Info;
import com.gerimedica.csvservice.exception.NotFoundInfoException;
import com.gerimedica.csvservice.repository.InfoRepository;
import com.gerimedica.csvservice.service.InfoService;
import com.gerimedica.csvservice.service.mapper.InfoServiceMapper;
import com.gerimedica.csvservice.service.model.InfoModel;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InfoServiceImpl implements InfoService {

	private final InfoRepository repository;

	private final InfoServiceMapper mapper;

	@Override
	public void save(List<InfoModel> infoModels) {
		repository.saveAll(mapper.toInfoList(infoModels));
	}

	@Override
	public List<InfoModel> getAll() throws NotFoundInfoException {
		List<Info> infoList = repository.findAll();
		if (CollectionUtils.isNotEmpty(infoList)) {
			return mapper.toInfoModels(infoList);
		}
		throw new NotFoundInfoException("There are not any info");
	}

	@Override
	public InfoModel getByCode(String code) throws NotFoundInfoException {
		return repository.findByCode(code).orElseThrow(() -> new NotFoundInfoException("This info does not exist, by this code: %s".formatted(code)));
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}
