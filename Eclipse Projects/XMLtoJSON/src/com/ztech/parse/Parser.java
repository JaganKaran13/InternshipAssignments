package com.ztech.parse;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

import org.json.*;

public class Parser {
	
	static String xml_data, line;
	private static Logger logger = Logger.getLogger(Parser.class.getName());

	public static void main(String[] args) throws IOException {
		URL rssURL = new URL("https://news.google.com/news/rss/headlines/section/topic/WORLD?ned=us&hl=en&gl=US");
		BufferedReader br = new BufferedReader(new InputStreamReader(rssURL.openStream()));
		StringBuilder rssStringBuilder = new StringBuilder("");
		while ((line = br.readLine()) != null) {
			rssStringBuilder.append(line);
		}
		try {
			JSONObject jsonObj = XML.toJSONObject(rssStringBuilder.toString());
			JSONObject object = (JSONObject) jsonObj.get("rss");
			object = (JSONObject) object.get("channel");
			JSONArray jsonArray = (JSONArray) object.get("item");
			for (int i = 0;i < jsonArray.length(); i++) {
				object = (JSONObject) jsonArray.get(i);
				System.out.println(object.get("title"));
			}
		} catch (JSONException jsonex) {
			logger.warning("Error converting rss feed to JSON object.");
		}
	} 

}
