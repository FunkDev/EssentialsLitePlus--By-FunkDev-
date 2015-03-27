package tk.FunkDev.EssentialsLitePlus;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerInfo implements Listener {

	private Core core;
	public PlayerInfo(Core core) {
		this.core = core;
	}
	
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(!p.hasPlayedBefore()) core.getConfig().set(p.getUniqueId().toString(), p.getName());
		core.getConfig().set(p.getUniqueId().toString() + ".name", p.getName());
		
	}

}
