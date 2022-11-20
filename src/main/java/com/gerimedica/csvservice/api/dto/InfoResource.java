package com.gerimedica.csvservice.api.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bugs resource")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/bugs")
public class InfoResource {
}
