package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tk.FunkDev.EssentialsLitePlus.Core;

public class ItemInfo<ItemMetaKey> implements CommandExecutor {
	
	private Core core;
	public ItemInfo(Core core) {
		this.core = core;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("iteminfo")) {
			if(core.getConfig().getString("Commands.ItemInfo.Enabled").equalsIgnoreCase("true")) {
				if(p.hasPermission("el+.cmd.iteminfo")) {
					ItemStack item = p.getItemInHand();
					@SuppressWarnings("unchecked")
					ItemMetaKey itemeta = (ItemMetaKey) item.getItemMeta();
				    Material itemtype = item.getType();
					int itemid = itemtype.getId(); 
					p.sendMessage(ChatColor.GOLD + "ItemInfo:");
				    p.sendMessage(ChatColor.GOLD + "  ItemName: " + ChatColor.RED + itemtype + ":" + itemeta);
				    p.sendMessage(ChatColor.GOLD + "  ItemID: " + ChatColor.RED + itemid);
				} else {
					p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			} else {
				p.sendMessage(ChatColor.RED + "This command is disabled!");
			}
		}
		return false;
	}

}
