package com.grmdca.csvservice.service.impl;

import java.util.List;

import com.grmdca.csvservice.domain.Info;
import com.grmdca.csvservice.exception.NotFoundInfoException;
import com.grmdca.csvservice.repository.InfoRepository;
import com.grmdca.csvservice.service.InfoService;
import com.grmdca.csvservice.service.mapper.InfoServiceMapper;
import com.grmdca.csvservice.service.model.InfoModel;
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
		return mapper.toInfoModel(repository.findByCode(code).orElseThrow(() -> new NotFoundInfoException("This info does not exist, by this code: %s".formatted(code))));
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}
