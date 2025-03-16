package me.xemor.configurationdata.comparison;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonDeserialize(using = RangeData.Deserializer.class)
public class RangeData {

    private double upperbound = Double.POSITIVE_INFINITY;
    private double lowerbound = Double.NEGATIVE_INFINITY;

    public RangeData() {}

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
        }
    }

    public boolean isInRange(double value) {
        return value >= lowerbound && value <= upperbound;
    }

    public static class Deserializer extends JsonDeserializer<RangeData> {
        @Override
        public RangeData deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            String text = parser.getText();
            return new RangeData(text);
        }
    }
}
