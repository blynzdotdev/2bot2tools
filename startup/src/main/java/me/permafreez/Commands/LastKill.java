package me.permafreez.Commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LastKill extends ListenerAdapter {
	
	// Setting variables
	String CommanName = "Lastkill";
	
	// Scrapeing
	private String[] ScrapeLK(String[] args) {
		try {
			
			// Getting doc
			final Document doc = Jsoup.connect("https://api.2b2t.dev/stats?lastkill=" + args[1]).ignoreContentType(true).get();
			
			// Clearing doc
			String normal = doc.select("body").text();
			normal = normal.replace("[{", "");
			normal = normal.replace("}]", "");
			normal = normal.replace("\"", "");
			normal = normal.replace(":", ",");
			String[] normalA = normal.split(",");
			
			// Returning info
			return normalA;
		
		// Errors
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String[] error = null;
		return error;
	}

	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for command
		if (args[0].equalsIgnoreCase(Main.prefix +"lastkill") || args[0].equalsIgnoreCase(Main.prefix +"lk") || args[0].equalsIgnoreCase(Main.prefix +"lastk")) {
			
			// Testing for if command was ised properly
			if (args.length > 1) {
				
				// Getting info into a array
				String[] ld = ScrapeLK(args);
				
				// Testing for info's existence
				if (ld.length > 1) {
					
					// Building embed
					EmbedBuilder lastd = new EmbedBuilder();
					lastd.setColor(Main.uColor);
					
					lastd.setTitle("Last kill of " + ld[3] + " was found");
					lastd.addField("Username", ld[3], false);
					lastd.addField("Timestamp", ld[5] + " " + ld[7] + ":" + ld[8] + ":" + ld[9], false);
					lastd.addField("Kill Message", ld[11], false);
					lastd.setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/190.png");
					
					// Answering
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(lastd.build()).queue();
					lastd.clear();
				}
				
				// If user wasn't found
				if (ld.length <= 1) {
					
					// Building embed
					EmbedBuilder error = new EmbedBuilder();
					error.setTitle("User wasn't found");
					error.setDescription("I've never seen **" + args[1] + "** killing anyone");
					error.setThumbnail(Main.icon);
					error.setColor(Main.eColor);
					
					// Answering
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} 
				
			// Errors
			} else {
				
				// Answer
				event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
				event.getAuthor().openPrivateChannel().complete()
				.sendMessage(":x: Error occured while using command! Use the command like this: \"!lastkill PLAYER\"").queue();
			}
			
			// CommandInfo
			Main.CommandInfo(CommanName, event);
		}
	}
}
