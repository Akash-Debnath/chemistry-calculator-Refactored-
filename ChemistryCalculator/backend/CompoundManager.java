package ChemistryCalculator.backend;

import java.util.ArrayList;
import java.util.HashMap;

public class CompoundManager {
    private final HashMap<String, Integer> elementList = new HashMap<>();
    private final ArrayList<ArrayList<Integer>> elementMatrix = new ArrayList<>();
    private final HashMap<Atom, Integer> atomList = new HashMap<>();

    public CompoundManager(String compound, int index, int side) {
        this.compoundDecipher(compound, index, side);
    }

    public CompoundManager(String compound) {
        this.compoundDecipher(compound, 0, 1);
    }

    public CompoundManager(Compound compound, int index, int side) {
        this.compoundDecipher(compound.getCompound(), index, side);
    }

    public CompoundManager(Compound compound) {
        this.compoundDecipher(compound.getCompound(), 0, 1);
    }

    public void append(String compound) {
        this.compoundDecipher(compound, 0, 1);
    }

    public void append(String compound, int index, int side) {
        this.compoundDecipher(compound, index, side);
    }

    public void append(Compound compound) {
        this.compoundDecipher(compound.getCompound(), 0, 1);
    }

    public void append(Compound compound, int index, int side) {
        this.compoundDecipher(compound.getCompound(), index, side);
    }

    private void compoundDecipher(String compound, int index, int side) {
        String[] segments = compound.split("(?<=\\)([0-9]{0,100}))(?=[A-Z])|(?=\\([A-Za-z0-9]*\\)[0-9]*)|(?=\\*)");

        for (String segment : segments) {
            int multiplier;
            String eachCompound;
            if (segment.startsWith("(")) {
                String[] splitedSegment = segment.split("\\)(?=\\d*)");
                multiplier = Integer.parseInt(splitedSegment[1]);
                eachCompound = splitedSegment[0].substring(1);
            } else {
                if (segment.startsWith("*")) {
                    String[] splitedSegment = segment.split("\\*");
                    String[] compoundWithMultiplier = splitedSegment[1].split("(?<=^[0-9]{0,100})(?=[A-Z])");
                    multiplier = Integer.parseInt(compoundWithMultiplier[0]);
                    eachCompound = compoundWithMultiplier[1];

                } else {
                    multiplier = 1;
                    eachCompound = segment;
                }
            }
            findAtoms(eachCompound, index, multiplier, side);
        }
    }

    private void findAtoms(String eachCompound, int index, int multiplier, int side) {
        String[] atomsAndNumbers = eachCompound.split("(?=[A-Z])|(?<=[A-Za-z])(?=[0-9])");
        int i = 0;

        while (i < atomsAndNumbers.length) {

            if ((i + 1) < atomsAndNumbers.length && atomsAndNumbers[i + 1].matches("^[0-9]+$")) {
                int count = Integer.parseInt(atomsAndNumbers[i + 1]) * multiplier;
                addToMatrix(atomsAndNumbers[i], index, count, side);
                i++;
            } else {
                addToMatrix(atomsAndNumbers[i], index, multiplier, side);
            }
            i++;
        }
    }

    private void addToMatrix(String atoms, int index, int multiplier, int side) {

        if (index == elementMatrix.size()) {
            elementMatrix.add(new ArrayList<>());
            for (int i = 0; i < elementList.size(); i++) {
                elementMatrix.get(index).add(0);
            }
        }

        if (!elementList.containsKey(atoms)) {
            int value = 0;
            if (elementList.size() > 0) {
                value = elementList.size();
            }
            elementList.put(atoms, value);
            atomList.put(Atom.getInstance(atoms), value);
            for (ArrayList<Integer> matrix : elementMatrix) {
                matrix.add(0);
            }

        }

        int column = elementList.get(atoms);
        int value = elementMatrix.get(index).get(column) + multiplier * side;
        elementMatrix.get(index).set(column, value);

    }

    public HashMap<Atom, Integer> getAtomList() {
        return atomList;
    }

    public ArrayList<ArrayList<Integer>> getElementMatrix() {
        return elementMatrix;
    }


}
