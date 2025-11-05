package de.mark225.bluebridge.worldguard.config;

import de.mark225.bluebridge.core.addon.AddonConfig;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BlueBridgeWGConfig extends AddonConfig {

    private static BlueBridgeWGConfig instance;

    public static BlueBridgeWGConfig getInstance() {
        return instance;
    }

    public BlueBridgeWGConfig(FileConfiguration config) {
        super();
        instance = this;
        init(config);
    }

    public synchronized String htmlPreset() {
        return config.getString("htmlPreset", "$(name)");
    }

    public synchronized boolean defaultExtrude() {
        return config.getBoolean("defaultExtrude", false);
    }

    /**
     * Returns a list of compiled regex patterns that should be excluded from rendering.
     * The config key is `excludePatterns` and expects a list of regex strings.
     */
    public synchronized List<Pattern> excludePatterns() {
        List<String> patterns = config.getStringList("excludePatterns");
        if (patterns == null || patterns.isEmpty()) return Collections.emptyList();
        return patterns.stream().map(p -> Pattern.compile(p)).collect(Collectors.toList());
    }

}
