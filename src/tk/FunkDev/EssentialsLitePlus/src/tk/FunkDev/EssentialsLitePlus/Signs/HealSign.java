package tk.FunkDev.EssentialsLitePlus.Signs;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import tk.FunkDev.EssentialsLitePlus.Core;

public class HealSign implements Listener {
	
	/*
	 * This class has not been put to affect even though
	 * it seems as it is. 
	 */
	
	private Core core;
	public HealSign(Core core) {
		this.core = core;
	}
	
	@EventHandler
    public void onSignChange(SignChangeEvent e) {
		if(core.getConfig().getString("Signs.Heal").equalsIgnoreCase("true")) {
			if(e.getPlayer().hasPermission("el+.signs.create.heal")) {
				if (e.getLine(0).equalsIgnoreCase("[Heal]")) {
	                e.setLine(0, "§1[Heal]");
	            }
			}
		}
    }
   
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
    	if(core.getConfig().getString("Signs.Heal").equalsIgnoreCase("true")) {
    		if(e.getPlayer().hasPermission("el+.signs.use.heal")) {
    			if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
                if (e.getClickedBlock().getState() instanceof Sign) {
                        Sign s = (Sign) e.getClickedBlock().getState();
                        if (s.getLine(0).equalsIgnoreCase("§3[Heal]")) {
                                e.getPlayer().setHealth(20);
                                e.getPlayer().sendMessage(ChatColor.GREEN + "You were healed!");
                        }
                }
    		}
    	}
    }
}
