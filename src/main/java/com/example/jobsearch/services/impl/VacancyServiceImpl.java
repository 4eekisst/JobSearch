package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.models.Category;
import com.example.jobsearch.exceptions.VacancyNotFoundException;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.models.Vacancy;
import com.example.jobsearch.services.CategoryService;
import com.example.jobsearch.services.UserService;
import com.example.jobsearch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<VacancyDto> getAllVacancies(int page, int pageSize) {
        int offset = page * pageSize;

        List<Vacancy> vacancies = dao.getAllVacancies(offset, pageSize);

        return getVacancyDtos(vacancies);
    }

    @Override
    public int getTotalVacanciesCount() {

        return dao.getTotalVacanciesCount();
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(String category, int page, int pageSize) {
        int offset = page * pageSize;
        List<Vacancy> vacancies = dao.getVacanciesByCategory(category, offset, pageSize);
        return getVacancyDtos(vacancies);
    }

    @Override
    public int getTotalVacanciesCountByCategory(String category) {
        return dao.getTotalVacanciesCountByCategory(category);
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
    public VacancyDto createVacancy(VacancyDto vacancyDto) {
        Vacancy vacancy = mapDtoToVacancy(vacancyDto);
        vacancy.setCreatedDate(LocalDateTime.now());
        vacancy.setUpdateTime(LocalDateTime.now());
        Long vacancyId = dao.createVacancy(vacancy);
        return getVacancyById(vacancyId);
    }


    @Override
    public void deleteVacancy(Long vacancyId) {
        dao.deleteVacancyById(vacancyId);
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


    @Override
    public VacancyDto updateVacancy(Long vacancyId, VacancyDto updatedVacancy) throws VacancyNotFoundException {
        Vacancy existingVacancy = dao.getVacancyById(vacancyId)
                .orElseThrow(() -> new VacancyNotFoundException("Вакансия с id: " + vacancyId + " не найдена"));

        if (updatedVacancy.getName() != null) {
            existingVacancy.setName(updatedVacancy.getName());
        }
        if (updatedVacancy.getDescription() != null) {
            existingVacancy.setDescription(updatedVacancy.getDescription());
        }
        if (updatedVacancy.getCategoryId() != null) {
            existingVacancy.setCategoryId(updatedVacancy.getCategoryId());
        }
        if (updatedVacancy.getSalary() != null) {
            existingVacancy.setSalary(updatedVacancy.getSalary());
        }
        existingVacancy.setUpdateTime(LocalDateTime.now());

        dao.updateVacancy(existingVacancy);

        return VacancyDto.builder()
                .id(existingVacancy.getId())
                .createdDate(existingVacancy.getCreatedDate())
                .updateTime(existingVacancy.getUpdateTime())
                .build();
    }



    @Override
    public List<ResumeDto> getRespondedApplicants(Long vacancyId) {
        List<Resume> respondedResumes = dao.getRespondedApplicantsForVacancy(vacancyId);
        List<ResumeDto> respondedApplicantsDto = new ArrayList<>();
        respondedResumes.forEach(resume -> respondedApplicantsDto.add(mapResumeToDto(resume)));
        return respondedApplicantsDto;
    }

    private ResumeDto mapResumeToDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .userId(resume.getApplicantId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateDate())
                .build();
    }

    private Vacancy mapDtoToVacancy(VacancyDto vacancyDto) {
        return Vacancy.builder()
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .authorId(vacancyDto.getAuthorId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.getIsActive())
                .build();
    }
}
