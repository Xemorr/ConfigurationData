package me.xemor.configurationdata;

import java.util.logging.Logger;

public class ConfigurationData {

    private static Logger logger;

    static {
        logger = Logger.getLogger("XemorConfiguration");
    }

    public static Logger getLogger() {
        return logger;
    }


}
