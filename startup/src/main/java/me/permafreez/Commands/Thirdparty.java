package me.permafreez.Commands;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Thirdparty extends ListenerAdapter {
	
	// Setting variables
	String CommandName = "Thirdparty";
	Main main = new Main();
	
	// MessageRecived Method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		// Getting message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for command
		if (args[0].equalsIgnoreCase(Main.prefix + "thirdparty") || args[0].equalsIgnoreCase(Main.prefix + "thirdp")) {
			// Building Embed
			EmbedBuilder thirdparty = new EmbedBuilder();
			thirdparty.setColor(main.uColor);
			thirdparty.setTitle("Thirdparty softwares");
			thirdparty.setDescription("Here you can see all the thirdparty apis used by " + main.botName);
			thirdparty.addField("2b2t.dev", "https://api.2b2t.dev/", false);
			thirdparty.addField("2b2t.io", "https://2b2t.io/api/", false);
			thirdparty.setThumbnail(main.icon);
			
			// Answering to command
			event.getAuthor().openPrivateChannel().complete().sendMessage(thirdparty.build()).queue();
			
			// Getting CommandInfo
			main.CommandInfo(CommandName, event);
			// Clearing Embed
			thirdparty.clear();
		}
	}

}
