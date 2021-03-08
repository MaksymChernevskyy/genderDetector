package com.genderDetector.rest;

import com.genderDetector.enums.GenderNameEnum;
import com.genderDetector.service.GenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenderNameControllerTest {
    private GenderService genderService;
    private GenderNameController controller;

    @BeforeEach
    void setup() {
        genderService = mock(GenderService.class);
        controller = new GenderNameController(genderService);
    }

    @Test
    void shouldReturnStatusCodeOK() {
        List<String> names = new ArrayList<>(Arrays.asList("Zbigniew", "Jan", "Maria"));
        when(genderService.chooseMethodToGetGenderName(names,true)).thenReturn(GenderNameEnum.FEMALE.name());

        ResponseEntity<?> status = controller.getGenderName(names, true);

        assertEquals(status.getStatusCode(), HttpStatus.OK);
        verify(genderService).chooseMethodToGetGenderName(names, true);
    }
}