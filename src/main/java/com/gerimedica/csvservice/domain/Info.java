package com.gerimedica.csvservice.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Info {
	@Id
	@GeneratedValue
	private Long id;

	private String source;

	private String codeListCode;

	private String code;

	private String displayValue;

	private String longDescription;

	private Date fromDate;

	private Date toDate;

	private Integer sortingPriority;

}
