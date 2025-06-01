package me.xemor.configurationdata.particles;

import com.fasterxml.jackson.annotation.JsonAlias;
import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.shapes.Point;
import me.xemor.configurationdata.particles.shapes.Shape;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Optional;

public class ParticleData {

    @JsonPropertyWithDefault
    private double duration = 2.5;
    @JsonPropertyWithDefault
    private Particle particle = Particle.PORTAL;
    @JsonPropertyWithDefault
    @JsonAlias("numberOfParticles")
    private int amount = 1;
    @JsonPropertyWithDefault
    private Shape shape = new Point();
    @JsonPropertyWithDefault
    private double extra = 0;
    @JsonPropertyWithDefault
    private OptionsData options = null;

    private Optional<Color> getColourForTick() {
        if (options == null) return Optional.empty();
        return Optional.ofNullable(options.getColours().get(ConfigurationData.getArbitraryClock() % options.getColours().size()));
    }

    public void spawnParticle(LivingEntity entity) { //Needs to be improved. Colour is calculated even when not in use.
        if (shape instanceof EntityShape entityShape) {
            List<Location> particleLocations = entityShape.getWireframe(entity);
            for (Location location : particleLocations) {
                Optional<Particle.DustOptions> dustOptions = getColourForTick().map((colour) -> new Particle.DustOptions(colour, options.getScale()));//Dust options are only applicable for the 'DUST' particle type.
                entity.getWorld().spawnParticle(particle, location, amount, 0, 0, 0, 0, dustOptions.orElse(null));
            }
        }
    }

    public void spawnParticle(Location location) {
        if (shape instanceof LocationShape locationShape) {
            List<Location> particleLocations = locationShape.getWireframe(location);
            for (Location particleLocation : particleLocations) {
                Optional<Particle.DustOptions> dustOptions = getColourForTick().map((colour) -> new Particle.DustOptions(colour, options.getScale()));//Dust options are only applicable for the 'DUST' particle type.
                location.getWorld().spawnParticle(particle, particleLocation, amount, 0, 0, 0, 0, dustOptions.orElse(null));
            }
        }
    }

    public int getDuration() { //For future use in plugins - not currently used.
        return (int) duration * 20;
    }

    public Particle getParticle() { //For future use in plugins - not currently used.
        return particle;
    }
}