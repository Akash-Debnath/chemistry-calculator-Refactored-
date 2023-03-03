package ChemistryCalculator.backend;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EquationBalancer {
    private final String reactants;
    private final String products;

    public EquationBalancer(String reactants, String products) {
        this.reactants = reactants;
        this.products = products;
    }

    public String balance() {
        String[] reactants = Arrays.stream(this.reactants.replaceAll("\\s+", "").split("\\+")).map(String::trim).toArray(String[]::new);
        String[] products = Arrays.stream(this.products.replaceAll("\\s+", "").split("\\+")).map(String::trim).toArray(String[]::new);

        CompoundManager manager = new CompoundManager(reactants[0]);
        for (int i = 1; i < reactants.length; i++) {
            manager.append(reactants[i], i, 1);
        }

        //products should be inputted as negative numbers into our matrix.
        for (int i = 0; i < products.length; i++) {
            manager.append(products[i], i + reactants.length, -1);
        }

        int[][] elementsMatrix = manager.getElementMatrix().stream().map(list -> list.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
        Matrix matrix = new Matrix(elementsMatrix);
        Fraction[] nullSpace = matrix.transpose().nullSpace();

        if (nullSpace == null) {
            throw new InvalidEquationException("Error ! given equation is not valid");
        }

        long[] balancedCoefficients = Fraction.normalize(nullSpace);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reactants.length; i++) {
            if (i > 0) {
                result.append(" + ");
            }
            result.append(balancedCoefficients[i]);
            result.append(reactants[i]);
        }

        result.append(" = ");

        for (int i = 0; i < products.length; i++) {
            if (i > 0) {
                result.append(" + ");
            }
            result.append(balancedCoefficients[i + reactants.length]);
            result.append(products[i]);
        }

        return result.toString();
    }
}

