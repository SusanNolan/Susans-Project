package SusansCitCloudApp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SwapServlet extends HttpServlet {

	private WebApplicationContext ctx;

	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Swaprepository repo = getRepo(req);
		req.setAttribute("swaps", repo.getSwaps());
		doForward(req, resp);
	}

	private Swaprepository getRepo(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Swaprepository repo = getRepo(req);
		Swap swap = new Swap();
		swap.setText(req.getParameter("text"));
		repo.addSwap(swap);

		req.setAttribute("swaps", repo.getSwaps());
		doForward(req, resp);
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp, Swap swap)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("swapId"));
		Swaprepository repo = getRepo(req);
		Swap
		Swap = repo.getSwaps().get(index - 1);
		swap.setDone(!swap.isDone());
		req.setAttribute("swaps", repo.getSwaps());
		doForward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("swapId"));
		Swaprepository repo = getRepo(req);
		repo.getSwaps().remove(index - 1);
		req.setAttribute("swaps", repo.getSwaps());
		doForward(req, resp);
	}

	private Swaprepository getSwap(HttpServletRequest req) {
		return ctx.getBean(Swaprepository.class);
	}

	private void doForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/Swap_list.jsp");
		rd.forward(req, resp);
	}
}	