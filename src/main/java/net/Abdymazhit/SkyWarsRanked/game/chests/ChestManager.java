package net.Abdymazhit.SkyWarsRanked.game.chests;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import net.Abdymazhit.SkyWarsRanked.game.chests.loot.LootGenerator;
import net.Abdymazhit.SkyWarsRanked.game.chests.loot.StandardLootGenerator;
import net.Abdymazhit.SkyWarsRanked.utils.Random;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер сундуков, отвечает за работу сундуков
 *
 * @version   13.08.2021
 * @author    Islam Abdymazhit
 */
public class ChestManager {

    /** Генератор лута */
    private final LootGenerator lootGenerator;

    /** Мистический сундук */
    private final MysteryChest mysteryChest;

    /** Хранит текущий открытый сундук игрока */
    private final Map<Player, Chest> currentOpenedPlayerChest;

    /** Хранит голограммы открытых сундуков */
    private final Map<Chest, ArmorStand> openedChestsHolograms;

    /** Хранит голограммы пустых сундуков */
    private final Map<Chest, ArmorStand> emptyChestsHolograms;

    /**
     * Инициализирует объекты менеджера
     */
    public ChestManager() {
        lootGenerator = new StandardLootGenerator();
        mysteryChest = new MysteryChest();
        currentOpenedPlayerChest = new HashMap<>();
        openedChestsHolograms = new HashMap<>();
        emptyChestsHolograms = new HashMap<>();
    }

    /**
     * Заполняет сундуки островов лутом
     */
    public void refillIslandChests() {
        for(Island island : Config.islands) {
            Inventory[] chests = island.getChests().stream().map(loc -> loc.getBlock().getState()).filter(state -> state instanceof Chest)
                    .map(state -> ((Chest) state).getInventory()).toArray(Inventory[]::new);

            for(Inventory inv : chests) {
                inv.clear();
            }

            int slot = 0;
            for(ItemStack item : lootGenerator.basic()) {
                if(chests.length != 0) {
                    chests[slot % chests.length].setItem(slot / chests.length, item);
                    slot++;
                }
            }

            for(Inventory inventory : chests) {
                ItemStack[] contents = inventory.getContents();
                shuffleArray(contents);
                inventory.setContents(contents);
            }
        }
    }

    /**
     * Заполняет базовые сундуки лутом
     */
    public void refillBasicChests() {
        for(Location location : Config.basicChests) {
            BlockState state = location.getBlock().getState();
            if(state instanceof Chest) {
                Inventory inventory = ((Chest) state).getInventory();
                ItemStack[] contents = new ItemStack[inventory.getSize()];

                int slot = 0;
                for(ItemStack item : lootGenerator.basic()) {
                    if(Random.random.nextBoolean()) {
                        slot++;
                        contents[slot] = item;
                    }
                }

                shuffleArray(contents);
                inventory.setContents(contents);
            }
        }
    }

    /**
     * Заполняет центральные сундуки лутом
     */
    public void refillMiddleChests() {
        for(Location location : Config.middleChests) {
            BlockState state = location.getBlock().getState();
            if(state instanceof Chest) {
                Inventory inventory = ((Chest) state).getInventory();
                ItemStack[] contents = new ItemStack[inventory.getSize()];

                int slot = 0;
                for(ItemStack item : lootGenerator.middle()) {
                    contents[slot++] = item;
                }

                shuffleArray(contents);
                inventory.setContents(contents);
            }
        }
        lootGenerator.rotation++;
    }

    /**
     * Заполняет мистический сундук лутом
     */
    public void refillMysteryChest(Inventory inventory) {
        ItemStack[] contents = new ItemStack[inventory.getSize()];
        int slot = 0;
        for(ItemStack item : lootGenerator.mystic()) {
            contents[slot++] = item;
        }
        shuffleArray(contents);
        inventory.setContents(contents);
    }

    /**
     * Перемешивает массив любого типа
     * @param array Массив
     * @param <T> Тип массива
     */
    private <T> void shuffleArray(T[] array) {
        for(int i = array.length - 1; i > 0; --i) {
            int index = Random.random.nextInt(i + 1);
            T a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    /**
     * Получает мистический сундук
     * @return Мистический сундук
     */
    public MysteryChest getMysteryChest() {
        return mysteryChest;
    }

    /**
     * Устанавливает текущий открытый сундук игроку
     * @param player Игрок
     * @param chest Сундук
     */
    public void setCurrentOpenedPlayerChest(Player player, Chest chest) {
        currentOpenedPlayerChest.put(player, chest);
    }

    /**
     * Получает текущий открытый сундук игрока
     * @param player Игрок
     * @return Текущий открытый сундук игрока
     */
    public Chest getCurrentOpenedPlayerChest(Player player) {
        return currentOpenedPlayerChest.get(player);
    }

    /**
     * Добавляет голограмму открытого сундука
     */
    public void addOpenedChestHologram(Chest chest) {
        if(!openedChestsHolograms.containsKey(chest)) {
            if(SkyWarsRanked.getGameManager().getGameStageManager().getTimeBeforeRefillingChests() != 0) {
                Location location = chest.getLocation().add(0.5, -0.5, 0.5);

                ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

                armorStand.setGravity(false);
                armorStand.setCanPickupItems(false);
                armorStand.setCustomName("§a" + timeToString(SkyWarsRanked.getGameManager().getGameStageManager().getTimeBeforeRefillingChests()));
                armorStand.setCustomNameVisible(true);
                armorStand.setVisible(false);

                openedChestsHolograms.put(chest, armorStand);
            }
        }
    }

    /**
     * Обновляет таймер голограмм открытых сундуков
     * @param time Время до перезаполнения сундуков
     */
    public void updateOpenedChestsHologramsTimer(int time) {
        for(ArmorStand armorStand : openedChestsHolograms.values()) {
            armorStand.setCustomName("§a" + timeToString(time));
        }
    }

    /**
     * Удаляет голограммы открытых сундуков
     */
    public void removeOpenedChestsHolograms() {
        for(Chest chest : openedChestsHolograms.keySet()) {
            ArmorStand armorStand = openedChestsHolograms.get(chest);
            armorStand.remove();
        }
        openedChestsHolograms.clear();
    }

    /**
     * Добавляет голограмму пустого сундука
     */
    public void addEmptyChestHologram(Chest chest) {
        if(!emptyChestsHolograms.containsKey(chest)) {
            Location location = chest.getLocation().add(0.5, -0.8, 0.5);

            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

            armorStand.setGravity(false);
            armorStand.setCanPickupItems(false);
            armorStand.setCustomName("§cПусто");
            armorStand.setCustomNameVisible(true);
            armorStand.setVisible(false);

            emptyChestsHolograms.put(chest, armorStand);
        }
    }

    /**
     * Удаляет голограмму пустого сундука
     */
    public void removeEmptyChestHologram(Chest chest) {
        if(emptyChestsHolograms.containsKey(chest)) {
            ArmorStand armorStand = emptyChestsHolograms.get(chest);
            armorStand.remove();
        }
        emptyChestsHolograms.remove(chest);
    }

    /**
     * Удаляет голограммы пустых сундуков
     */
    public void removeEmptyChestsHolograms() {
        for(Chest chest : emptyChestsHolograms.keySet()) {
            ArmorStand armorStand = emptyChestsHolograms.get(chest);
            armorStand.remove();
        }
        emptyChestsHolograms.clear();
    }

    /**
     * Конвертировать время типа Integer в тип String
     * @param time Время типа Integer
     * @return Время в типе String
     */
    public String timeToString(int time) {
        int min = time / 60 % 60;
        int sec = time % 60;

        return String.format("%02d:%02d", min, sec);
    }
}