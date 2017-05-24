package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {

	public String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while((cp=rd.read())!=-1){
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONObject readJsonFromURL (String url) throws MalformedURLException, IOException, JSONException{
			InputStream is = new URL(url).openStream();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			String jsonText = readAll(br);
			JSONObject json = new JSONObject(jsonText);
		return json;
		} finally {
			is.close();
		}
	}
	
}
