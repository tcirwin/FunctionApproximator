package me.therin.math451;

import java.util.ArrayList;
import java.util.List;

public class Polynomial implements DifferentiableFunction {
    
    public static double epsilon = 0.000000001d;

    // The list of term coefficients in descending order of degree.
    // Ex. 4x^2 + 5x - 2 would be represented as [4, 5, -2].
    private List<Double> termCoeff;
    
    public Polynomial(List<Double> coeffs) {
       termCoeff = coeffs;
    }
    
    @Override
    public double evaluate(double x) {
        double answer = 0.0d;
        
        for (int i = 0; i < termCoeff.size(); i++) {
            if (i != termCoeff.size() - 1)
                answer = (answer + termCoeff.get(i)) * x;
            else
                answer += termCoeff.get(i);
        }
        
        return answer;
    }

    @Override
    public Polynomial differentiate() {
        int curDegree = termCoeff.size() - 1;
        List<Double> coeffs = new ArrayList<Double>();
        
        for (double coeff : termCoeff) {
            if (curDegree > 0) {
                coeffs.add(coeff * curDegree);
                curDegree--;
            }
            else break;
        }
        
        return new Polynomial(coeffs);
    }
    
    public List<Double> getTermCoeffs() {
        return this.termCoeff;
    }
}
