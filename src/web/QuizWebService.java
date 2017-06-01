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

	@Path("getFirstSong/{language}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JavaRoundBean getFirstSong(@PathParam("language") String language) throws MalformedURLException, IOException, JSONException {
		HttpSession session = req.getSession(true);
			if (!session.equals(null)) {
				QuizListCreator qc = new QuizListCreator();
				JavaRoundBean round = qc.RoundCreator(qc.artistRandomChooser(language));
				return round;
			} else {
				res.sendError(505);
			}
		return null;
	}

}
