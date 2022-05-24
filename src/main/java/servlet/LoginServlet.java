package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import entity.User;
import service.LoginService;
import service.ProductService;
import util.ParamUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession(true);
		// 全てのセッション情報を削除する
		Enumeration en = session.getAttributeNames();
		String eName;

		while(en.hasMoreElements()){
		  eName = (String)en.nextElement();
		  session.removeAttribute(eName);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		
		String logId = request.getParameter("loginId");
		String logPass = request.getParameter("pass");
		HttpSession session = request.getSession(true);
		/* 
		 *空文字の時にログイン画面に戻る処理
		 */
		if(ParamUtil.isNullOrEmpty(logId)) {
			/*if(ParamUtil.isNullOrEmpty(logPass)) {
				request.setAttribute("passMsg", "PASSは必須です");
			}*/
			request.setAttribute("idMsg", "IDは必須です");
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		if(ParamUtil.isNullOrEmpty(logPass)) {
			request.setAttribute("passMsg", "PASSは必須です");
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		if(ParamUtil.isNullOrEmpty(logId) || ParamUtil.isNullOrEmpty(logPass)) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		
		LoginService logService = new LoginService();
		User user = logService.userCheck(logId, logPass);
		
		if(user == null) {
			request.setAttribute("logMsg", "IDまたはパスワードが不正です");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		ProductService proService = new ProductService();
		List<Product> product = proService.searchAll();
		session.setAttribute("productList", product);
		session.setAttribute("uName", user.getName());
		request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}

}
