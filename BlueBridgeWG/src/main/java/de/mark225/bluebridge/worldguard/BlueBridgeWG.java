package de.mark225.bluebridge.worldguard;

import de.mark225.bluebridge.core.addon.AddonRegistry;
import de.mark225.bluebridge.worldguard.addon.BlueBridgeWGAddon;
import de.mark225.bluebridge.worldguard.addon.WorldGuardIntegration;
import de.mark225.bluebridge.worldguard.config.BlueBridgeWGConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class BlueBridgeWG extends JavaPlugin {

    private static BlueBridgeWG instance;
    private static BlueBridgeWGAddon addon;
    private WorldGuardIntegration integration;

    public static BlueBridgeWG getInstance() {
        return instance;
    }

    public WorldGuardIntegration getWGIntegration() {
        return integration;
    }

    public BlueBridgeWGAddon getAddon() {
        return addon;
    }

    @Override
    public void onLoad() {
        instance = this;
        updateConfig();
        integration = new WorldGuardIntegration();
        if (!integration.init()) {
            getLogger().severe("========================================");
            getLogger().severe("Failed to initialize WorldGuard integration!");
            getLogger().severe("This plugin MUST be loaded during server startup.");
            getLogger().severe("Do NOT use /plugman load or similar commands!");
            getLogger().severe("Add this plugin to your plugins folder and restart the server.");
            getLogger().severe("========================================");
            // Plugin will continue but with limited functionality
        }
        addon = new BlueBridgeWGAddon();
        AddonRegistry.register(addon);
    }

    public void updateConfig() {
        saveDefaultConfig();
        reloadConfig();
        new BlueBridgeWGConfig(getConfig());
    }

}
