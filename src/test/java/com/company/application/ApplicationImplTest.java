package com.company.application;

import com.company.enums.Gender;
import com.company.enums.Qualification;
import com.company.enums.Role;
import com.company.exceptions.ApplicantAlreadyAppliedException;
import com.company.models.Applicant;
import com.company.models.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ApplicationImplTest {
    private ApplicationImpl application;

    @BeforeEach
    void setUp() {
        application = new ApplicationImpl();

    }

    @Test
    void applicantCanApplyToACompanySuccessfully() throws ApplicantAlreadyAppliedException {
        Applicant applicant = new Applicant(
                "James", "Deen","Edo", Gender.MALE,
                Qualification.MSc, Role.JUNIOR_DEVELOPER
        );

        Company company = new Company("Johnny", "67, edo tech");

        assertEquals(0, company.getApplicants().size());
        application.apply(applicant, company);
        assertEquals(1, company.getApplicants().size());
    }

    @Test
    void shouldThrowAnExceptionWhenApplicantAlreadyApplied() throws ApplicantAlreadyAppliedException {
        Applicant applicant = new Applicant(
                "James", "Deen","Edo", Gender.MALE,
                Qualification.MSc, Role.JUNIOR_DEVELOPER
        );

        Company company = new Company("Johnny", "67, edo tech");
        application.apply(applicant, company);

//        assertThrows(ApplicantAlreadyAppliedException.class,
//                () -> application.apply(applicant, company));

        assertThrowsExactly(ApplicantAlreadyAppliedException.class,
                () -> application.apply(applicant, company));
    }
}