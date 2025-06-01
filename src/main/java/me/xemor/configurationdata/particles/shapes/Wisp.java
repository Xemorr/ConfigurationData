package me.xemor.configurationdata.particles.shapes;

import com.fasterxml.jackson.annotation.JsonAlias;
import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.EntityShape;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class Wisp extends Shape implements EntityShape {
    //Similar to helix except
    //Example of the shape produced can be viewed here: https://gyazo.com/a2599daf5bc26b79bd9ec6cd545d06fb
    @JsonPropertyWithDefault
    private double radius = 0.5; //Defines radius of helix centred on players (x,y) coordinates.
    @JsonPropertyWithDefault
    private double speed = 1; //Defines how quickly animation moves around helix.
    @JsonPropertyWithDefault
    @JsonAlias("wave_frequency")
    private double waveFrequency = 1; //Can be seen as number of rotations helix makes around player in a set vertical distance
    @JsonPropertyWithDefault
    private double height = 2; //Defines how high above the players current y-location the helix should extend to.

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        double trigInput = (ConfigurationData.getArbitraryClock() *speed);
        double xCoordinate = location.getX() + radius * Math.cos(trigInput);
        double zCoordinate = location.getZ() + radius * Math.sin(trigInput);
        double cyclePosition = (Math.sin(ConfigurationData.getArbitraryClock() * waveFrequency) + 1)/2;
        double yCoordinate = cyclePosition*height + location.getY();
        Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
        wireframe.add(particleLocation);
        return wireframe;
    }
}
