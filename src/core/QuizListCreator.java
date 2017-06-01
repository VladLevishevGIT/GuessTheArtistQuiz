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

	public static ArrayList<String> artistsListRussian = new ArrayList<String>();
    static {
        try {
            artistsListRussian.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsListRussian.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsListRussian.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsListRussian.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsListRussian.add(URLEncoder.encode("Ария", "UTF-8"));
            artistsListRussian.add(URLEncoder.encode("Ария", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> artistsListEnglish = new ArrayList<String>();
    static {
        try {
            artistsListEnglish.add(URLEncoder.encode("Adele", "UTF-8"));
            artistsListEnglish.add(URLEncoder.encode("Adele", "UTF-8"));
            artistsListEnglish.add(URLEncoder.encode("Adele", "UTF-8"));
            artistsListEnglish.add(URLEncoder.encode("Adele", "UTF-8"));
            artistsListEnglish.add(URLEncoder.encode("Adele", "UTF-8"));
            artistsListEnglish.add(URLEncoder.encode("Adele", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

	public String artistRandomChooser(String language) {
        if(language.equals("english")){
		    String artist = artistsListEnglish.get((int) (Math.random() * 5));
		    String URL = "https://itunes.apple.com/ru/search?term=" + artist + "&limit=10";
		    return URL;
        }else{
            String artist = artistsListRussian.get((int) (Math.random() * 5));
            String URL = "https://itunes.apple.com/ru/search?term=" + artist + "&limit=10";
            return URL;
        }
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
        String secondSong;
        String thirdSong;
        String imageURL;
        do{
            secondSong = (String) songRandomChooser(JSONsongs).get(0);
        }while(secondSong.equals(firstSong));
		do{
            ArrayList songParams = songRandomChooser(JSONsongs);
            thirdSong = (String) songParams.get(0);
            imageURL = (String) songParams.get(1);
        }while(thirdSong.equals(firstSong) || (thirdSong.equals(secondSong)));

		JavaRoundBean round = new JavaRoundBean();

		round.setArtistName(artist);
		round.setFirstSong(firstSong);
		round.setSecondSong(secondSong);
		round.setThirdSong(thirdSong);
		round.setImageURL(imageURL);

		return round;
	}

}
