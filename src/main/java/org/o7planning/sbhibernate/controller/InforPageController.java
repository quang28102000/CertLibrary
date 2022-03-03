package org.o7planning.sbhibernate.controller;

import java.util.List;

import org.o7planning.sbhibernate.presistence.InforPageDto;
import org.o7planning.sbhibernate.service.InforPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/information")
public class InforPageController {
	
	@Autowired
	public InforPageService inforPageService;

	@GetMapping("/all")
	public ResponseEntity<List<InforPageDto>> getAllInformation() {
		List<InforPageDto> information = inforPageService.getInforDto();
		return new ResponseEntity<>(information, HttpStatus.OK);
	}
}
