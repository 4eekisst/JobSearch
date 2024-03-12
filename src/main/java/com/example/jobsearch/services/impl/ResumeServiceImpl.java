package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exception.ResumeNotFoundException;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(getResumeDto(e)));
        return dtos;
    }

    @Override
    public ResumeDto getResumeById(Long id) throws ResumeNotFoundException {
        Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new ResumeNotFoundException("Resume with id: " + id + " not found"));
        return getResumeDto(resume);
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Long applicantId) {
        List<Resume> resumes = resumeDao.getResumesByApplicantId(applicantId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(getResumeDto(e)));
        return dtos;
    }

    @Override
    public void createResume(Resume resume) {
        resumeDao.createResume(resume);
    }

    @Override
    public List<ResumeDto> getResumesByCategory(Integer categoryId) {
        List<Resume> resumes = resumeDao.getResumesByCategory(categoryId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(getResumeDto(e)));
        return dtos;
    }

    private ResumeDto getResumeDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }
}
