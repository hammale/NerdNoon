package me.hammale.noon;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class noon extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	private final noonWeather weather = new noonWeather(this);
	
	public boolean active = false;
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[NerdNoon] Version: " + pdfFile.getVersion() + " Enabled!");
		PluginManager pm = getServer().getPluginManager();
	    pm.registerEvent(Event.Type.WEATHER_CHANGE, weather, Event.Priority.Normal, this);	
	}
	
	  public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args){
			if(cmd.getName().equalsIgnoreCase("noon")){
				if(active == false){
					sender.sendMessage(ChatColor.GREEN + "Activating NerdNoon...");
					World w = getServer().getWorld("coliseum");
					w.setTime(2000);
					moniterTime(w);
					w.setStorm(false);
					active = true;
				}else{
					active = false;
					getServer().getScheduler().cancelTasks(this);
					sender.sendMessage(ChatColor.RED + "Stopping NerdNoon...");
				}
			}
			return true;	
	  }
	
	  public void moniterTime(final World w){
		  getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			  public void run() {
				  w.setTime(0);
			  }
			  }, 0, 10000);
	  }
	  
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[NerdNoon] Version: " + pdfFile.getVersion() + " Disabled!");
	}
	
}
