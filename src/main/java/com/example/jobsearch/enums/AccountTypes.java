package com.example.jobsearch.enums;

public enum AccountTypes {
    APPLICANT("employee"),EMPLOYER("employer");

    private  final String accType;

    AccountTypes(String accType) {
        this.accType = accType;
    }

    @Override
    public String toString() {
        return accType;
    }
}
