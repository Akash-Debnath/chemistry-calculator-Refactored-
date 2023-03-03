package ChemistryCalculator.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Converter {
    private static final Map<String, Map<String, Double>> FACTOR_MAP = new HashMap<>();

    static {
        registerFactor("molars", "decimolars", 10);
        registerFactor("molars", "centimolars", 100);
        registerFactor("molars", "millimolars", 1000);
        registerFactor("molars", "micromolars", 1000000);
        registerFactor("molars", "nanomolars", 1000000000);

        registerFactor("liters", "deciliters", 10);
        registerFactor("liters", "centiliters", 100);
        registerFactor("liters", "milliliters", 1000);
        registerFactor("liters", "cubic_decimeters", 1);
        registerFactor("liters", "cubic_millimeters", 1000000);
        registerFactor("liters", "cubic_centimeters", 1000);

        registerFactor("kilogram", "gram", 1000);
        registerFactor("kilogram", "milligram", 1000000);
        registerFactor("kilogram", "pound", 2.205);
    }

    public static double convert(String from, String to, double value) {
        Optional<Map.Entry<String, Double>> first = Optional.ofNullable(FACTOR_MAP.get(from))
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert from " + from));
        Optional<Map.Entry<String, Double>> second = Optional.ofNullable(FACTOR_MAP.get(to))
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert to " + to));
        double firstFactor = first.filter(entry -> entry.getKey().equals(to))
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert from " + from + " to " + to));
        double secondFactor = second.filter(entry -> entry.getKey().equals(from))
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert from " + from + " to " + to));
        return value * firstFactor / secondFactor;
    }

    public static void registerFactor(String from, String to, double factor) {
        putFactor(from, to, factor);
        putFactor(to, from, 1.0 / factor);
    }

    private static void putFactor(String from, String to, double factor) {
        FACTOR_MAP.computeIfAbsent(from, k -> new HashMap<>()).put(to, factor);
    }
}