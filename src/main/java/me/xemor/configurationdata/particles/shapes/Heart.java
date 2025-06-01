package me.xemor.configurationdata.particles.shapes;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.EntityShape;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Heart extends Shape implements EntityShape {
    //Heart shape above players head.
    //Example of the shape produced can be viewed here: https://gyazo.com/870054a859b5576c6369a62ca5437686

    @JsonPropertyWithDefault
    private double height = 2;
    @JsonPropertyWithDefault
    private double frequency = 0.05;
    @JsonPropertyWithDefault
    private double size = 1;

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        Vector perpendicularVector = getPerpendicularVector(entity.getEyeLocation().getDirection());
        //Vector used to ensure heart remains above players head, facing forward as their orientation changes.
        for (double theta = 0; theta < 2*Math.PI; theta += frequency) {
            double radius = 3 - 2*Math.sin(theta) + Math.cos(2*theta) - 2*Math.abs(Math.cos(theta));
            //Polar equation that can represent heart like shape.
            double horizontalOffset = radius * Math.cos(theta) * size;
            double verticalOffset = radius * Math.sin(theta) * size + height;
            Vector offset = perpendicularVector.clone().multiply(horizontalOffset).setY(verticalOffset);
            Location particleLocation = location.clone().add(offset);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
