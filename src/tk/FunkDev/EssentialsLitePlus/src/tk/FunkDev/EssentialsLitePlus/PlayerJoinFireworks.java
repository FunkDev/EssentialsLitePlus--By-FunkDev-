package tk.FunkDev.EssentialsLitePlus;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class PlayerJoinFireworks implements Listener {
	
	private Core core;
	public PlayerJoinFireworks(Core core) {
		this.core = core;
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
		if(core.getConfig().getString("PlayerJoinFireworks").equalsIgnoreCase("true")) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
                public void run() {
                        Firework f = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
                       
                        FireworkMeta fm = f.getFireworkMeta();
                        fm.addEffect(FireworkEffect.builder()
                                        .flicker(false)
                                        .trail(true)
                                        .with(Type.BALL_LARGE)
                                        .withColor(Color.GREEN)
                                        .withFade(Color.BLUE)
                                        .build());
                        fm.setPower(3);
                        f.setFireworkMeta(fm);
                }
            }, 20L);
		}
    }

}
