package me.xemor.configurationdata.comparison;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonDeserialize(using = RangeData.RangeDataDeserializer.class)
public class RangeData {

    private double upperbound = Double.POSITIVE_INFINITY;
    private double lowerbound = Double.NEGATIVE_INFINITY;

    public RangeData() {}

    public RangeData(String rangeStr) {
        init(rangeStr);
    }

    public RangeData(double number) {
        this.upperbound = number;
        this.lowerbound = number;
    }

    public void init(String rangeStr) {
        rangeStr = rangeStr.stripLeading();
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

    public static class RangeDataDeserializer extends JsonDeserializer<RangeData> {
        @Override
        public RangeData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            JsonToken token = p.getCurrentToken();
            if (token.isNumeric()) return new RangeData(p.getDoubleValue());
            else return new RangeData(p.getText());
        }
    }
}
