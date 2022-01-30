package com.company.controller;

import com.company.model.Quadratic;
import com.company.model.QuadraticRequestDto;
import com.company.service.QuadraticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuadraticController {
    private final QuadraticService quadraticService;

    public QuadraticController(QuadraticService quadraticService) {
        this.quadraticService = quadraticService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("dto", new QuadraticRequestDto());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String calculate(@ModelAttribute("dto") QuadraticRequestDto dto, Model model) {
        model.addAttribute("resultMsg1", String.format(
                "Quadratic equation %.2fx²%+.2fx%+.2f=0", dto.getA(), dto.getB(), dto.getC()));
        Quadratic result;
        try {
            result = quadraticService.calculate(dto.getA(), dto.getB(), dto.getC());
        } catch (ArithmeticException e) {
            model.addAttribute("resultMsg2", "has no solution!");
            return "result";
        }
        if (Double.compare(result.getX1(), result.getX2()) == 0) {
            model.addAttribute("resultMsg2", String.format(
                    "has one solution: %.2f", result.getX1()));
        } else {
        model.addAttribute("resultMsg2", String.format(
                "has two solutions: %.2f and %.2f", result.getX1(), result.getX2()));
        }
        return "result";
    }
}
