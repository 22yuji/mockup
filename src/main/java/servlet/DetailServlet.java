package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;
import util.ParamUtil;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String proId = request.getParameter("productId");
		int prId = ParamUtil.checkAndParseInt(proId);
		
		ProductService proService = new ProductService();
		Product product = proService.searchDetail(prId);
		
		request.setAttribute("product", product);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String proId = request.getParameter("proId");
			int prId = ParamUtil.checkAndParseInt(proId);
			
			ProductService proService = new ProductService();
			Product product = proService.destroyPro(prId);
			
			request.setAttribute("InMsg", "削除に成功しました");
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("InMsg", "削除に失敗しました");
			request.getRequestDispatcher("/detail.jsp").forward(request, response);
		}
	}

}
