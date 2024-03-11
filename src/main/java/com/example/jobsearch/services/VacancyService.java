package com.example.jobsearch.services;

import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.exception.VacancyNotFoundException;
import com.example.jobsearch.models.Vacancy;

import java.util.List;

public interface VacancyService {

    List<VacancyDto> getAllVacancies();

    VacancyDto getVacancyById(Long id) throws VacancyNotFoundException;

}
