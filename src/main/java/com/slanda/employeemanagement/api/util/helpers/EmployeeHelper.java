package com.slanda.employeemanagement.api.util.helpers;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Class with auxiliary methods for employee administration
 */
@Component
public class EmployeeHelper {

    /**
     * Calculate the age of an employee by date of birth
     * @param dateBirth Employee's date of birth
     * @return Employee´s age
     */
    public Integer calculateAge(Date dateBirth) {
        // Covert Date to LocalDate
        LocalDate localDateBirth = dateBirth.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Get current date
        LocalDate today = LocalDate.now();
        // Calculate difference between each date
        Period age = Period.between(localDateBirth, today);

        return age.getYears();
    }
}
