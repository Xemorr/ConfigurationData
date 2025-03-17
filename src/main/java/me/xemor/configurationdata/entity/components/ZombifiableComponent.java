package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.EntityData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.PiglinAbstract;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZombifiableComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private boolean immuneToZombification = false;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        if (entity instanceof PiglinAbstract piglinAbstract) {
            piglinAbstract.setImmuneToZombification(immuneToZombification);
        }
        else if (entity instanceof Hoglin hoglin) {
            hoglin.setImmuneToZombification(immuneToZombification);
        }
    }
}
