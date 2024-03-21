package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;

public class ExperienceOrbData extends EntityData {

    private final int experience;

    public ExperienceOrbData(ConfigurationSection configurationSection) {
        super(configurationSection);

        experience = configurationSection.getInt("experience", 1);
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        ExperienceOrb experienceOrb = (ExperienceOrb) entity;
        experienceOrb.setExperience(experience);
    }
}
