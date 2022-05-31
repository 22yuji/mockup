package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
import util.ParamUtil;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	
		try {
			String productId = request.getParameter("proId");
			String cateID = request.getParameter("cateId");
			String productName = request.getParameter("proName");
			String price = request.getParameter("pri");
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
				request.getRequestDispatcher("/insert.jsp").forward(request, response);
			}
			int prId = ParamUtil.checkAndParseInt(productId);
			int ctId = ParamUtil.checkAndParseInt(cateID);
			int pri = ParamUtil.checkAndParseInt(price);
			
			ProductService proService = new ProductService();
			
			if(proService.searchId(prId) != null) {
				request.setAttribute("InsertMsg", "商品IDが重複しています");
				request.getRequestDispatcher("/insert.jsp").forward(request, response);
			}
			proService.insertProduct(prId, ctId, productName, pri, description);
			
			request.setAttribute("InMsg", "登録が完了しました");
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}catch(Exception e){
			request.setAttribute("InsertMsg", "エラーが発生しました");
			request.getRequestDispatcher("/insert.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
