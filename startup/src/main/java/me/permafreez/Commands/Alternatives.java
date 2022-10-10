package me.permafreez.Commands;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Alternatives extends ListenerAdapter {
	
	// CommandName
	String CommandName = "Alternatives";
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting the message
		String args = event.getMessage().getContentRaw();
		
		// Testing for command
		if (args.equalsIgnoreCase(Main.prefix + "alternatives") || args.equalsIgnoreCase(Main.prefix + "alters")) {
			// Building embed
			EmbedBuilder alters = new EmbedBuilder();
			
			alters.setColor(Main.uColor);
			alters.setTitle("Command alternatives using " + Main.botName);
			alters.setThumbnail(Main.icon);
			
			// Contents here
			alters.addField("!info", "!i", false);
			alters.addField("!help", "!h", false);
			alters.addField("!alternatives", "!alters", false);
			alters.addField("!queue", "!q", false);
			alters.addField("!thirdparty", "!thirdp", false);
			alters.addField("!tablist", "!tl", false);
			alters.addField("!lastkd", "!lkd", false);
			alters.addField("!lastkill", "!lk, !lastk", false);
			alters.addField("!lastdeath", "!ld, !lasd", false);
			
			// Answering
			event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
			event.getAuthor().openPrivateChannel().complete().sendMessage(alters.build()).queue();
			alters.clear();
			
			// CommandInfo
			Main.CommandInfo(CommandName, event);
		}
	}

}
