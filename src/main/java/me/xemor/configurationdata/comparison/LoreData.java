package me.xemor.configurationdata.comparison;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LoreData {

    private final List<Pattern> patternLore;

    public LoreData(String variable, ConfigurationSection configurationSection) {
        patternLore = configurationSection.getStringList(variable).stream().map(Pattern::compile).collect(Collectors.toList());
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

}
