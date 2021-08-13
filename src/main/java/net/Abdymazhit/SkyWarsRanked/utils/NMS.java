package net.Abdymazhit.SkyWarsRanked.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Отвечает за работу NMS
 *
 * @version   13.08.2021
 * @author    Islam Abdymazhit
 */
public class NMS {

    /**
     * Отправляет игроку сообщение в центр экрана
     * @param player Игрок
     * @param message Сообщение
     * @param fadeInTime Время появления
     * @param showTime Время показа
     * @param fadeOutTime Время исчезания
     */
    public static void sendTitle(Player player, String message, int fadeInTime, int showTime, int fadeOutTime) {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInTime, showTime, fadeOutTime);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
    }

    /**
     * Отправляет игроку сообщение в панель действии
     * @param player Игрок
     * @param message Сообщение
     */
    public static void sendActionBar(Player player, String message) {
        IChatBaseComponent chatMessage = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chatMessage, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }
}