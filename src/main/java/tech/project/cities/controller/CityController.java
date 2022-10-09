package tech.project.cities.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
	
	@Autowired
	CityService service;
	
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
	
	/**
	 * Method initialize date add the application start
	 */
	@PostConstruct
	public void initData() {
		String line = "";
		List<City> listOfAllCities = new ArrayList<>();
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cities.csv").getFile());
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while((line = reader.readLine()) != null) {
				String [] data = line.split(",");
				City city = new City();
				city.setId(Long.parseLong(data[0]));
				city.setName(data[1]);
				city.setImageUrl(data[2]);
				listOfAllCities.add(city);
			}
			service.addCities(listOfAllCities);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
