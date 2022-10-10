package me.permafreez;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Test2 {
	
	public static void main(String[] args) {
		JDABuilder jdaBuilder = JDABuilder.createDefault(Token.uToken);
		try {		
		JDA jda = jdaBuilder.build();
		Thread.sleep(1500);
		jda.getTextChannelById("865329215725502465").sendMessage("Heló").queue();
		
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
