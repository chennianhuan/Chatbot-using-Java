import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherApi {

	public static double[] getWeather(String json) {

		// Create a JsonParser object to parse a Json object
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();
		String cityName = object.get("name").getAsString();
		JsonObject main = object.getAsJsonObject("main");
		JsonObject wind = object.getAsJsonObject("wind");
		JsonObject clouds = object.getAsJsonObject("clouds");

		double temp = main.get("temp").getAsDouble();
		double speed = wind.get("speed").getAsDouble();
		double all = clouds.get("all").getAsDouble();
		double pressure = main.get("pressure").getAsDouble();
		final int WEATHERSIZE = 4;

		// Store the weather infomation into an array
		double[] weatherInfo = new double[WEATHERSIZE];
		weatherInfo[0] = temp;
		weatherInfo[1] = speed;
		weatherInfo[2] = all;
		weatherInfo[3] = pressure;

		return weatherInfo;
	}
}
