package com.genderDetector.rest;

import com.genderDetector.service.GenderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GenderNameController.class)
@AutoConfigureMockMvc
public class GenderNameControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GenderService genderService;

    @Test
    void shouldReturnStatisCodeOkForController() throws Exception {
        List<String> names = new ArrayList<>(Arrays.asList("Jan", "Maria", "Zbigniew"));

        MvcResult result = mockMvc
                .perform(get("/gender/names?methodNumber=1&names=Jan&names=Maria&names=Zbigniew"))
                .andReturn();

        int actualHttpStatus = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), actualHttpStatus);
        verify(genderService).chooseMethodToGetGenderName(names, 1);
    }
}
