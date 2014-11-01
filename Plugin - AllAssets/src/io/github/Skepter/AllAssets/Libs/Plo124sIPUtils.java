/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Libs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Plo124sIPUtils {

	static HashMap<String, JSONObject> ipStorage = new HashMap<String, JSONObject>();

	public static String ipToTime(final String ip) {
		int offset = 0;
		if (ipStorage.containsKey(ip))
			offset = Integer.parseInt((String) ipStorage.get(ip).get("timeZone"));
		else {
			final String url = "http://api.ipinfodb.com/v3/ip-city/?key=d7859a91e5346872d0378a2674821fbd60bc07ed63684c3286c083198f024138&ip=" + ip + "&format=json";
			final JSONObject object = stringToJSON(getUrlSource(url));
			final String timezone = (String) object.get("timeZone");
			if ((timezone != null) && (timezone.length() > 3)) {
				offset = Integer.parseInt(timezone.substring(0, timezone.length() - 3));
				ipStorage.put(ip, object);
			} else
				return "Error: Cannot parse time";
		}
		final Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		time.add(Calendar.HOUR_OF_DAY, offset);
		final DateFormat formatter = new SimpleDateFormat("EEEEEE hh:mm");
		formatter.setCalendar(time);
		String date = formatter.format(time.getTime());
		final DateFormat formatter2 = new SimpleDateFormat("aa");
		formatter2.setCalendar(time);
		date += formatter2.format(time.getTime()).toLowerCase();
		return date;
	}

	public static String getCityName(final String ip) {
		JSONObject obj = null;
		if (ipStorage.containsKey(ip))
			obj = ipStorage.get(ip);
		else {
			final String url = "http://api.ipinfodb.com/v3/ip-city/?key=d7859a91e5346872d0378a2674821fbd60bc07ed63684c3286c083198f024138&ip=" + ip + "&format=json";
			final JSONObject object = stringToJSON(getUrlSource(url));
			obj = object;
			ipStorage.put(ip, object);
		}
		return (String) obj.get("cityName");
	}

	public static String getStateName(final String ip) {
		JSONObject obj = null;
		if (ipStorage.containsKey(ip))
			obj = ipStorage.get(ip);
		else {
			final String url = "http://api.ipinfodb.com/v3/ip-city/?key=d7859a91e5346872d0378a2674821fbd60bc07ed63684c3286c083198f024138&ip=" + ip + "&format=json";
			final JSONObject object = stringToJSON(getUrlSource(url));
			obj = object;
			ipStorage.put(ip, object);
		}
		return (String) obj.get("regionName");
	}

	public static String getCountryName(final String ip) {
		JSONObject obj = null;
		if (ipStorage.containsKey(ip))
			obj = ipStorage.get(ip);
		else {
			final String url = "http://api.ipinfodb.com/v3/ip-city/?key=d7859a91e5346872d0378a2674821fbd60bc07ed63684c3286c083198f024138&ip=" + ip + "&format=json";
			final JSONObject object = stringToJSON(getUrlSource(url));
			obj = object;
			ipStorage.put(ip, object);
		}
		String country = (String) obj.get("countryName");
		if (country.contains(","))
			country = country.split(",")[0];
		return country;
	}

	public static String getCountryCode(final String ip) {
		JSONObject obj = null;
		if (ipStorage.containsKey(ip))
			obj = ipStorage.get(ip);
		else {
			final String url = "http://api.ipinfodb.com/v3/ip-city/?key=d7859a91e5346872d0378a2674821fbd60bc07ed63684c3286c083198f024138&ip=" + ip + "&format=json";
			final JSONObject object = stringToJSON(getUrlSource(url));
			obj = object;
			ipStorage.put(ip, object);
		}
		final String country = (String) obj.get("countryCode");
		return country;
	}

	public static JSONObject stringToJSON(final String json) {
		return (JSONObject) JSONValue.parse(json);
	}

	private static String getUrlSource(final String url) {
		URL url2 = null;
		try {
			url2 = new URL(url);
		} catch (final MalformedURLException e) {
		}
		URLConnection yc = null;
		try {
			yc = url2.openConnection();
		} catch (final IOException e) {
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
		} catch (final IOException e) {
		}
		String inputLine;
		final StringBuilder a = new StringBuilder();
		try {
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
		} catch (final IOException e) {
		}
		try {
			in.close();
		} catch (final IOException e) {
		}

		return a.toString();
	}

	public static String getWeather(final String ip) {
		String weather = "Unknown";
		final String search = getCityName(ip) + "," + getCountryCode(ip);
		final String url = "http://api.openweathermap.org/data/2.5/weather?q=" + search;
		final JSONObject object = stringToJSON(getUrlSource(url));
		weather = (String) ((JSONObject) ((JSONArray) object.get("weather")).get(0)).get("main");
		return weather;
	}
}