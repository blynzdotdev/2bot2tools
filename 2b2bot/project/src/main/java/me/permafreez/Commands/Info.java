package me.permafreez.Commands;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Info extends ListenerAdapter {
	
	// Setting variables
	String CommandName = "Info";
	
	
	// MessageRecived method
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
    	// Getting the message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for the command in the message
		if (args[0].equalsIgnoreCase(Main.prefix + "info") || args[0].equalsIgnoreCase(Main.prefix + "i")) {
			
			// Building an embed
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle(Main.botName + "'s bussiness card:");
			info.setDescription(Main.botName + " is a helping hand for 2b2t!");
			info.addField("Creator", "Permafreez", false);
			info.setColor(Main.uColor);
			info.addField("Commands", "For the commands of Popbot use \"!help\"!", false);
			info.setThumbnail(Main.icon);
			info.setFooter("Almost all data we provide is from third-party platforms. For more information use '!thirdparty'");
			
			// Reacting to the command
			event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
			event.getAuthor().openPrivateChannel().complete().sendMessage(info.build()).queue();
			
			// Sending the info about the conditions of the command to the console
			Main.CommandInfo(CommandName, event);
	    	
			//clearing the embed
	    	info.clear();
		}
	}
}
