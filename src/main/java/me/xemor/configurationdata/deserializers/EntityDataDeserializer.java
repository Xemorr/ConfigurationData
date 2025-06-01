package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import me.xemor.configurationdata.AttributesData;
import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.deserializers.text.RegistryDeserializer;
import me.xemor.configurationdata.entity.EntityComponentRegistry;
import me.xemor.configurationdata.entity.EntityData;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class EntityDataDeserializer extends JsonDeserializer<EntityData> {

    @Override
    public EntityData deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        if (node.isNull() || node.isMissingNode()) {
            return new EntityData(); // Avoids Jackson misinterpreting structure
        }

        NamespacedKey key = NamespacedKey.fromString(node.path("type").asText("ZOMBIE").toLowerCase());

        EntityType type = key == null ? EntityType.ZOMBIE : Registry.ENTITY_TYPE.get(key);

        if (type == null) ConfigurationData.getLogger().severe("No entity type found. Possible are: %s".formatted(Registry.ENTITY_TYPE.stream().map((entityType) -> entityType.getKey().getKey()).reduce((s1, s2) -> s1 + "," + s2).orElse("")));

        EntityData builder = new EntityData()
                .setType(type)
                .setNameTag(node.path("nameTag").asText())
                .shouldDespawn(node.path("shouldDespawn").asBoolean(true))
                .setCustomNameVisible(node.path("customNameVisible").asBoolean(false))
                .setSilent(node.path("silent").asBoolean(false))
                .setVisualFire(node.path("visualFire").asBoolean(false));

        if (node.has("attributes")) {
            builder.setAttributes(parser.getCodec().readValue(node.path("attributes").traverse(parser.getCodec()), new TypeReference<Map<Attribute, Double>>() {}));
        }
        if (node.has("passenger")) {
            // hoping this handles null sensibly without causing infinite recursion
            builder.setPassenger(context.readValue(node.path("passenger").traverse(parser.getCodec()), EntityData.class));
        }

        List<? extends Class<? extends EntityComponent>> relevantComponentClasses = EntityComponentRegistry.getEntityComponentDataClasses(type.getEntityClass());

        Stream<EntityComponent> components = relevantComponentClasses
                .stream()
                .map((entityComponentDataClazz) -> {
                    try {
                        return context.readTreeAsValue(node, entityComponentDataClazz);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        if (node.get("extra") != null) {
            ConfigurationData.getLogger().warning("Please move away from using the extra section within entities");
            Stream<EntityComponent> extraComponents = relevantComponentClasses
                    .stream()
                    .map((entityComponentDataClazz) -> {
                        try {
                            return context.readTreeAsValue(node.path("extra"), entityComponentDataClazz);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            components = Stream.concat(components, extraComponents);
        }

        builder.setEntitySpecificAttributes(components.toList());
        return builder;
    }
}
