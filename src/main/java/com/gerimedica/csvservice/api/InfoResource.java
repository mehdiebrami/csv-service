package com.gerimedica.csvservice.api;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.gerimedica.csvservice.api.dto.CsvInfoDto;
import com.gerimedica.csvservice.api.mapper.InfoResourceMapper;
import com.gerimedica.csvservice.exception.CsvFileException;
import com.gerimedica.csvservice.exception.NotFoundInfoException;
import com.gerimedica.csvservice.service.InfoService;
import com.gerimedica.csvservice.service.model.InfoModel;
import com.gerimedica.csvservice.util.Utils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Info resource")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/info")
public class InfoResource {

	private final Utils utils;

	private final InfoResourceMapper mapper;

	private final InfoService service;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file) throws CsvFileException {
		List<CsvInfoDto> csvDTOList = utils.toCsvInfoDtos(file);
		service.save(mapper.toInfoModels(csvDTOList));
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{code}", produces = "text/csv")
	public void downloadByCode(HttpServletResponse httpResponse, @PathVariable("code") String code) throws NotFoundInfoException, CsvFileException {
		httpResponse.setContentType("text/csv");
		InfoModel infoModel = service.getByCode(code);
		String fileName = "info-list-%s-%s.csv".formatted(infoModel.getCode(), new Date());
		httpResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"%s\"".formatted(fileName));
		utils.toCsv(httpResponse, mapper.toCsvInfoDto(List.of(infoModel)));
	}

	@GetMapping(produces = "text/csv")
	public void downloadAll(HttpServletResponse httpResponse) throws NotFoundInfoException, CsvFileException {
		List<InfoModel> infoModels = service.getAll();
		httpResponse.setContentType("text/csv");
		String fileName = "info-list-%s-%s.csv".formatted(infoModels.get(0).getCode(), new Date());
		httpResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"%s\"".formatted(fileName));
		utils.toCsv(httpResponse, mapper.toCsvInfoDto(infoModels));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		service.deleteAll();
		return ResponseEntity.ok().build();
	}
}
