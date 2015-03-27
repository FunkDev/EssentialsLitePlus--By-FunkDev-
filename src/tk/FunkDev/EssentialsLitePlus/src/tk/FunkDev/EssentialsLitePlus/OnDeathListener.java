package tk.FunkDev.EssentialsLitePlus;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class OnDeathListener implements Listener {
	
	private Core core;
	public OnDeathListener(Core core) {
		this.core = core;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(core.getConfig().getString("OnDeathMisc").equalsIgnoreCase("true")) {
			Player p = e.getEntity();
			World world = p.getWorld();
		    Location loc = p.getLocation();
		    
		    world.strikeLightning(loc);
		    
		    if(!(p.getType() == EntityType.PLAYER)) {
		    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
	                public void run() {
	                        Firework f = (Firework) e.getEntity().getWorld().spawn(e.getEntity().getLocation(), Firework.class);
	                       
	                        FireworkMeta fm = f.getFireworkMeta();
	                        fm.addEffect(FireworkEffect.builder()
	                                        .flicker(false)
	                                        .trail(true)
	                                        .with(Type.BALL)
	                                        .withColor(Color.RED)
	                                        .withFade(Color.ORANGE)
	                                        .build());
	                        fm.setPower(2);
	                        f.setFireworkMeta(fm);
	                }
	             }, 25L);
		    }
		}
	}

}
