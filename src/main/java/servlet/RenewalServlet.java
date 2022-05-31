package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ParamUtil;

/**
 * Servlet implementation class RenewalServlet
 */
@WebServlet("/RenewalServlet")
public class RenewalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenewalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String productId = request.getParameter("proId");
		String cateID = request.getParameter("cateId");
		String productName = request.getParameter("proName");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		
		if(ParamUtil.isNullOrEmpty(productId) || ParamUtil.isNullOrEmpty(productName) || ParamUtil.isNullOrEmpty(price)) {
			if(ParamUtil.isNullOrEmpty(productId)) {
				request.setAttribute("IdMsg", "商品IDは必須です");
			}
			if(ParamUtil.isNullOrEmpty(productName)) {
				request.setAttribute("NameMsg", "商品名は必須です");
			}
			if(ParamUtil.isNullOrEmpty(price)) {
				request.setAttribute("PriceMsg", "単価は必須です");
			}
			request.getRequestDispatcher("/updateInput.jsp").forward(request, response);
		}
		request.setAttribute("InMsg", "更新処理が完了しました");
		request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
