package me.xemor.configurationdata.comparison;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@JsonDeserialize(using = LoreData.LoreDataDeserializer.class)
public class LoreData {

    private List<Pattern> patternLore;
    private final static LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    public LoreData(List<String> lore) {
        patternLore = lore
                .stream()
                .map(string -> legacySerializer.serialize(MiniMessage.miniMessage().deserialize(string)))
                .map(Pattern::compile)
                .collect(Collectors.toList());
    }

    public boolean matches(List<String> lore) {
        if (lore == null) lore = Collections.emptyList();
        if (patternLore.size() > 0 && patternLore.size() < lore.size()) {
            return false;
        }
        for (int i = 0; i < patternLore.size(); i++) {
            String line;
            if (i >= lore.size())  {
                line = "";
            } else {
                line = lore.get(i);
            }
            Pattern pattern = patternLore.get(i);
            if (!pattern.matcher(line).matches()) return false;
        }
        return true;
    }

    public static class LoreDataDeserializer extends JsonDeserializer<LoreData> {
        @Override
        public LoreData deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            List<String> lore = new ArrayList<>();
            JsonNode node = jsonParser.readValueAsTree();
            for (JsonNode element : node) {
                String value = jsonParser.getCodec().treeToValue(element, String.class);
                lore.add(value);
            }
            return new LoreData(lore);
        }
    }

}
