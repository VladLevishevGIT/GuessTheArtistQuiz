package core;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JavaRoundBean {

	private String artistName;
	private String firstSong;
	private String secondSong;
	private String thirdSong;
	private String imageURL;
	private int roundScore;
	private int totalScore;
	private int roundNumber;

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getFirstSong() {
		return firstSong;
	}

	public void setFirstSong(String firstSong) {
		this.firstSong = firstSong;
	}

	public String getSecondSong() {
		return secondSong;
	}

	public void setSecondSong(String secondSong) {
		this.secondSong = secondSong;
	}

	public String getThirdSong() {
		return thirdSong;
	}

	public void setThirdSong(String thirdSong) {
		this.thirdSong = thirdSong;
	}

	public int getRoundScore() {
		return roundScore;
	}

	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
