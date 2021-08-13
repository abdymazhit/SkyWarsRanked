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
 * @version   13.08.2021
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
        SkyWarsRanked.getInstance().saveDefaultConfig();

        world = Bukkit.getWorld(config.getString("world"));
        lobbyLocation = getLocation(config.getString("lobby"));
        mapName = config.getString("name");

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
        for (Map<?, ?> island : config.getMapList("islands")) {
            int id = islands.size() + 1;
            Location spawn = getLocation(island.get("spawn"));

            List<Location> chests = new ArrayList<>();
            for (Object chestLocation : (List) island.get("chests")) {
                chests.add(getLocation(chestLocation));
            }

            islands.add(new Island(id, spawn, chests));
        }

        if(Config.islands.size() != 8 && Config.islands.size() != 10 && Config.islands.size() != 12 && Config.islands.size() != 16 && Config.islands.size() != 20) {
            throw new IllegalArgumentException("Неверный формат игры! Вам нужно изменить количество островов! Текущее количество островов: " +
                    Config.islands.size() + ". Доступные форматы игры: 1x8, 1x10, 1x12, 1x16, 1x20");
        }

        if (Config.islands.size() != Config.deathmatchSpawns.size()) {
            throw new IllegalArgumentException("Количество местоположений точек детматча должно совпадать с количеством островов! Текущее количество: " +
                    Config.deathmatchSpawns.size() + ". Необходимо: " + Config.islands.size());
        }

        for(Island island : islands) {
            if(island.getChests().size() != 3) {
                throw new IllegalArgumentException("Количество сундуков в каждом острове должно быть 3! Id острова " +
                        island.getId() + ", количество сундуков: " + island.getChests().size());
            }
        }

        SkyWarsRanked.getInstance().getLogger().info("Загружено " + Config.islands.size() + " островов.");
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