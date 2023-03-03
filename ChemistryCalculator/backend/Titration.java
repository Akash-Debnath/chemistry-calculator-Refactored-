package ChemistryCalculator.backend;

import java.util.ArrayList;
import java.util.List;

public class Titration {

    private final List<Double> acidProperties = new ArrayList<>();
    private final List<Double> baseProperties = new ArrayList<>();

    // Constructor to add all the given properties
    public Titration(Double molarityOfAcid, Double molarityOfBase, Double volumeOfAcid,
                     Double volumeOfBase, Double numOfMolesOfAcid, Double numOfMolesOfBase) {
        addAcidProperties(molarityOfAcid, volumeOfAcid, numOfMolesOfAcid);
        addBaseProperties(molarityOfBase, volumeOfBase, numOfMolesOfBase);
    }

    // Add the acid properties to the list
    private void addAcidProperties(Double molarityOfAcid, Double volumeOfAcid, Double numOfMolesOfAcid) {
        acidProperties.add(molarityOfAcid);
        acidProperties.add(volumeOfAcid);
        acidProperties.add(numOfMolesOfAcid);
    }

    // Add the base properties to the list
    private void addBaseProperties(Double molarityOfBase, Double volumeOfBase, Double numOfMolesOfBase) {
        baseProperties.add(molarityOfBase);
        baseProperties.add(volumeOfBase);
        baseProperties.add(numOfMolesOfBase);
    }

    // Get the unknown value using the given formula
    public double getUnknownValue() {
        if (isValid()) {
            double numerator = 1;
            double denominator = 1;
            int unknownIndex = -1;
            if (acidProperties.contains(null)) {
                unknownIndex = acidProperties.indexOf(null);
                for (Double baseProperty : baseProperties) {
                    numerator *= baseProperty;
                }
            } else {
                unknownIndex = baseProperties.indexOf(null);
                for (Double acidProperty : acidProperties) {
                    numerator *= acidProperty;
                }
            }
            for (int i = 0; i < acidProperties.size(); i++) {
                if (i != unknownIndex) {
                    denominator *= acidProperties.get(i);
                }
            }
            for (int i = 0; i < baseProperties.size(); i++) {
                if (i != unknownIndex) {
                    denominator *= baseProperties.get(i);
                }
            }
            return numerator / denominator;
        } else {
            throw new InsufficientDataException("Fill up any 5 fields to get unknown value");
        }
    }

    // Check if the input is valid
    private boolean isValid() {
        int nullCount = 0;
        for (Double property : acidProperties) {
            if (property == null) {
                nullCount++;
            }
        }
        for (Double property : baseProperties) {
            if (property == null) {
                nullCount++;
            }
        }
        return nullCount == 1;
    }

}








// package ChemistryCalculator.backend;

// import java.util.Arrays;

// public class Titration {

//     private final String[] acidProperties = new String[3];
//     private final String[] baseProperties = new String[3];
//     private boolean unknownValue_in_Acid = false;

//     //Here one and only one property can be empty, which is the unknown value
//     public Titration(String molarityOfAcid,
//                      String molarityOfBase,
//                      String volumeOfAcid,
//                      String volumeOfBase,
//                      String numOfMolesOfAcid,
//                      String numOfMolesOfBase) {
//         acidProperties[0] = molarityOfAcid;
//         acidProperties[1] = volumeOfAcid;
//         acidProperties[2] = numOfMolesOfAcid;

//         baseProperties[0] = molarityOfBase;
//         baseProperties[1] = volumeOfBase;
//         baseProperties[2] = numOfMolesOfBase;
//     }

//     //Executing formula  =>  (numOfMolesOfBase*molarityOfBase*volumeOfBase) = numOfMolesOfAcid*molarityOfAcid*volumeOfAcid)
//     public double getUnknownValue() {
//         if (this.isValid()) {

//             double numerator;
//             double denominator = 1;
//             if (unknownValue_in_Acid) {

//                 numerator = Arrays.stream(baseProperties).mapToDouble(Double::parseDouble).reduce(1, (a, b) -> a * b);
//                 for (String property : acidProperties) {
//                     if (!property.isEmpty()) {
//                         denominator *= Double.parseDouble(property);
//                     }
//                 }
//                 return numerator / denominator;
//             } else {
//                 numerator = Arrays.stream(acidProperties).mapToDouble(Double::parseDouble).reduce(1, (a, b) -> a * b);
//                 for (String property : baseProperties) {
//                     if (!property.isEmpty()) {
//                         denominator *= Double.parseDouble(property);
//                     }
//                 }

//                 return numerator / denominator;

//             }
//         } else {
//             throw new InsufficientDataException("\"Fill up any 5 fields to get unknown value\"");
//         }


//     }


//     // if there is only one index value contain empty string  in both String[] acidProperties and String[] baseProperties, then its return true. False otherwise
//     private boolean isValid() {
//         int count = 0;
//         for (String property : acidProperties) {
//             if (property.isEmpty()) {
//                 unknownValue_in_Acid = true;
//                 count++;
//             }
//         }
//         count += Arrays.stream(baseProperties).filter(String::isEmpty).count();
//         return count < 2;
//     }

// }
