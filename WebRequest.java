import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebRequest {

	// Various parameter to accept the unknown number of string
	public static String makeRequest(String myApiUrl, String... args) throws IOException {

		String myApiToken = "";
		for (int i = 0; i < args.length; i++) {
			myApiToken += args[i];
		}

		// Create an url object
		URL url = new URL(myApiUrl + myApiToken);

		// Create an HttpURLConnection obejct that represents a connection to the
		// remote object that referred to by the url
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// The processing form is idempotent, use GET
		// If modify the database, use POST
		conn.setRequestMethod("GET");

		// BufferReader buffers read operations for a better performance
		// InputStreamReader reads bytes and decodes them into characters, then result
		// is passed to the bufferReader
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		// Convert rd variable to a String
		String result = rd.lines().collect(Collectors.joining());

		return result;
	}
}
