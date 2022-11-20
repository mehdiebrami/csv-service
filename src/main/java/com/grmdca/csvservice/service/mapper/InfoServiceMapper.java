package com.grmdca.csvservice.service.mapper;


import java.util.List;

import com.grmdca.csvservice.domain.Info;
import com.grmdca.csvservice.service.model.InfoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfoServiceMapper {
	List<Info> toInfoList(List<InfoModel> infoModels);

	List<InfoModel> toInfoModels(List<Info> all);

	InfoModel toInfoModel(Info orElseThrow);
}
