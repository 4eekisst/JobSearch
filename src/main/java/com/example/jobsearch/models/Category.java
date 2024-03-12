package com.example.jobsearch.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Category {
    private Long id;
    private String name;
    private Integer parentId;
}
