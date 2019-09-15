import org.jibble.pircbot.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ChatBot {

	public static void main(String[] args) throws Exception {

		Bot chatBot = makeConnection();

	}

	public static Bot makeConnection() throws Exception {
		Bot chatBot = new Bot();
		chatBot.setVerbose(true); // Enable debugging output
		chatBot.connect("irc.freenode.net"); // Connect to the irc server
		chatBot.joinChannel("#pircBotaaa"); // Join the pirBotaaa channel

		return chatBot;
	}

}
