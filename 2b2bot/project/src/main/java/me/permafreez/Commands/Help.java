package me.permafreez.Commands;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {
	
	// Setting variables
	String CommandName = "Help";
	
	// MessageRecived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		// Get message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for the command in the message
		if (args[0].equalsIgnoreCase(Main.prefix + "help") || args[0].equalsIgnoreCase(Main.prefix + "h")) {
			
			// Building an embed
			EmbedBuilder help = new EmbedBuilder();
			help.setColor(Main.uColor);
			help.setTitle("Commands");
			help.setDescription("Here's a breif summary of our commands:");
			help.setThumbnail(Main.icon);
			// Command list starts here
			help.addField("!info", "Basic bot information", false);
			help.addField("!help", "Opens this list", false);
			help.addField("!alternatives", "Sends you the alternative command structures", false);
			help.addField("!queue", "2b2t queue information for both normal and prio queue", false);
			help.addField("!seen PLAYER", "Sends you information about the last known date the player joined.", false);
			help.addField("!thirdparty", "Information about the thirdparty apis used by " + Main.botName, false);
			help.addField("!stats PLAYER", "Information about players inclouding uuid, kills, deaths, joins, leves. Usage: \"!stats PLAYER\"", false);
			help.addField("!tablist", "Sends you the newest tablist (provided by.: tab.2b2t.dev)", false);
			help.addField("!lastkd PLAYER", "Info about a player's last death and last kill", false);
			help.addField("!lastkill PLAYER", "Info about a player's last kill", false);
			help.addField("!lastdeath PLAYER", "Info about a player's last death", false);
			
			
			// Sending the answer for the command
			event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
			event.getAuthor().openPrivateChannel().complete().sendMessage(help.build()).queue();
			
			// Sending the info about the conditions of the command to the console
			Main.CommandInfo(CommandName, event);
			
			// Clearing the embed
			help.clear();
		}
	}
}
