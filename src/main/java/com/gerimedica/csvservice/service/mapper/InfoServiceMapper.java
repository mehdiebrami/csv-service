package com.gerimedica.csvservice.service.mapper;


import java.util.List;

import com.gerimedica.csvservice.domain.Info;
import com.gerimedica.csvservice.service.model.InfoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfoServiceMapper {
	List<Info> toInfoList(List<InfoModel> infoModels);

	List<InfoModel> toInfoModels(List<Info> all);
}
