package net.Abdymazhit.SkyWarsRanked;

import net.Abdymazhit.SkyWarsRanked.customs.Island;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Отвечает за работу с конфиг файлом
 *
 * @version   05.08.2021
 * @author    Islam Abdymazhit
 */
public class Config {

    /** Мир, где будет проходить игра */
    public static World world;

    /** Местоположение, где будут появляться игроки при заходе в игру */
    public static Location lobbyLocation;

    /** Название карты */
    public static String mapName;

    /** Высота на которой будут появляться зрители */
    public static int respawnY;

    /** Список местоположений обычных сундуков */
    public static List<Location> basicChests;

    /** Список местоположений центральных сундуков */
    public static List<Location> middleChests;

    /** Местоположение мистического сундука */
    public static Location mysteryChest;

    /** Список местоположений детматча */
    public static List<Location> deathmatchSpawns;

    /** Список {@link Island острова} игроков */
    public static List<Island> islands;

    /**
     * Загружает данные с конфиг файла
     * @throws IllegalArgumentException Если количество островов
     * <p>
     * больше чем количество местоположений детматча
     */
    public static void load() {
        FileConfiguration config = SkyWarsRanked.getInstance().getConfig();
        config.options().copyDefaults(true);
        SkyWarsRanked.getInstance().saveConfig();

        world = Bukkit.getWorld(config.getString("world"));
        lobbyLocation = getLocation(config.getString("lobby"));
        mapName = config.getString("map");

        respawnY = config.getInt("respawnY");

        basicChests = new ArrayList<>();
        for (Object chestLocation : config.getList("basicChests")) {
            basicChests.add(getLocation(chestLocation));
        }

        middleChests = new ArrayList<>();
        for (Object chestLocation : config.getList("middleChests")) {
            middleChests.add(getLocation(chestLocation));
        }

        mysteryChest = getLocation(config.getString("mysteryChest"));

        deathmatchSpawns = new ArrayList<>();
        for (Object chestLocation : config.getList("deathmatchSpawns")) {
            deathmatchSpawns.add(getLocation(chestLocation));
        }

        islands = new ArrayList<>();
        for (Map<?, ?> map : config.getMapList("islands")) {
            int id = islands.size() + 1;
            Location spawn = getLocation(String.valueOf(map.get("spawn")));

            List<Location> chests = new ArrayList<>();
            for (String chestLocation : (List<String>) map.get("chests")) {
                chests.add(getLocation(chestLocation));
            }

            islands.add(new Island(id, spawn, chests));
        }

        SkyWarsRanked.getInstance().getLogger().info("Загружено " + Config.islands.size() + " островов.");
        if (Config.islands.size() > Config.deathmatchSpawns.size()) {
            throw new IllegalArgumentException("Не достаточно местоположений deathmatchSpawns. Количество: " + Config.deathmatchSpawns.size() + ". Необходимо: " + Config.islands.size());
        }
    }

    /**
     * Получает местоположение из объекта конфиг файла
     * @return Местоположение из объекта конфиг файла
     */
    private static Location getLocation(Object object) {
        String stringLocation = (String) object;
        String[] locArgs = stringLocation.split(",");

        double x = Double.parseDouble(locArgs[0]);
        double y = Double.parseDouble(locArgs[1]);
        double z = Double.parseDouble(locArgs[2]);

        if (locArgs.length == 5) {
            float yaw = Float.parseFloat(locArgs[3]);
            float pitch = Float.parseFloat(locArgs[4]);
            return new Location(world, x, y, z, yaw, pitch);
        }

        return new Location(world, x, y, z);
    }
}