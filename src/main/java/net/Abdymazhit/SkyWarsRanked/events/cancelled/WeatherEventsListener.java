package net.Abdymazhit.SkyWarsRanked.events.cancelled;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Отменяет события связанные с погодой
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class WeatherEventsListener implements Listener {

    /**
     * Событие удара молнии
     */
    @EventHandler
    public void onLightningStrike(LightningStrikeEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие изменения состояния грома
     */
    @EventHandler
    public void onThunderChange(ThunderChangeEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие изменения погоды
     */
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}