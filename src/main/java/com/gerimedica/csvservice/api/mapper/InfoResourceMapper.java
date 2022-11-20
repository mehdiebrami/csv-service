package com.gerimedica.csvservice.api.mapper;

import java.util.List;

import com.gerimedica.csvservice.api.dto.CsvInfoDto;
import com.gerimedica.csvservice.service.model.InfoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfoResourceMapper {

	List<InfoModel> toInfoModels(List<CsvInfoDto> csvDTOList);

	List<InfoModel> toCsvInfoDto(List<InfoModel> infoModels);
}
