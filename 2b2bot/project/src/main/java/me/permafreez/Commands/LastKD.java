package me.permafreez.Commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LastKD extends ListenerAdapter {
	
	// Getting info from api.2b2t.dev - method - about Death
	private String[] ScrapeLD(String[] args) {
		try {
			// Getting doc
			final Document doc = Jsoup.connect("https://api.2b2t.dev/stats?lastdeath=" + args[1]).ignoreContentType(true).get();
			
			// Cleaning up doc
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
	
	// Getting info from api.2b2t.dev - method - about kill
	private String[] ScrapeLK(String[] args) {
		try {
			
			// Getting doc
			final Document doc = Jsoup.connect("https://api.2b2t.dev/stats?lastkill=" + args[1]).ignoreContentType(true).get();
			
			// Cleaning up doc
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

	// CommandName
	String CommandName = "LastKD";
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for command
		if (args[0].equalsIgnoreCase(Main.prefix + "lastkd") || args[0].equalsIgnoreCase(Main.prefix + "lkd")) {

			// Testing for command lenght
			if (args.length == 2) {
				
				// Storeing info in arrays
				String[] ld = ScrapeLD(args);
				String[] lk = ScrapeLK(args);
				
				// Testing for last kill's existence
				if (lk.length > 1) {
					
					// Building embed
					EmbedBuilder lastk = new EmbedBuilder();
					lastk.setColor(Main.uColor);
					
					lastk.setTitle("Last kill of " + lk[3] + " was found");
					lastk.addField("Username", lk[3], false);
					lastk.addField("Timestamp", lk[5] + " " + ld[7] + ":" + lk[8] + ":" + ld[9], false);
					lastk.addField("Kill Message", lk[11], false);
					lastk.setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/190.png");
					
					// Answering
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(lastk.build()).queue();
					lastk.clear();
				}
				
				// Testing for last death's existence
				if (ld.length > 1) {
					
					// Building embed
					EmbedBuilder lastd = new EmbedBuilder();
					lastd.setColor(Main.uColor);
					
					lastd.setTitle("Last death of " + ld[3] + " was found");
					lastd.addField("Username", ld[3], false);
					lastd.addField("Timestamp", ld[5] + " " + ld[7] + ":" + ld[8] + ":" + ld[9], false);
					lastd.addField("Death Message", ld[11], false);
					lastd.setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/190.png");
					
					// Answering
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(lastd.build()).queue();
					lastd.clear();
				} 
				
				// If neither was found
				if (ld.length <= 1 && lk.length <= 1) {
					
					// Building embed
					EmbedBuilder error = new EmbedBuilder();
					error.setTitle("User wasn't found");
					error.setDescription("I've never seen **" + args[1] + "** killing anyone or dying");
					error.setThumbnail(Main.icon);
					error.setColor(Main.eColor);
					
					// Answering
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				}
			} // If command wasn't used properly
			else {
				
				// Answering
				event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
				event.getAuthor().openPrivateChannel().complete()
				.sendMessage(":x: Error occured while using command! Use the command like this: \"!lastkd PLAYER\"").queue();
			}
			
			// CommandInfo
			Main.CommandInfo(CommandName, event);
		}
	}
}
