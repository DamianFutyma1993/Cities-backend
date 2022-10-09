package tech.project.cities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.project.cities.model.City;

public interface CityRepository extends JpaRepository<City, Long>{}