package net.Abdymazhit.SkyWarsRanked.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Отвечает за отправки уведомлении (title)
 *
 * @version   10.08.2021
 * @author    Islam Abdymazhit
 */
public class Title {

    /**
     * Отправляет игроку уведомление
     * @param player Игрок
     * @param text Текст уведомления
     * @param fadeInTime Время появления
     * @param showTime Время показа
     * @param fadeOutTime Время исчезания
     */
    public static void sendTitle(Player player, String text, int fadeInTime, int showTime, int fadeOutTime) {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInTime, showTime, fadeOutTime);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
    }
}