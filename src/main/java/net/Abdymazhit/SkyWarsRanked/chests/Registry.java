package net.Abdymazhit.SkyWarsRanked.chests;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.function.Supplier;

/**
 * Реестр предметов
 *
 * @version   10.08.2021
 * @author    Islam Abdymazhit
 */
public class Registry {

    /** Алмазные предметы */
    public static final List<ItemStack> DIAMOND_ITEMS;

    /** Типы эффектов */
    public static final List<PotionEffectType> EFFECT_TYPES;

    /** Предметы железной руды */
    public static final List<Supplier<ItemStack>> IRON_ORE_ITEMS;

    /** Предметы еды */
    public static final List<Supplier<ItemStack>> FOODS;

    /** Предметы блоков */
    public static final List<Supplier<ItemStack>> BLOCKS;

    /** Предметы кирок */
    public static final List<ItemStack> PICKAXES;

    /** Предметы топоров */
    public static final List<ItemStack> AXES;

    /** Предметы мечей */
    public static final List<ItemStack> SWORDS;

    /** Предметы лопат */
    public static final List<ItemStack> SPADES;

    /** Предметы шлемов */
    public static final List<ItemStack> HELMETS;

    /** Предметы нагрудников */
    public static final List<ItemStack> CHESTPLATES;

    /** Предметы штанов */
    public static final List<ItemStack> LEGGINGS;

    /** Предметы ботинков */
    public static final List<ItemStack> BOOTS;

    static {
        SplittableRandom random = new SplittableRandom();
        
        DIAMOND_ITEMS = Arrays.asList(new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_AXE), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS));
        EFFECT_TYPES = Arrays.asList(PotionEffectType.SPEED, PotionEffectType.SLOW, PotionEffectType.FAST_DIGGING, PotionEffectType.SLOW_DIGGING, PotionEffectType.INCREASE_DAMAGE, PotionEffectType.JUMP, PotionEffectType.CONFUSION, PotionEffectType.REGENERATION, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.FIRE_RESISTANCE, PotionEffectType.INVISIBILITY, PotionEffectType.BLINDNESS, PotionEffectType.NIGHT_VISION, PotionEffectType.HUNGER, PotionEffectType.WEAKNESS, PotionEffectType.POISON, PotionEffectType.WITHER, PotionEffectType.HEALTH_BOOST, PotionEffectType.ABSORPTION, PotionEffectType.SATURATION);
        IRON_ORE_ITEMS = Arrays.asList(() -> new ItemStack(Material.ARROW, random.nextInt(4, 10)), () -> new ItemStack(Material.STICK, random.nextInt(2, 4)), () -> new ItemStack(Material.FLINT, random.nextInt(2, 4)), () -> new ItemStack(Material.STRING, random.nextInt(3, 4)), () -> new ItemStack(Material.FEATHER, random.nextInt(2, 4)), () -> new ItemStack(Material.COAL, random.nextInt(3, 4)), () -> new ItemStack(Material.DIAMOND, random.nextInt(1, 2)), () -> new ItemStack(Material.DIAMOND, random.nextInt(1, 2)), () -> new ItemStack(Material.IRON_INGOT, random.nextInt(1, 3)), () -> new ItemStack(Material.IRON_INGOT, random.nextInt(1, 3)));
        FOODS = Arrays.asList(() -> new ItemStack(Material.CARROT_ITEM, random.nextInt(8, 32)), () -> new ItemStack(Material.APPLE, random.nextInt(8, 32)), () -> new ItemStack(Material.BREAD, random.nextInt(8, 32)), () -> new ItemStack(Material.BAKED_POTATO, random.nextInt(8, 32)), () -> new ItemStack(Material.COOKED_FISH, random.nextInt(8, 32)), () -> new ItemStack(Material.COOKED_BEEF, random.nextInt(8, 32)), () -> new ItemStack(Material.COOKED_CHICKEN, random.nextInt(8, 32)));
        BLOCKS = Arrays.asList(() -> new ItemStack(Material.STONE, random.nextInt(20, 40)), () -> new ItemStack(Material.WOOD, random.nextInt(20, 40)), () -> new ItemStack(Material.BRICK, random.nextInt(20, 40)), () -> new ItemStack(Material.COBBLESTONE, random.nextInt(20, 40)), () -> new ItemStack(Material.STONE, random.nextInt(20, 40)), () -> new ItemStack(Material.DIRT, random.nextInt(20, 40)));
        PICKAXES = Arrays.asList(new ItemStack(Material.STONE_PICKAXE), new ItemStack(Material.IRON_PICKAXE), new ItemStack(Material.GOLD_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE));
        AXES = Arrays.asList(new ItemStack(Material.STONE_AXE), new ItemStack(Material.IRON_AXE), new ItemStack(Material.GOLD_AXE), new ItemStack(Material.DIAMOND_AXE));
        SWORDS = Arrays.asList(new ItemStack(Material.STONE_SWORD), new ItemStack(Material.IRON_SWORD), new ItemStack(Material.GOLD_SWORD), new ItemStack(Material.DIAMOND_SWORD));
        SPADES = Arrays.asList(new ItemStack(Material.STONE_SPADE), new ItemStack(Material.IRON_SPADE), new ItemStack(Material.GOLD_SPADE), new ItemStack(Material.DIAMOND_SPADE));
        HELMETS = Arrays.asList(new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.CHAINMAIL_HELMET), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.GOLD_HELMET), new ItemStack(Material.IRON_HELMET));
        CHESTPLATES = Arrays.asList(new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.GOLD_CHESTPLATE), new ItemStack(Material.IRON_CHESTPLATE));
        LEGGINGS = Arrays.asList(new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.GOLD_LEGGINGS), new ItemStack(Material.IRON_LEGGINGS));
        BOOTS = Arrays.asList(new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.CHAINMAIL_BOOTS), new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.GOLD_BOOTS), new ItemStack(Material.IRON_BOOTS));
    }
}