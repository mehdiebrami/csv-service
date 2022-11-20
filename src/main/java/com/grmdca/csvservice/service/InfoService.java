package com.grmdca.csvservice.service;

import java.util.List;

import com.grmdca.csvservice.exception.NotFoundInfoException;
import com.grmdca.csvservice.service.model.InfoModel;

public interface InfoService {


	void save(List<InfoModel> infoModels);

	List<InfoModel> getAll() throws NotFoundInfoException;

	InfoModel getByCode(String code) throws NotFoundInfoException;

	void deleteAll();
}
