package me.permafreez.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LongId extends ListenerAdapter{

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		System.out.println(event.getChannel().getId());
	}
}
