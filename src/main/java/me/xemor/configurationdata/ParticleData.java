package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Particle;

public class ParticleData {

    @JsonProperty
    private Particle particle = Particle.TOTEM_OF_UNDYING;
    @JsonProperty
    private int numberOfParticles = 1;

    public Particle getParticle() {
        return particle;
    }

    public int getNumberOfParticles() {
        return numberOfParticles;
    }

}
