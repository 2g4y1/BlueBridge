package de.mark225.bluebridge.core.config;

import de.bluecolored.bluemap.api.math.Color;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class BlueBridgeConfig {
    private static FileConfiguration config;
    private static Pattern rgbaRegex = Pattern.compile("[0-9a-f]{8}");
    private static Pattern rgbRegex = Pattern.compile("[0-9a-f]{6}");

    public static synchronized void setConfig(FileConfiguration config) {
        BlueBridgeConfig.config = config;
    }

    public static synchronized int updateInterval() {
        if (config == null) return 200;
        return config.getInt("updateInterval", 200);
    }

    public static synchronized boolean defaultRender() {
        if (config == null) return true;
        return config.getBoolean("defaultRender", true);
    }

    public static synchronized boolean defaultHideSets() {
        if (config == null) return false;
        return config.getBoolean("hideMarkerset", false);
    }

    public static synchronized boolean defaultDepthCheck() {
        if (config == null) return false;
        return config.getBoolean("defaultDepthCheck", false);
    }

    public static synchronized int renderHeight() {
        if (config == null) return 63;
        return config.getInt("renderHeight", 63);
    }

    public static synchronized double minDistance() {
        if (config == null) return 10.0;
        return config.getDouble("minDistance", 10.0);
    }

    public static synchronized double maxDistance() {
        if (config == null) return 500.0;
        return config.getDouble("maxDistance", 500.0);
    }

    public static synchronized Color defaultColor() {
        String rgba = "960087ff";
        if (config != null) {
            rgba = config.getString("defaultColor", "960087ff");
        }
        if (!rgbaRegex.matcher(rgba).matches())
            rgba = "960087ff";
        return new Color("#" + rgba);
    }

    public static synchronized Color defaultOutlineColor() {
        String rgb = "0060ff";
        if (config != null) {
            rgb = config.getString("defaultOutlineColor", "0060ff");
        }
        if (!rgbRegex.matcher(rgb).matches())
            rgb = "0060ff";
        return new Color("#" + rgb);
    }

    public static synchronized boolean debug() {
        if (config == null) return false;
        return config.getBoolean("debug", false);
    }

    public static synchronized List<String> excludedMaps() {
        if (config == null) return Collections.emptyList();
        return config.getStringList("excludedMaps");
    }
}
