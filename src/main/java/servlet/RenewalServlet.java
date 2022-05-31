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
		
		try {
			String productId = request.getParameter("proId");
			String hiddenId = request.getParameter("hydeId");
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
			
			int prId = ParamUtil.checkAndParseInt(productId);
			int hydeId = ParamUtil.checkAndParseInt(hiddenId);
			int ctId = ParamUtil.checkAndParseInt(cateID);
			int pri = ParamUtil.checkAndParseInt(price);
			
			ProductService proService = new ProductService();
			
			if(proService.searchId(prId) != null) {
				if(prId == hydeId) {
					proService.updatePro(prId, ctId, productName, pri, description);
					request.setAttribute("InMsg", "更新処理が完了しました");
					request.getRequestDispatcher("/menu.jsp").forward(request, response);
				}else {
					Product product = proService.searchDetail(hydeId);
					
					request.setAttribute("product", product);
					request.setAttribute("UpdateMsg", "商品IDが重複しています");
					request.getRequestDispatcher("/updateInput.jsp").forward(request, response);
				}
			}
		}catch(Exception e) {
			request.setAttribute("InMsg", "更新時にエラーが発生しました");
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
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
