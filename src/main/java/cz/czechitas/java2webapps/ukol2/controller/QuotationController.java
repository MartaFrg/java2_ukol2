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
    public ModelAndView citaty() throws IOException {
        List citatyList = prectiCitaty("citaty.txt");
        int cisloCitatu = random.nextInt(8);
        ModelAndView result = new ModelAndView("citaty");
        result.addObject("citat", citatyList.get(cisloCitatu));
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

