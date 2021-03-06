package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Parent;
import database.UserDAO;

@WebServlet("/ProcessEditParent")
public class ProcessEditParent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ProcessEditParent() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Parent parent = new Parent();
		UserDAO userDAO = new UserDAO();
		String parentId = (String) session.getAttribute("parentId");
		
		parent.setFirstName(request.getParameter("firstName"));
		parent.setLastName(request.getParameter("lastName"));
		parent.setPassword(request.getParameter("password"));
		parent.setBirthdate(request.getParameter("birthdate"));
		parent.setGender(request.getParameter("gender"));
		parent.setAddress(request.getParameter("address"));
		parent.setPhone(request.getParameter("phone"));
		parent.setEmail(request.getParameter("email"));
		parent.setId(Integer.valueOf(parentId));
		parent.setIsParent("true");
		parent.setIsAdmin("false");
		
		if(!parent.isRegisterValid()) {
			session.setAttribute("registerError", "All fields must be filled");
			response.sendRedirect("views/EdithParent.jsp");
		}
		else {
			userDAO.updateParent(parent);
			response.sendRedirect("views/UserList.jsp");
		}
		
	}

}
