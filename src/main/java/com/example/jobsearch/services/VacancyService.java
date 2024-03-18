package com.example.jobsearch.services;


import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.exceptions.CategoryNotFoundException;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.exceptions.VacancyNotFoundException;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesOfRespondedApplicant(String email) throws UserNotFoundException;

    List<VacancyDto> getVacanciesOfCategory(String strip) throws CategoryNotFoundException;

    VacancyDto getVacancyById(Long vacancyId);

    VacancyDto createVacancy(VacancyDto vacancyDto);

    void deleteVacancy(Long vacancyId);

    VacancyDto updateVacancy(Long vacancyId, VacancyDto updatedVacancy) throws VacancyNotFoundException;

}
