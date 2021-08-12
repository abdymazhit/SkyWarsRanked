package net.Abdymazhit.SkyWarsRanked.game.chests;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import net.Abdymazhit.SkyWarsRanked.game.chests.loot.LootGenerator;
import net.Abdymazhit.SkyWarsRanked.game.chests.loot.StandardLootGenerator;
import net.Abdymazhit.SkyWarsRanked.utils.Random;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Менеджер сундуков, отвечает за заполнение сундуков
 *
 * @version   12.08.2021
 * @author    Islam Abdymazhit
 */
public class ChestManager {

    /** Генератор лута */
    private static final LootGenerator lootGenerator = new StandardLootGenerator();

    /** Менеджер мистического сундука, отвечает за мистический сундук */
    private final MysteryChestManager mysteryChestManager;

    /**
     * Инициализирует объекты менеджера
     */
    public ChestManager() {
        mysteryChestManager = new MysteryChestManager();
    }

    /**
     * Заполняет сундуки островов лутом
     */
    public void refillIslandChests() {
        for(Island island : Config.islands) {
            Inventory[] chests = island.getChests().stream().map(loc -> loc.getBlock().getState()).filter(state -> state instanceof org.bukkit.block.Chest)
                    .map(state -> ((org.bukkit.block.Chest) state).getInventory()).toArray(Inventory[]::new);

            for(Inventory inv : chests) {
                inv.clear();
            }

            int slot = 0;
            for(ItemStack item : lootGenerator.basic()) {
                chests[slot % chests.length].setItem(slot / chests.length, item);
                slot++;
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
            if(state instanceof org.bukkit.block.Chest) {
                Inventory inventory = ((org.bukkit.block.Chest) state).getInventory();
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
            if(state instanceof org.bukkit.block.Chest) {
                Inventory inventory = ((org.bukkit.block.Chest) state).getInventory();
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
     * Получает менеджер мистического сундука
     * @return Менеджер мистического сундука
     */
    public MysteryChestManager getMysteryChestManager() {
        return mysteryChestManager;
    }
}