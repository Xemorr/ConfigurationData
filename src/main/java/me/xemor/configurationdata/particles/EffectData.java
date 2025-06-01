package me.xemor.configurationdata.particles;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class EffectData { //Used in ESParticles to build effects connected to each player.

    @JsonPropertyWithDefault
    private final List<ParticleData> particles = new ArrayList<>();
    @JsonPropertyWithDefault
    private String name;

    public void spawnEffect(LivingEntity entity) {
        for (ParticleData particle : particles) {
            particle.spawnParticle(entity);
        }
    }

    public String getName() {
        return name;
    }

    public List<ParticleData> getParticles() {
        return particles;
    }
}
