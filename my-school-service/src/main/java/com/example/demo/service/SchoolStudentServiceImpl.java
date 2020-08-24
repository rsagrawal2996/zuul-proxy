package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.School;
import com.example.demo.repo.SchoolDao;

@Service
public class SchoolStudentServiceImpl implements SchoolStudentService{
	private SchoolDao schoolDao;
	
	public SchoolStudentServiceImpl(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	

	@Override
	@Transactional
	public Iterable<School> getDetails() {
		// TODO Auto-generated method stub
		return schoolDao.findAll();
	}

	@Override
	@Transactional
	public School createSchool(School school) {
		// TODO Auto-generated method stub
		return schoolDao.save(school);
	}



	@Override
	@Transactional
	public Optional<School> findBySchoolId(Integer schoolId) {
		// TODO Auto-generated method stub
		return schoolDao.findById(schoolId);
	}
 
}
