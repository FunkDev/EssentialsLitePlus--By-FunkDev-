package tk.FunkDev.EssentialsLitePlus;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvseeListener implements Listener {
	
	@SuppressWarnings("unused")
	private Core core;
	public InvseeListener(Core core) {
		this.core = core;
	}
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getName().startsWith("Invsee: ")) {
        	e.setCancelled(true);
        }    
    }

}
