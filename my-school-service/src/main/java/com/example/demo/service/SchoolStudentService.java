package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.School;

public interface SchoolStudentService {
	public Iterable<School> getDetails();
	public School createSchool(School school);
	public Optional<School> findBySchoolId(Integer schoolId);

}
