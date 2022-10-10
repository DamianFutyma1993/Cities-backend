package tech.project.cities.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.project.cities.model.City;
import tech.project.cities.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
	
	private CityService service;
	
	@Autowired
	public CityController(CityService cityService) {
		this.service = cityService;
	}
	
	/**
	 * Get all cities from database
	 * @return List of all cities and status code
	 */
	@GetMapping("/all")
	public ResponseEntity<List<City>> getAllCities(){
		List<City> allCities = service.getAllCities();
		return new ResponseEntity<>(allCities, HttpStatus.OK);		
	}
	
	/**
	 * Update existing city
	 * @param updated city date
	 * @return Updated city and status code
	 */
	@PutMapping("/update")
	public ResponseEntity<City> updateCity(@RequestBody City city){
		City updatedCity = service.updateCity(city);
		return new ResponseEntity<>(updatedCity, HttpStatus.OK);
	}
}
