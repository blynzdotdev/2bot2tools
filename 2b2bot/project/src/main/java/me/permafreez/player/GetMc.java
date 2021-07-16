package me.permafreez.player;

import java.sql.Connection;
import java.sql.DriverManager;

import me.permafreez.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GetMc extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		if (args[0].equalsIgnoreCase(Main.prefix + "getmc")) {
			
			String url = "jdbc:sqlite:/home/dani1/test/test.sql";
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(url);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			
			
		}
	}
}
