package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class EssentialsLitePlus implements CommandExecutor {
	
	
	@SuppressWarnings("unused")
	private Core core;
	public EssentialsLitePlus(Core core) {
		this.core = core;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can open up a GUI!");
		}
		if(cmd.getName().equalsIgnoreCase("essentialsliteplus")) {
			if(p.hasPermission("el+.controlpanel")) {
				p.openInventory(Core.GUI);
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 60, 1);
			}
		}
		return false;
	}

}