package com.genderDetector.service;

import com.genderDetector.enums.GenderNameEnum;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@Service
public class GenderService {

    public String chooseMethodToGetGenderName(List<String> names, boolean genderByAllNames) {
        if (genderByAllNames) {
            return getgenderByAllNames(names);
        } else {
            return getGenderByOnlyFirstName(names);
        }
    }

    private String getGenderByOnlyFirstName(List<String> names) {
        String genderName = GenderNameEnum.INCONCLUSIVE.name();
        if (names != null) {
            String name = names.get(0);
            Scanner maleNames = createScanner("MaleNames");
            genderName = getGenderName(name, genderName, maleNames, GenderNameEnum.MALE);

            Scanner femaleNames = createScanner("FemaleNames");
            genderName = getGenderName(name, genderName, femaleNames, GenderNameEnum.FEMALE);
        }
        return genderName;
    }

    private String getGenderName(String name, String genderName, Scanner maleNames, GenderNameEnum male) {
        while (maleNames.hasNextLine()) {
            String line = maleNames.nextLine();
            if (line.contains(name)) {
                genderName = male.name();
                break;
            }
        }
        return genderName;
    }

    private String getgenderByAllNames(List<String> names) {
        int countMale = 0;
        int countFemale = 0;
        for (String name : names) {
            Scanner maleNames = createScanner("MaleNames");
            countMale = getCountOfNames(countMale, name, maleNames);

            Scanner femaleNames = createScanner("FemaleNames");
            countFemale = getCountOfNames(countFemale, name, femaleNames);
        }
        return (countFemale > countMale ? GenderNameEnum.FEMALE.name() :
                countFemale < countMale ? GenderNameEnum.MALE.name() : GenderNameEnum.INCONCLUSIVE.name());
    }

    private int getCountOfNames(int countNames, String name, Scanner maleNames) {
        while (maleNames.hasNextLine()) {
            String line = maleNames.nextLine();
            if (line.contains(name)) {
                countNames++;
            }
        }
        return countNames;
    }

    private Scanner createScanner(String names) {
        return new Scanner(getInputStream(names), StandardCharsets.UTF_8);
    }

    private InputStream getInputStream(String fileName) {
        InputStream inputStream;
        inputStream = GenderService.class.getClassLoader().getResourceAsStream("listsWithNames\\" + fileName + ".txt");
        if (inputStream == null) {
            Resource resource = new ClassPathResource("classpath:listsWithNames/" + fileName + ".txt");
            try {
                inputStream = resource.getInputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return inputStream;
        } else {
            return inputStream;
        }
    }
}
