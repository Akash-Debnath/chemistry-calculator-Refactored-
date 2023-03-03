package ChemistryCalculator.backend;

import java.util.HashMap;

public class Atom {
    private static final HashMap<String, String[]> allAtoms = new Database().getAllAtoms();
    private String symbol;
    private String name;
    private double atomicMass;
    private int atomicNumber;

    private Atom(String symbol) {
        String[] atom = allAtoms.get(symbol);
        this.symbol = symbol;
        atomicNumber = Integer.parseInt(atom[0]);
        name = atom[1];
        atomicMass = Double.parseDouble(atom[2]);

    }



    // Refactored Code by Akash Chandra Debnath

    private Atom(int atomicNumber) {
        allAtoms.entrySet().stream()
                .filter(entry -> atomicNumber == Integer.parseInt(entry.getValue()[0]))
                .forEach(entry -> {
                    this.symbol = entry.getKey();
                    this.name = entry.getValue()[1];
                    this.atomicMass = Double.parseDouble(entry.getValue()[2]);
                });
        this.atomicNumber = atomicNumber;
    }

   
   
   
    // Refactored Code by Akash Chandra Debnath

    private createAtom(String symbol, int atomicNumber) {
        if (symbol != null && isValid(symbol)) {
            return new Atom(symbol);
        } else if (atomicNumber >= 1 && atomicNumber <= 118) {
            return new Atom(atomicNumber);
        }
        throw new InvalidAtomException("Invalid symbol or atomic number");
    }
    
    public static Atom getInstance(String symbol) {
        return createAtom(symbol, 0);
    }
    
    public static Atom getInstance(int atomicNumber) {
        return createAtom(null, atomicNumber);
    }



    private static boolean isValid(String symbol) {
        return allAtoms.containsKey(symbol);
    }



    // Refactored Code by Akash Chandra Debnath

    public String getElectronConfig() {
        int[] orbitalSizes = {2, 2, 6, 2, 6, 2, 10, 6, 2, 10, 6, 2, 14, 10, 6, 2, 14, 10, 6, 2};
        StringBuilder output = new StringBuilder();
        int remainingElectrons = atomicNumber;
        for (int i = 0; i < orbitalSizes.length; i++) {
            int electronsInOrbital = Math.min(orbitalSizes[i], remainingElectrons);
            output.append((i + 1)).append("s").append(electronsInOrbital).append(" ");
            remainingElectrons -= electronsInOrbital;
            if (remainingElectrons == 0) {
                break;
            }
        }
        return output.toString();
    }


    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    @Override
    public String toString() {
        return "Atom{" +
                "name='" + name + '\'' +
                '}';
    }
}

