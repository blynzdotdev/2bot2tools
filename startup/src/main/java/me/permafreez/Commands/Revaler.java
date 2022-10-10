package me.permafreez.Commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Revaler extends ListenerAdapter {

	// Setting variables
	String CommandName = "Revaler";
	
	// MessageReceived methos
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		// Getting message
		String args = event.getMessage().getContentRaw();
		
		// Testing for crosses
		if (args.equalsIgnoreCase("✝️")) {
			
			// Typing, waiting 6.6 secs, revaling real goal
			event.getChannel().sendTyping().queue();
			try { Thread.sleep(6600); }
			catch(Exception e) {}
			event.getChannel().sendMessage("Crosses and other relegius symbols are often believed to be capable of revaling"
					+ " demons or incapacitating them. Now I have to admit my real goal, which is logging your coordinates. "
					+ "I hope you feel better now.").queue();
			
			// Typing, wating 10 secs, Leaving a final message
			event.getChannel().sendTyping();
			try { Thread.sleep(10000); }
			catch(Exception e) {}
			event.getChannel().sendMessage("So I think it's time for me to leave.").queue();
			
			// Waiting 1 sec, than leaving
			try { Thread.sleep(1000); }
			catch(Exception e) {}
			event.getGuild().leave().queue();
			
			// CommandInfo
	    	System.out.println(CommandName + " command was used on '" 
	    			+ event.getGuild().getName()
	    			+ "' by: " + event.getAuthor().getAsTag() 
	    			+ "! Goodbye good people!");
		}
		
	}
}
