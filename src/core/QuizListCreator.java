package core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizListCreator {

	public static ArrayList<String> artistsList = new ArrayList<String>();
	static {
		String str = "Алиса";
		artistsList.add("Madonna");
		artistsList.add("Rihanna");
		artistsList.add("Sia");
		artistsList.add("Adele");
		artistsList.add(str);
	}

	public String artistRandomChooser() {
		String URL;
		String str = "Алиса";
		System.out.println("Алиса");
		System.out.println(artistsList.toString());
		String artist = artistsList.get((int) (Math.random() * 5));
		System.out.println(artist);
		URL = "https://itunes.apple.com/ru/search?term=" + artist + "&limit=10";
		System.out.println(URL);
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
		System.out.println(round.getArtistName());

		/*
		 * round.setArtistName("artist"); round.setFirstSong("firstSong");
		 * round.setSecondSong("secondSong"); round.setThirdSong("thirdSong"); round.setImageURL("imageURL");
		 */

		return round;
	}

}
