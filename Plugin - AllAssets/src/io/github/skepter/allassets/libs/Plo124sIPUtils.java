/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.libs;

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