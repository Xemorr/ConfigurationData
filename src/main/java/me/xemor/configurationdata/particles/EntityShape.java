package me.xemor.configurationdata.particles;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public interface EntityShape {

    List<Location> getWireframe(LivingEntity entity);
}
