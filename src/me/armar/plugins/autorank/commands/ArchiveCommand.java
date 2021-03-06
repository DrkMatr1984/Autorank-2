package me.armar.plugins.autorank.commands;

import me.armar.plugins.autorank.Autorank;
import me.armar.plugins.autorank.commands.manager.AutorankCommand;
import me.armar.plugins.autorank.language.Lang;
import me.armar.plugins.autorank.util.AutorankTools;
import me.armar.plugins.autorank.util.AutorankTools.Time;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ArchiveCommand extends AutorankCommand {

	private final Autorank plugin;

	public ArchiveCommand(final Autorank instance) {
		this.setUsage("/ar archive <minimum>");
		this.setDesc("Archive data with a minimum");
		this.setPermission("autorank.archive");

		plugin = instance;
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd,
			final String label, final String[] args) {

		if (!plugin.getCommandsManager().hasPermission("autorank.archive",
				sender)) {
			return true;
		}

		int rate = -1;

		if (args.length != 2) {
			sender.sendMessage(ChatColor.RED + "You need to specify a time!");
			return true;
		}

		rate = AutorankTools.stringToTime(args[1], Time.MINUTES);

		if (rate <= 0) {
			sender.sendMessage(ChatColor.RED
					+ Lang.INVALID_FORMAT
							.getConfigValue("/ar archive 10d/10h/10m"));
			return true;
		}

		sender.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.YELLOW
				+ plugin.getPlaytimes().archive(rate) + "" + ChatColor.GREEN
				+ " records below " + ChatColor.YELLOW
				+ AutorankTools.timeToString(rate, Time.MINUTES)
				+ ChatColor.GREEN + ".");
		return true;
	}

}
