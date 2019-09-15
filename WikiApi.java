import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WikiApi {

	public static String[] getWikiInfo(String json) {

		final int ARRAY_SIZE = 2;
		String[] wikiInfo = new String[ARRAY_SIZE];

		// Create a JsonParse object to parse a JsonObject
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();

		// Fetch the information in query and pages and store them as JsonObject
		JsonObject query = object.getAsJsonObject("query");
		JsonObject pages = query.getAsJsonObject("pages");

		String key = null;

		// Different searches have unique page ids
		for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {

			// this gets the dynamic keys
			key = entry.getKey();
		}

		// Get the JsonObject that the page id refers to
		JsonObject numberObject = pages.get(key).getAsJsonObject();
		String extract = numberObject.get("extract").getAsString();

		String newExtract = extract.replaceAll("\\(.*?\\) ?", "");

		if (numberObject.has("extlinks")) {
			JsonArray extLinksObject = numberObject.get("extlinks").getAsJsonArray();
			JsonElement extlinks = extLinksObject.get(0).getAsJsonObject().get("*");
			String link = extlinks.getAsString();

			// Store the requested page's extract and link into the array
			wikiInfo[0] = newExtract;
			wikiInfo[1] = link;
		}

		else {
			wikiInfo[0] = "Sorry, Your search title is not valid";
			wikiInfo[1] = "Your search does not have external links";
		}

		return wikiInfo;
	}

}
