package core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.SourceTree;

import org.json.JSONException;
import org.json.JSONObject;

public class QuizListCreator {

	public static ArrayList<String> artistsList = new ArrayList<String>();
	static {
        try {
            artistsList.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsList.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsList.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsList.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsList.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsList.add(URLEncoder.encode("Ария", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

	}

	public String artistRandomChooser() {
		String artist = artistsList.get((int) (Math.random() * 5));
		String URL = "https://itunes.apple.com/ru/search?term=" + artist + "&limit=10";
		return URL;
	}

	public ArrayList songRandomChooser(JSONObject jo) throws JSONException {
		int randomNum = (int) (Math.random() * 9);
		String song = jo.getJSONArray("results").getJSONObject(randomNum).getString("trackName");
		String imageURL = jo.getJSONArray("results").getJSONObject(randomNum).getString("artworkUrl100");
		ArrayList songParams = new ArrayList();
		songParams.add(song);
		songParams.add(imageURL);
		return songParams;
	}

	public JavaRoundBean RoundCreator(String URL) throws MalformedURLException, IOException, JSONException {

		JSONReader jr = new JSONReader();
		JSONObject JSONsongs = jr.readJsonFromURL(URL);

		String artist = JSONsongs.getJSONArray("results").getJSONObject(0).getString("artistName");
		String firstSong = (String) songRandomChooser(JSONsongs).get(0);
		String secondSong = (String) songRandomChooser(JSONsongs).get(0);
		if (firstSong.equals(secondSong)) {
			secondSong = (String) songRandomChooser(JSONsongs).get(0);
		}
		ArrayList songParams = songRandomChooser(JSONsongs);
		String thirdSong = (String) songParams.get(0);
		String imageURL = (String) songParams.get(1);
		if (firstSong.equals(thirdSong) || (secondSong.equals(thirdSong))) {
			songParams = songRandomChooser(JSONsongs);
			thirdSong = (String) songParams.get(0);
			imageURL = (String) songParams.get(1);
		}

		JavaRoundBean round = new JavaRoundBean();

		round.setArtistName(artist);
		round.setFirstSong(firstSong);
		round.setSecondSong(secondSong);
		round.setThirdSong(thirdSong);
		round.setImageURL(imageURL);

		return round;
	}

}
