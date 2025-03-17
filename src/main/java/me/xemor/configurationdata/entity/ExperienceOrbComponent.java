package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceOrbComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private int experience = 1;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        ExperienceOrb experienceOrb = (ExperienceOrb) entity;
        experienceOrb.setExperience(experience);
    }
}
