package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class QuotationController {
    private final Random random = new Random();

    @GetMapping("/")
    public ModelAndView citaty() {
        List<String> citatyList;
        List<String> listOfImages;
        int cisloCitatu = random.nextInt(8);
        String citat;
        try {
            citatyList = prectiCitaty("citaty.txt");
        citat = citatyList.get(cisloCitatu);
        }
        catch(Exception e){
            citat = "Citát nenačten.";
        }
        listOfImages = List.of("sC4g85ssOwA", "8AoiLR9DOqc", "MpyiM0FKxL8", "w8NcD5tSu_k", "mS0v4ilJgBA", "u2IZda2Dn6A", "spz5zbmOSiU", "WvA2RaOSThk");
        ModelAndView result = new ModelAndView("citaty");
        result.addObject("citat", citat);
        result.addObject("imageURL", "https://source.unsplash.com/"+ listOfImages.get(cisloCitatu) +"/1600x900");
        return result;
    }

    private static List<String> prectiCitaty(String resource) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resource);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }
}

