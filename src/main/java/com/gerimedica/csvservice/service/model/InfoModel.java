package com.gerimedica.csvservice.service.model;


import java.util.Date;

import lombok.Data;

@Data
public class InfoModel {
	private String source;

	private String codeListCode;

	private String code;

	private String displayValue;

	private String longDescription;

	private Date fromDate;

	private Date toDate;

	private Integer sortingPriority;

}
