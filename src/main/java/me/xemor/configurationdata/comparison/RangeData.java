package me.xemor.configurationdata.comparison;

import org.bukkit.configuration.ConfigurationSection;

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
            String[] split = rangeStr.split("- ");
            if (split.length == 1) {
                lowerbound = Double.parseDouble(rangeStr);
                upperbound = Double.parseDouble(rangeStr);
            }
            else {
                lowerbound = Double.parseDouble(split[0]);
                upperbound = Double.parseDouble(split[1]);
            }
        } else {
            lowerbound = Double.NEGATIVE_INFINITY;
            upperbound = Double.POSITIVE_INFINITY;
        }
    }

    public boolean isInRange(double value) {
        return value >= lowerbound && value <= upperbound;
    }

}
