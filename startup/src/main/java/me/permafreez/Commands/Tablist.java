package me.permafreez.Commands;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.jsoup.Jsoup;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Tablist extends ListenerAdapter {
	
	// Setting variables
	String CommandName = "Tablist";
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting Message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for command
		if (args[0].equalsIgnoreCase(Main.prefix + "tablist") || args[0].equalsIgnoreCase(Main.prefix + "tl")) {
			
			// Creating UTC date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			// Importing image
			File file = new File("tablist.png");
			
			// Building embed
			EmbedBuilder tablist = new EmbedBuilder();
			tablist.setTitle("Current tablist of 2b2t");
			tablist.setDescription("UTC Time: " + sdf.format(new Date()));
			tablist.setColor(Main.uColor);
			tablist.setImage("attachment://tablist.png");
			tablist.setFooter("Data provided isfrom 2b2t.dev. This tablist is updated in every 3 minutes.");
			
			// Sending message
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(tablist.build())
				.addFile(file, "tablist.png")
				.queue();
			
			// Clearing embed
			tablist.clear();
			
			// CommandInfo
			Main.CommandInfo(CommandName, event);
		}
	}

}
