package com.ztech.parser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import org.json.*;

public class RSStoJSON {

	private static Logger logger = Logger.getLogger(RSStoJSON.class.getName());

	public JSONArray getJsonArray(String newsCategory) {
		URL rssURL;
		JSONObject jsonObj;
		JSONArray jsonArray = null;
		try {
			rssURL = new URL(
					"https://news.google.com/news/rss/headlines/section/topic/" + newsCategory + "?ned=us&hl=en&gl=US");
			BufferedReader br = new BufferedReader(new InputStreamReader(rssURL.openStream()));
			StringBuilder rssStringBuilder = new StringBuilder("");
			String line;
			while ((line = br.readLine()) != null) {
				rssStringBuilder.append(line);
			}
			jsonObj = XML.toJSONObject(rssStringBuilder.toString());
			JSONObject object = (JSONObject) jsonObj.get("rss");
			object = (JSONObject) object.get("channel");
			jsonArray = (JSONArray) object.get("item");
			return jsonArray;
		} catch (MalformedURLException e1) {
			logger.warning("The URL is invalid or improper.");
		} catch (JSONException e) {
			logger.warning("Error parsing the data to JSON");
		} catch (IOException e) {
			logger.warning("Error in reading the data from RSS feed.");
		}
		return null;
	}

}
