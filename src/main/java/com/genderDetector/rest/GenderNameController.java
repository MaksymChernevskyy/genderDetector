package com.genderDetector.rest;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.genderDetector.service.GenderService;

import java.util.List;

@Api(value = "/gender", description = "Returns gender by names", tags = {"GenderNameDetector"})
@RestController
@RequestMapping(GenderNameController.BASE_URL)
public class GenderNameController {
    private GenderService genderService;

    @Autowired
    public GenderNameController(GenderService genderService) {
        this.genderService = genderService;
    }

    public static final String BASE_URL = "/gender";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGenderName(@RequestParam("names") List<String> names,
                                                @RequestParam("genderByAllNames") boolean genderByAllNames) {
       return getResponseForSuccess(genderService.chooseMethodToGetGenderName(names, genderByAllNames));
    }

    private ResponseEntity<String> getResponseForSuccess(String names) {
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
}
