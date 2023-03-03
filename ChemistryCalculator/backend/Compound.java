package ChemistryCalculator.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compound {
    private final String compound;
    private final CompoundManager compoundManager;
    private List<Map<String, String>> percentageOfCompletion;
    private Double molarMass;

    public Compound(String compound) {
        this.compound = compound;
        this.compoundManager = new CompoundManager(compound);
    }

    private void calculateMolarMass() {
        double sum = 0;
        for (Map.Entry<Atom, Integer> entry : compoundManager.getAtomList().entrySet()) {
            Atom atom = entry.getKey();
            int coefficient = entry.getValue();
            int totalAtoms = compoundManager.getElementMatrix().get(0).get(coefficient);
            double atomicMass = totalAtoms * atom.getAtomicMass();
            sum += atomicMass;
        }
        molarMass = sum;
    }

    private void calculatePercentageOfCompletion() {
        percentageOfCompletion = new ArrayList<>();
        for (Map.Entry<Atom, Integer> entry : compoundManager.getAtomList().entrySet()) {
            Atom atom = entry.getKey();
            int coefficient = entry.getValue();
            int totalAtoms = compoundManager.getElementMatrix().get(0).get(coefficient);
            double atomicMass = totalAtoms * atom.getAtomicMass();
            double massPercent = atomicMass / getMolarMass() * 100;

            Map<String, String> elementDetails = new HashMap<>();
            elementDetails.put("symbol", atom.getSymbol());
            elementDetails.put("name", atom.getName());
            elementDetails.put("atomicMass", String.valueOf(atomicMass));
            elementDetails.put("totalAtoms", String.valueOf(totalAtoms));
            elementDetails.put("massPercent", String.valueOf(massPercent));

            percentageOfCompletion.add(elementDetails);
        }
    }

    public List<Map<String, String>> getPercentageOfCompletion() {
        if (percentageOfCompletion == null) {
            calculatePercentageOfCompletion();
        }
        return percentageOfCompletion;
    }

    public String getCompound() {
        return compound;
    }

    public double getMolarMass() {
        if (molarMass == null) {
            calculateMolarMass();
        }
        return molarMass;
    }

    public Atom[] getAtomList(){
        return compoundManager.getAtomList().keySet().toArray(new Atom[0]);
    }
}
