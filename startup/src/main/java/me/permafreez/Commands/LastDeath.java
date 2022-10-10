package me.permafreez.Commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LastDeath extends ListenerAdapter {
	
	// Setting variables
	String CommanName = "Lastdeath";
	
	// Scrapeing 
	private String[] ScrapeLD(String[] args) {
		try {
			
			// Getting doc
			final Document doc = Jsoup.connect("https://api.2b2t.dev/stats?lastdeath=" + args[1]).ignoreContentType(true).get();
			
			// Clearing doc
			String normal = doc.select("body").text();
			normal = normal.replace("[{", "");
			normal = normal.replace("}]", "");
			normal = normal.replace("\"", "");
			normal = normal.replace(":", ",");
			String[] normalA = normal.split(",");
			
			// Answering
			return normalA;
			
		} // If something goes wrong 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		// If something goes REALLY wrong
		String[] error = null;
		return error;
	}
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for command
		if (args[0].equalsIgnoreCase(Main.prefix + "lastdeath") || args[0].equalsIgnoreCase(Main.prefix + "ld") || args[0].equalsIgnoreCase(Main.prefix + "ld")) {
			// If the command is right
			if (args.length > 1) {
				
				// Get the array
				String[] ld = ScrapeLD(args);
				
				// If there is a result
				if (ld.length > 1) {
					
					// Building embed
					EmbedBuilder lastd = new EmbedBuilder();
					lastd.setColor(Main.uColor);
					
					lastd.setTitle("Last death of " + ld[3] + " was found");
					lastd.addField("Username", ld[3], false);
					lastd.addField("Timestamp", ld[5] + " " + ld[7] + ":" + ld[8] + ":" + ld[9], false);
					lastd.addField("Death Message", ld[11], false);
					lastd.setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/190.png");
					
					// Answering command
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(lastd.build()).queue();
					lastd.clear();
				}
				// If there is no result
				if (ld.length <= 1) {
					
					// Building embed
					EmbedBuilder error = new EmbedBuilder();
					error.setTitle("User wasn't found");
					error.setDescription("I've never seen **" + args[1] + "** dying");
					error.setThumbnail(Main.icon);
					error.setColor(Main.eColor);
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} 
				// If the command wasn't right
			} else {
				event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
				event.getAuthor().openPrivateChannel().complete()
				.sendMessage(":x: Error occured while using command! Use the command like this: \"!lastdeath PLAYER\"").queue();
			}
			
			// Command info
			Main.CommandInfo(CommanName, event);
		}
	}
}
