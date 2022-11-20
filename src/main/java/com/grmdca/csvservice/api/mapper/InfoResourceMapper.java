package com.grmdca.csvservice.api.mapper;

import java.util.List;

import com.grmdca.csvservice.api.dto.CsvInfoDto;
import com.grmdca.csvservice.service.model.InfoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfoResourceMapper {

	List<InfoModel> toInfoModels(List<CsvInfoDto> csvDTOList);

	List<InfoModel> toCsvInfoDto(List<InfoModel> infoModels);
}
