package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;

public class ExperienceOrbComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final int experience = 1;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        ExperienceOrb experienceOrb = (ExperienceOrb) entity;
        experienceOrb.setExperience(experience);
    }
}
