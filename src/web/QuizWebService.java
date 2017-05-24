package web;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import core.JavaRoundBean;
import core.QuizListCreator;

@WebService
@Path("quizWebService")
public class QuizWebService {

	@Context
	private HttpServletRequest req;

	@Context
	private HttpServletResponse res;

	@Path("getFirstSong")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JavaRoundBean getFirstSong() throws MalformedURLException, IOException, JSONException {
		HttpSession session = req.getSession(true);
		Integer roundNumber = (Integer) session.getAttribute("roundNumber");
		if (roundNumber == null) {
			roundNumber = 1;
		}
		while (roundNumber <= 5) {
			if (!session.equals(null)) {
				QuizListCreator qc = new QuizListCreator();
				JavaRoundBean round = qc.RoundCreator(qc.artistRandomChooser());
				round.setRoundScore(50);
				session.setAttribute("round", round);
				session.setAttribute("songsCounter", "0");
				session.setAttribute("roundNumber", roundNumber);
				JavaRoundBean returnedRound = new JavaRoundBean();
				returnedRound.setRoundNumber(roundNumber);
				returnedRound.setArtistName(round.getArtistName());
				returnedRound.setFirstSong(round.getFirstSong());
				return returnedRound;
			} else {
				res.sendError(505);
			}
		}
		return null;
	}

	@Path("getOneMoreSong/{inputValue}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JavaRoundBean getSecondSong(@PathParam("inputValue") String inputValue)
			throws MalformedURLException, IOException, JSONException {
		HttpSession session = req.getSession(false);
		Integer roundNumber = (Integer) session.getAttribute("roundNumber");
		JavaRoundBean answer = (JavaRoundBean) session.getAttribute("round");
		if (!session.equals(null)) {
			String counter = (String) session.getAttribute("songsCounter");
			if (!answer.getArtistName().equals(inputValue) && !counter.equals("2")) {
				if (counter.equals("0")) {
					JavaRoundBean round = (JavaRoundBean) session.getAttribute("round");
					round.setRoundScore(30);
					JavaRoundBean returnedRound = new JavaRoundBean();
					returnedRound.setRoundNumber((Integer) session.getAttribute("roundNumber"));
					returnedRound.setArtistName(round.getArtistName());
					returnedRound.setFirstSong(round.getFirstSong());
					returnedRound.setSecondSong(round.getSecondSong());
					session.removeAttribute("songsCounter");
					session.setAttribute("songsCounter", "1");
					return returnedRound;
				} else if (counter.equals("1")) {
					JavaRoundBean round = (JavaRoundBean) session.getAttribute("round");
					round.setRoundScore(20);
					JavaRoundBean returnedRound = new JavaRoundBean();
					returnedRound.setRoundNumber((Integer) session.getAttribute("roundNumber"));
					returnedRound.setArtistName(round.getArtistName());
					returnedRound.setFirstSong(round.getFirstSong());
					returnedRound.setSecondSong(round.getSecondSong());
					returnedRound.setThirdSong(round.getThirdSong());
					session.removeAttribute("songsCounter");
					session.setAttribute("songsCounter", "2");
					return returnedRound;
				}
			} else {
				JavaRoundBean round = (JavaRoundBean) session.getAttribute("round");
				if (!answer.getArtistName().equals(inputValue) && counter.equals("2")) {
					round.setRoundScore(0);
				}
				JavaRoundBean returnedRound = new JavaRoundBean();
				returnedRound.setRoundNumber((Integer) session.getAttribute("roundNumber"));
				returnedRound.setRoundScore(round.getRoundScore());
				returnedRound.setArtistName(round.getArtistName());
				returnedRound.setFirstSong(null);
				returnedRound.setSecondSong(null);
				returnedRound.setThirdSong(null);
				Integer totalScore = (Integer) session.getAttribute("totalScore");
				if (totalScore == null) {
					totalScore = 0;
				}
				returnedRound.setTotalScore(round.getRoundScore() + totalScore);
				session.removeAttribute("totalScore");
				session.removeAttribute("roundNumber");
				session.setAttribute("totalScore", returnedRound.getTotalScore());
				roundNumber++;
				session.setAttribute("roundNumber", roundNumber);
				return returnedRound;
			}
		} else {
			res.sendError(505);
		}
		return null;
	}

	@Path("getFirstSong01")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JavaRoundBean getFirstSong01() throws MalformedURLException, IOException, JSONException {
		HttpSession session = req.getSession(true);
			if (!session.equals(null)) {
				QuizListCreator qc = new QuizListCreator();
				JavaRoundBean round = qc.RoundCreator(qc.artistRandomChooser());
				return round;
			} else {
				res.sendError(505);
			}
		return null;
	}

}
