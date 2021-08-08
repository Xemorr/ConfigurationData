package me.xemor.configurationdata.comparison;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RangeData {

    private double upperbound;
    private double lowerbound;

    public RangeData(String variable, ConfigurationSection configurationSection) {
        String rangeStr = configurationSection.getString(variable);
        init(rangeStr);
    }

    public RangeData(String rangeStr) {
        init(rangeStr);
    }

    public void init(String rangeStr) {
        if (rangeStr != null) {
            List<Double> rangeList = Arrays.stream(rangeStr.split("- ")).map(value -> value.replace(" ", "")).map(Double::valueOf).collect(Collectors.toList());
            lowerbound = rangeList.get(0);
            upperbound = rangeList.get(1);
        } else {
            lowerbound = Double.NEGATIVE_INFINITY;
            upperbound = Double.POSITIVE_INFINITY;
        }
    }

    public boolean isInRange(double value) {
        return value >= lowerbound && value <= upperbound;
    }

}
