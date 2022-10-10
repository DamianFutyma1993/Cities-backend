package tech.project.cities.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.project.cities.model.City;
import tech.project.cities.service.CityService;

@Component
public class DataLoader {
	
	private CityService service;
	
	@Autowired
	public DataLoader(CityService cityService) {
		this.service = cityService;
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
