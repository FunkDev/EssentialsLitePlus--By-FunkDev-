package tk.FunkDev.EssentialsLitePlus.Signs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import tk.FunkDev.EssentialsLitePlus.Core;

public class ColoredSigns implements Listener {
	
	/*
	 * This class has not been put to affect even though
	 * it seems as it is. 
	 */
	
	private Core core;
	public ColoredSigns(Core core) {
		this.core = core;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(core.getConfig().getString("Signs.Colored").equalsIgnoreCase("true")) {
			if(e.getPlayer().hasPermission("el+.signs.colored")) {
				e.getLine(1).replaceAll("&", "§");
				e.getLine(2).replaceAll("&", "§");
				e.getLine(3).replaceAll("&", "§");
				e.getLine(4).replaceAll("&", "§");
			}
		}
	}

}
