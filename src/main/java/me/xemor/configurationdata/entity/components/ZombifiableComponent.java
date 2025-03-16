package me.xemor.configurationdata.entity.components;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.PiglinAbstract;

public class ZombifiableComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final boolean immuneToZombification = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        if (entity instanceof PiglinAbstract piglinAbstract) {
            piglinAbstract.setImmuneToZombification(immuneToZombification);
        }
        else if (entity instanceof Hoglin hoglin) {
            hoglin.setImmuneToZombification(immuneToZombification);
        }
    }
}
