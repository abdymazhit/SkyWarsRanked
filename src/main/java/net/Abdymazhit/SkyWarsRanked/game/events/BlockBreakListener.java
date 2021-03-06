package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameState;
import net.Abdymazhit.SkyWarsRanked.game.chests.Registry;
import net.Abdymazhit.SkyWarsRanked.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.function.Supplier;

/**
 * Отвечает за событие ломания блока игроком
 *
 * @version   20.08.2021
 * @author    Islam Abdymazhit
 */
public class BlockBreakListener implements Listener {

    /**
     * Событие ломания блока игроком
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.WAITING) || SkyWarsRanked.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }

        if(!event.isCancelled()) {
            Player player = event.getPlayer();

            // Увеличить количество сломанных блоков игрока за матч
            SkyWarsRanked.getGameManager().getPlayerInfo(player).addBlocksBroken();

            // Выполнить действия ломания кастомных руд
            Material type = event.getBlock().getType();
            switch (type) {
                case GOLD_ORE: {
                    Location loc = event.getBlock().getLocation().add(0.5, 0.5, 0.5);

                    if (Random.random.nextDouble() < 0.1) {
                        loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
                    }

                    if (Random.random.nextBoolean()) {
                        loc.getWorld().dropItem(loc, (ItemStack) Random.of(new List[] { Registry.HELMETS, Registry.CHESTPLATES, Registry.LEGGINGS, Registry.BOOTS, Registry.PICKAXES, Registry.AXES, Registry.SWORDS, Registry.SPADES }));
                    } else {
                        ArrayList<Supplier<ItemStack>> items = new ArrayList<>();
                        items.addAll(Registry.FOODS);
                        items.addAll(Registry.IRON_ORE_ITEMS);
                        items.addAll(Registry.BLOCKS);
                        items.addAll(Arrays.asList(() -> new ItemStack(Material.ARROW, 16 + Random.random.nextInt(15)), () -> new ItemStack(Material.EGG, 2 + Random.random.nextInt(3)), () -> new ItemStack(Material.TNT, 5 + Random.random.nextInt(11)), () -> new ItemStack(Material.WATER_BUCKET), () -> new ItemStack(Material.LAVA_BUCKET), () -> new ItemStack(Material.GOLDEN_APPLE, 1 + Random.random.nextInt(1)), () -> new ItemStack(Material.ENDER_PEARL), () -> new ItemStack(Material.FISHING_ROD)));

                        Supplier<ItemStack> itemSupplier = () -> Objects.requireNonNull(Random.of(items)).get();

                        loc.getWorld().dropItem(loc, itemSupplier.get());
                        
                        if (Random.random.nextBoolean()) {
                            loc.getWorld().dropItem(loc, itemSupplier.get());
                        }
                        
                        if (Random.random.nextBoolean()) {
                            loc.getWorld().dropItem(loc, itemSupplier.get());
                        }
                    }
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                }
                case IRON_ORE: {
                    Location loc = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
                    LinkedList<Supplier<ItemStack>> list = new LinkedList<>(Registry.IRON_ORE_ITEMS);
                    loc.getWorld().dropItem(loc, list.remove(Random.random.nextInt(list.size())).get());
                    loc.getWorld().dropItem(loc, list.remove(Random.random.nextInt(list.size())).get());
                    if (Random.random.nextBoolean()) {
                        loc.getWorld().dropItem(loc, list.get(Random.random.nextInt(list.size())).get());
                    }
                    list.clear();
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                }
                case LAPIS_ORE: {
                    PotionEffectType effect = Random.of(Registry.EFFECT_TYPES);
                    int duration = Random.random.nextInt(40, 60) * 20;
                    if(effect != null) {
                        player.addPotionEffect(effect.createEffect(duration, 0));
                    }
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                }
                case DIAMOND_ORE: {
                    Location loc = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
                    if (Random.random.nextDouble() < 0.05) {
                        loc.getWorld().dropItem(loc, new ItemStack(Material.ENDER_PEARL));
                    } else if (Random.random.nextDouble() < 0.05) {
                        loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND));
                    } else {
                        loc.getWorld().dropItem(loc, Objects.requireNonNull(Random.of(Registry.DIAMOND_ITEMS)).clone());
                    }
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                }
                case REDSTONE_ORE:
                case GLOWING_REDSTONE_ORE: {
                    if (Random.random.nextInt(100) <= 50) {
                        if (player.getMaxHealth() < 40.0) {
                            player.setMaxHealth(player.getMaxHealth() + 2.0);
                            player.setHealth(player.getHealth() + 2.0);
                        }
                    } else {
                        player.setHealth(Math.max(1.0, player.getHealth() - 2.0));
                        player.setMaxHealth(Math.max(10.0, player.getMaxHealth() - 2.0));
                        int noDamageTicks = player.getNoDamageTicks();
                        player.damage(0.0);
                        player.setNoDamageTicks(noDamageTicks);
                    }
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                }
                case EMERALD_ORE: {
                    player.setLevel(player.getLevel() + (Random.random.nextBoolean() ? 1 : 2));
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                }
                case ENDER_CHEST: {
                    if (Config.mysteryChest.equals(event.getBlock().getLocation())) {
                        event.setCancelled(true);
                    }
                    break;
                }
            }
        }
    }
}