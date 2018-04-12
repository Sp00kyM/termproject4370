package servletPackage;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;
import java.lang.Object;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> root = new HashMap<String, Object>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    static String templateDir = "WEB-INF/templates";
    //static String resultTemplateName = "converter.ftl";//template after proper login
    
    private Configuration cfg;

    public void init(){

		//Prepare freeMarker config
		//Load templates from the WEB-INF/templates directory of the Web App
		cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }//init
    
    public void runTemplate(HttpServletRequest request, HttpServletResponse response, String templName) {
		// You can use this structure for all of your objects to be sent to browser
		Template template = null;
		
		
		try {
			String templateName = templName;
			template = cfg.getTemplate(templateName);
			response.setContentType("text/html");
			Writer out = response.getWriter();
			template.process(root, out);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
}// runTemplate
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = null;
		ArrayList<Meteorite> mets = new ArrayList<Meteorite>();
		mets.clear();
		String input = request.getParameter("val");
		System.out.println("INPUT IS: "+ input);
		
		try {
			con = DbAccessImpl.connect();
			LogicImpl logic = new LogicImpl(request, response,con);
			mets = logic.getMets();
			/*
			if(input.equals("name")) {
				runTemplate(request, response,"city.ftl");
			}
			else if(input.equals("mass")) {
				System.out.println("In the else if of mass");
				runTemplate(request, response,"mass.ftl");
			}
			else if(input.equals("year")) {
				runTemplate(request, response,"year.ftl");
			}
			*/
			root.put("meteorites", mets);
			runTemplate(request, response,"map.ftl");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null) {
				DbAccessImpl.disconnect(con);
			}
		}
	}//doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
