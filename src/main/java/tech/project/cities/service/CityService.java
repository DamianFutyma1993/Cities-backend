package tech.project.cities.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import tech.project.cities.model.City;
import tech.project.cities.repository.CityRepository;

@Service
public class CityService {
	
	private CityRepository repository;

	@Autowired
	public CityService(CityRepository cityRepository) {
		this.repository = cityRepository;
	}
	
	public void addCities(List<City> cities) {
		repository.saveAll(cities);
	}
	
	public List<City> getAllCities(){
		return repository.findAll();
	}
	
	public City updateCity(City city) {
		return repository.save(city);
	}
}
