package ChemistryCalculator.backend;

public class Concentration {
    private final Compound compound;
    private final double givenCompoundMass;
    private final double volumeOfSolution;

    public Concentration(Compound compound, double givenCompoundMass, double volumeOfSolution) {
        this.compound = compound;
        this.givenCompoundMass = givenCompoundMass;
        this.volumeOfSolution = volumeOfSolution;
    }

    public double getMolarity() {
        double molarMass = compound.getMolarMass();
        return (1000 * givenCompoundMass) / (molarMass * volumeOfSolution);
    }

    public double getMolality() {
        double massOfSolvent = volumeOfSolution - givenCompoundMass;
        double molarMass = compound.getMolarMass();
        return (givenCompoundMass * 1000) / (molarMass * massOfSolvent);
    }

    public double geNormality(double equivalentNumber) {
        double molarity = getMolarity();
        return molarity * equivalentNumber;
    }
}
