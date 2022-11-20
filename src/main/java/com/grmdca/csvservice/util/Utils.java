package com.grmdca.csvservice.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import com.grmdca.csvservice.api.dto.CsvInfoDto;
import com.grmdca.csvservice.exception.CsvFileException;
import com.grmdca.csvservice.service.model.InfoModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class Utils {

	public List<CsvInfoDto> toCsvInfoDtos(MultipartFile file) throws CsvFileException {
		if (!Objects.equals(file.getContentType(), "text/csv")) {
			throw new CsvFileException("File type is wrong: %s".formatted(file.getContentType()));
		}
		try (Reader reader = new InputStreamReader(file.getInputStream())) {
			CsvToBean csvToBean = new CsvToBeanBuilder(reader)
					.withSkipLines(1)
					.withType(CsvInfoDto.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			return csvToBean.parse();
		} catch (IOException e) {
			throw new CsvFileException("While casting file error occurred! file name: %s".formatted(file.getName()));
		}
	}

	public void toCsv(HttpServletResponse httpResponse, List<InfoModel> list) throws CsvFileException {
		try {
			StatefulBeanToCsv<InfoModel> writer = new StatefulBeanToCsvBuilder<InfoModel>(httpResponse.getWriter())
					.withOrderedResults(true)
					.build();
			writer.write(list);
			httpResponse.getWriter().close();
		} catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
			throw new CsvFileException("While making file error occurred!");
		}
	}
}
