package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.models.Category;
import com.example.jobsearch.exceptions.VacancyNotFoundException;
import com.example.jobsearch.models.Vacancy;
import com.example.jobsearch.services.CategoryService;
import com.example.jobsearch.services.UserService;
import com.example.jobsearch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao dao;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = dao.getAllVacancies();
        return getVacancyDtos(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesOfRespondedApplicant(String userEmail) {
        UserDto applicant = userService.getUserByEmail(userEmail);
        Long id = applicant.getId();
        List<Vacancy> vacancies = dao.getVacanciesByRespondedApplicantsId(id);
        return getVacancyDtos(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesOfCategory(String strip) {
        Category category = categoryService.getCategoryIdByName(strip);
        List<Vacancy> vacancies = dao.getVacancyByCategory(category.getId());
        return getVacancyDtos(vacancies);
    }

    @Override
    public VacancyDto getVacancyById(Long vacancyId) {
        try {
            Vacancy e = dao.getVacancyById(vacancyId).orElseThrow(VacancyNotFoundException::new);
            return VacancyDto.builder()
                    .id(e.getId())
                    .categoryId(e.getCategoryId())
                    .createdDate(e.getCreatedDate())
                    .expTo(e.getExpTo())
                    .description(e.getDescription())
                    .expFrom(e.getExpFrom())
                    .isActive(e.getIsActive())
                    .salary(e.getSalary())
                    .updateTime(e.getUpdateTime())
                    .name(e.getName())
                    .categoryId(e.getAuthorId())
                    .build();
        } catch (VacancyNotFoundException e) {
            log.error("vacancy by id: {} not found", vacancyId);
        }
        return null;
    }


    private List<VacancyDto> getVacancyDtos(List<Vacancy> vacancies) {
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
                .id(e.getId())
                .categoryId(e.getCategoryId())
                .createdDate(e.getCreatedDate())
                .expTo(e.getExpTo())
                .description(e.getDescription())
                .expFrom(e.getExpFrom())
                .isActive(e.getIsActive())
                .salary(e.getSalary())
                .updateTime(e.getUpdateTime())
                .name(e.getName())
                .categoryId(e.getAuthorId())
                .build()));
        return dtos;
    }
}
