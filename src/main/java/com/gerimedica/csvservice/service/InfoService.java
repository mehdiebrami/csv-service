package com.gerimedica.csvservice.service;

import java.util.List;

import com.gerimedica.csvservice.exception.NotFoundInfoException;
import com.gerimedica.csvservice.service.model.InfoModel;

public interface InfoService {


	void save(List<InfoModel> infoModels);

	List<InfoModel> getAll() throws NotFoundInfoException;

	InfoModel getByCode(String code) throws NotFoundInfoException;

	void deleteAll();
}
