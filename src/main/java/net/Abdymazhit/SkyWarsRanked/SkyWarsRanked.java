package net.Abdymazhit.SkyWarsRanked;

import org.bukkit.plugin.java.JavaPlugin;

public class SkyWarsRanked extends JavaPlugin {

    private static SkyWarsRanked instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public SkyWarsRanked getInstance() {
        return instance;
    }
}