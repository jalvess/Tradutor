package br.com.coursera.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.coursera.controller.TradutorController;
import br.com.coursera.models.Palavra;

/**
 * Servlet implementation class TradutorServlet
 */
@WebServlet("/traduzir")
public class TradutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String palavra = request.getParameter("palavra") != null ? request.getParameter("palavra").trim().toLowerCase()
				: null;
		if (palavra == null)
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
		String idioma = request.getParameter("from_pt") == null ? "português" : "inglês";
		Palavra p = new Palavra(palavra, idioma);
		TradutorController controller = new TradutorController();
		p = controller.traduzir(p);
		if (p != null) {
			response.getWriter().write(Arrays.asList(p.getTraducoes()).toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} else {
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
		}

	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 **/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String novaPalavra = req.getParameter("new_word") != null && !req.getParameter("new_word").isEmpty()
				? req.getParameter("new_word").trim().toLowerCase()
				: null;
		String idioma = req.getParameter("idioma").toLowerCase();
		String[] translation = req.getParameter("translations") != null && !req.getParameter("translations").isEmpty()
				? req.getParameter("translations").trim().split(",")
				: null;
		if (novaPalavra == null || translation == null)
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
		else {
			Palavra p = new Palavra(novaPalavra, idioma, translation);
			TradutorController controller = new TradutorController();
			try {
				controller.adicionaTraducao(p);
			} catch (Exception e) {
				throw new RuntimeException();
			}

		}
	}

}
