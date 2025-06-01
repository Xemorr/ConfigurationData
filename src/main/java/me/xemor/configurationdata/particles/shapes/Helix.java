package me.xemor.configurationdata.particles.shapes;

import com.fasterxml.jackson.annotation.JsonAlias;
import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.EntityShape;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class Helix extends Shape implements EntityShape {
    //Creates animated helix.
    //Example of the shape produced can be viewed here: https://gyazo.com/ead409d61c8fc0bdf22997c1c83d05f4

    @JsonPropertyWithDefault
    private double radius = 0.5;
    @JsonPropertyWithDefault
    private double interval = 0.05;
    @JsonPropertyWithDefault
    private double speed = 1;
    @JsonPropertyWithDefault
    @JsonAlias("wave_frequency")
    private double waveFrequency = 1;
    @JsonPropertyWithDefault
    private double height = 2;

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        for (double y = 0; y < height; y+= interval) {
            double trigInput = y*waveFrequency*2*Math.PI + ConfigurationData.getArbitraryClock() *speed;
            double xCoordinate = location.getX() + radius * Math.cos(trigInput);
            double zCoordinate = location.getZ() + radius * Math.sin(trigInput);
            double yCoordinate = y + location.getY();
            Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
