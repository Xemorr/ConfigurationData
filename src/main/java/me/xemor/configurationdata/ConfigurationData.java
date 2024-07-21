package me.xemor.configurationdata;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ConfigurationData {

    private static Logger logger;
    private static JavaPlugin plugin;

    static {
        logger = Logger.getLogger("XemorConfiguration");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static JavaPlugin getPlugin() { return plugin; }
    public static void setup(JavaPlugin newPlugin) {
        plugin = newPlugin;
    }
}
