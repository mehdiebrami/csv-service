package com.grmdca.csvservice.api.dto;

import java.util.Date;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvInfoDto {

	@CsvBindByPosition(position = 0)
	private String source;

	@CsvBindByPosition(position = 1)
	private String codeListCode;

	@CsvBindByPosition(position = 2)
	private String code;

	@CsvBindByPosition(position = 3)
	private String displayValue;

	@CsvBindByPosition(position = 4)
	private String longDescription;

	@CsvBindByPosition(position = 5)
	@CsvDate(value = "dd-MM-yyyy")
	private Date fromDate;

	@CsvBindByPosition(position = 6)
	@CsvDate(value = "dd-MM-yyyy")
	private Date toDate;

	@CsvBindByPosition(position = 7)
	private Integer sortingPriority;
}
