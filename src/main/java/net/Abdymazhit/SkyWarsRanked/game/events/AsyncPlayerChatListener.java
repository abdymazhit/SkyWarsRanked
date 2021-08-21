package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerVimeInfo;
import net.Abdymazhit.SkyWarsRanked.enums.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Отвечает за событие отправки сообщения в чат
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class AsyncPlayerChatListener implements Listener {

    /**
     * Событие отправки сообщения в чат
     */
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        PlayerVimeInfo playerVimeInfo = SkyWarsRanked.getGameManager().getPlayerInfo(player).getPlayerVimeInfo();

        String msgColor = "&f";
        if(playerVimeInfo.getRank().equals(Rank.CHIEF)) {
            msgColor = "&a";
            event.setMessage(colored(event.getMessage()));
        } else if(playerVimeInfo.getRank().equals(Rank.IMMORTAL)) {
            event.setMessage(colored(event.getMessage()));
        }

        // Чат для всех игроков
        if(!SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
            if(playerVimeInfo.getGuildColor() != null && playerVimeInfo.getGuildTag() != null) {
                event.setFormat(colored("&7<" + playerVimeInfo.getGuildColor() + playerVimeInfo.getGuildTag() + "&7> &7%1$s&r&7: " + msgColor) + "%2$s");
            } else {
                event.setFormat(colored("&7%1$s&r&7: " + msgColor) + "%2$s");
            }
        }
        // Отдельный чат для зрителей
        else {
            for(Player p : SkyWarsRanked.getGameManager().getPlayers()) {
                event.getRecipients().remove(p);
            }

            if(playerVimeInfo.getGuildColor() != null && playerVimeInfo.getGuildTag() != null) {
                event.setFormat(colored("&7(Наблюдатели) <" + playerVimeInfo.getGuildColor() + playerVimeInfo.getGuildTag() + "&7> &7%1$s&r&7: " + msgColor) + "%2$s");
            } else {
                event.setFormat(colored("&7(Наблюдатели) %1$s&r&7: " + msgColor) + "%2$s");
            }
        }
    }

    /**
     * Получает цветной текст
     * @param text Текст
     * @return Цветной текст
     */
    private String colored(String text) {
        if (text == null) {
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}