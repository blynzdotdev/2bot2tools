package me.permafreez.Commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Seen extends ListenerAdapter {
	
	// Setting 
	Main main = new Main();
	String CommandName = "Seen";
	
	// Scraping last player join date
	private String SeenP(String[] args) {
		
		try {
			
			// Getting document
			final Document doc = Jsoup.connect("https://api.2b2t.dev/seen?username=" + args[1]).ignoreContentType(true).get();
			
			// Cleaning up document
			String normal = doc.select("body").text();
			normal = normal.replace("[{\"seen\":\"", "");
			normal = normal.replace("\"}]", "");
			//normal = normal.replace("[]", "never");
			
			// Answering
			return normal;
			
		} // Catch
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// In case something goes wrong
		return "Seen ERROR";
	}
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting message
		String[] args = event.getMessage().getContentRaw().split(" ");

		// Testing for command
		if(args[0].equalsIgnoreCase(Main.prefix + "seen")) {
			// Creating embed
			EmbedBuilder seen = new EmbedBuilder();
			seen.setColor(main.uColor);
			
			if (args.length == 2) {
				
				// Testing for user never seen
				if (SeenP(args).equals("[]")) {
					// Editing embed
					seen.setTitle("User wasn't found");
					seen.setDescription("I've never seen **" + args[1] + "**");
					seen.setThumbnail(main.icon);
					seen.setColor(main.eColor);
					
					// Answering if user WAS NEVER seen
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(seen.build()).queue();
				} 
				
				else {
					// Editing embed
					seen.setTitle("User found");
					seen.setDescription("**" + args[1] + "** was last seen:");
					seen.addField(SeenP(args), "This time is measured by the EDT timezone which is 4 hours behind UTC time.", false);
					// Setting thumbnail for the head of asked player & testing for missing variable
					seen.setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/190.png");
					
					//Answering if user WAS seen
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(seen.build()).queue();
				}
			} 
			// Incorrect command usage
			else {
				event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
				event.getAuthor().openPrivateChannel().complete()
				.sendMessage(":x: Error occured while using command! Use the command like this: \"!seen PLAYER\"").queue();
			}
			
			// Clearing embed
			seen.clear();
			
			// Sending command info to console
			main.CommandInfo(CommandName, event);
		}
	}
}
