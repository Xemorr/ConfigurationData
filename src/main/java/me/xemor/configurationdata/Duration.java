package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Optional;

public class Duration {

    private Double durationInSeconds = null;

    @JsonCreator
    public Duration(Double duration) {
        this.durationInSeconds = duration;
    }

    @JsonCreator
    public Duration(int duration) { this.durationInSeconds = (double) duration; }

    public Optional<Double> getDurationInSeconds() {
        return Optional.ofNullable(durationInSeconds);
    }

    public Optional<Long> getDurationInTicks() {
        return Optional.ofNullable(durationInSeconds).map((it) -> Math.round(it * 20));
    }
}
