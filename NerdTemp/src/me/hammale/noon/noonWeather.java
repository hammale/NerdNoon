package me.hammale.noon;

import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherListener;

public class noonWeather extends WeatherListener {

	public noon plugin;
	
    public noonWeather(noon instance) {
    	plugin = instance;
    }

    public void onWeatherChange(WeatherChangeEvent e){
    	if(plugin.active == true && e.getWorld().getName().equals("coliseum")){
    		if(e.toWeatherState() == true){
    			e.setCancelled(true);
    		}
    	}
    }
}