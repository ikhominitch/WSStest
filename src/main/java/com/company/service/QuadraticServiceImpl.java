package com.company.service;

import com.company.dao.QuadraticDao;
import com.company.model.Quadratic;
import org.springframework.stereotype.Service;

@Service
public class QuadraticServiceImpl implements QuadraticService {
    private final QuadraticDao quadraticDao;

    public QuadraticServiceImpl(QuadraticDao quadraticDao) {
        this.quadraticDao = quadraticDao;
    }

    @Override
    public Quadratic calculate(Double a, Double b, Double c) {
        double d = b * b - 4 * a * c;
        if (d < 0) {
            throw new ArithmeticException("Quadratic equation has no solution");
        }
        Quadratic quadratic = new Quadratic(a, b, c);
        quadratic.setX1(- b + Math.sqrt(d) / (2 * a));
        quadratic.setX2(- b - Math.sqrt(d) / (2 * a));
        quadraticDao.save(quadratic);
        return quadratic;
    }
}
