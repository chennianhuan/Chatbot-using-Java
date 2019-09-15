import java.io.BufferedReader;
import org.jibble.pircbot.*;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

public class Bot extends PircBot {

	// Constructor
	public Bot() {
		this.setName("WeatherandWikiBot");
	}

	protected void onJoin(String channel, String sender, String login, String hostname) {
		sendMessage(channel, "Hello there");
		sendMessage(channel, "Enter weather for weather information");
		sendMessage(channel, "Enter wikipedia to search on wikipedia");
	}

	// Set flag for the user input
	Boolean flag = false;
	Boolean flag_2 = false;

	// Send message to the chatroom based on the user input
	public void onMessage(String channel, String sender, String login, String hostName, String message) {
		double temperature = 273.15; // Used to convert temp from Calvin to Celsius

		// If the user is looking for weather, ask the user for the location
		if (message.toLowerCase().contains("weather")) {

			String result;
			sendMessage(channel, "What city are you looking for:");
			flag = true;
		}

		// Connect to the weather api only if the user is looking for the weather
		else if (flag) {
			try {
				String weatherApi = "http://api.openweathermap.org/data/2.5/weather?q=";
				String weatherApiToken = "&APPID=ac0b1ef87e01bab6610205ae8f82d8e3";
				String newMessage = message.replaceAll(" ", "%20");

				// Get a Json format weather information
				String result = WebRequest.makeRequest(weatherApi, newMessage, weatherApiToken);
				// Parse the information
				double[] weatherInfo = WeatherApi.getWeather(result);
				// Send the weather info to the chatroom
				sendMessage(channel,
						"Temperature in " + message + " is " + Double.toString(Math.round(weatherInfo[0] - temperature))
								+ " °C, wind " + weatherInfo[1] + "m/s, clouds " + weatherInfo[2] + "%, "
								+ weatherInfo[3] + " hpa");

			} catch (IOException e) {
				sendMessage(channel, "The city is invalid");
			}
			// Set the flag back to false after the user is done with the weather info
			flag = false;
		}

		// Prompt user to enter search on wikipedia
		else if (message.toLowerCase().contains("wikipedia")) {
			sendMessage(channel, "What are you searching for on Wikipedia?");
			flag_2 = true;
		}

		// Connect to the wikipedia api only if the user asks for wikipedia
		else if (flag_2) {
			try {
				String newMessage = message.replaceAll(" ", "%20");
				String wikipediaApi = "https://en.wikipedia.org/w/api.php?";
				String wikipediaToken = "action=query&prop=extracts|extlinks&exintro=&explaintext=&titles=";
				String wikipediaToken_2 = "&format=json";

				// Connect to the api and get the Json format string info
				String result = WebRequest.makeRequest(wikipediaApi, wikipediaToken, newMessage, wikipediaToken_2);
				String[] wikiInfo = WikiApi.getWikiInfo(result);

				sendMessage(channel, "Extract: " + wikiInfo[0]);
				sendMessage(channel, "External link: " + wikiInfo[1]);

			} catch (Exception e) {

				sendMessage(channel, "Your search is not found on Wikipedia");
			}

			flag_2 = false;
		}

		else {
			sendMessage(channel, "The input is invalid, please enter weather or wikipedia first");
		}
	}
}
