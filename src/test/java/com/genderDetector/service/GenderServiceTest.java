package com.genderDetector.service;

import com.genderDetector.enums.GenderNameEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GenderServiceTest {
    GenderService genderService = new GenderService();

    @Test
    void shouldReturnMaleForOneName() {
        assertEquals(GenderNameEnum.MALE.name(), genderService.chooseMethodToGetGenderName(Arrays.asList("Jan", "Maria", "Rokita"), false));
    }

    @Test
    void shouldReturnFemaleForOneName() {
        assertEquals(GenderNameEnum.FEMALE.name(), genderService.chooseMethodToGetGenderName(Arrays.asList("Anna", "Jan", "Zbigniew"),false));
    }

    @Test
    void shouldReturnInconclusiveForOneName() {
        assertEquals(GenderNameEnum.INCONCLUSIVE.name(), genderService.chooseMethodToGetGenderName(Arrays.asList("aaa", "Jan", "Zbigniew"),false));
    }

    @Test
    void shouldReturnFemaleForSeveralNames() {
        assertEquals(GenderNameEnum.FEMALE.name(), genderService.chooseMethodToGetGenderName(Arrays.asList("Anna", "Gertruda", "Zbigniew"), true));
    }

    @Test
    void shouldReturnMaleForSeveralNames() {
        assertEquals(GenderNameEnum.MALE.name(), genderService.chooseMethodToGetGenderName(Arrays.asList("Anna", "Jan", "Zbigniew"),true));
    }

    @Test
    void shouldReturnInconclusiveForSeveralNames() {
        assertEquals(GenderNameEnum.INCONCLUSIVE.name(), genderService.chooseMethodToGetGenderName(Arrays.asList("Jan", "Maria", "Rokita"),true));
    }
}