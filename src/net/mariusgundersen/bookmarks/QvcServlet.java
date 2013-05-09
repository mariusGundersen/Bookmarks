package net.mariusgundersen.bookmarks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mariusgundersen.bookmarks.domain.BookmarkCollection;

import qvc.JsonEndpoint;
import qvc.QVC;
import qvc.exceptions.DuplicateExecutableException;
import qvc.handlers.Handler;
import qvc.handlers.factory.*;

/**
 * Servlet implementation class Qvc
 */
public class QvcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private QVC qvc;
	private JsonEndpoint endpoint;
	
	public QvcServlet() {
	    try {
	    	
	        qvc = new QVC();
	        qvc.setHandleFactory(new BookmarkHandlerFactory(new BookmarkCollection()));
	        qvc.addPackage("net.mariusgundersen.bookmarks.domain");
			qvc.loadCommandsAndQueries();
			
	        endpoint = qvc.getJsonEndpoint();
		} catch (DuplicateExecutableException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String[] path = request.getPathInfo().substring(1).split("/");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json");
		PrintWriter pw = response.getWriter();
		
		if(path.length != 2){
			pw.print("{\"error\":\"wrong path: "+request.getPathInfo()+"\"}");
		}else{
			String type = path[0];
			String name = path[1];
			String parameters = request.getParameter("parameters");
	
			pw.print(execute(type, name, parameters));
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(204);
		response.setHeader("access-control-allow-origin", "*");
		response.setHeader("access-control-allow-methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("access-control-allow-headers", "content-type, accept");
		response.setIntHeader("access-control-max-age", 10); // Seconds.
		response.setContentLength(0);
	}
	
	protected String execute(String type, String name, String parameters){
		switch(type){
			case "command": return endpoint.command(name, parameters);
			case "query": return endpoint.query(name, parameters);
			case "constraints": return endpoint.constraints(name);
			default: return "{\"error\": \"unknown type\"}";
		}
	}
	
	public class BookmarkHandlerFactory implements HandlerFactory{

		private BookmarkCollection bookmarkCollection;

		public BookmarkHandlerFactory(BookmarkCollection bookmarkCollection) {
			this.bookmarkCollection = bookmarkCollection;
		}

		@Override
		public Handler create(Class<? extends Handler> handlerClass, String sessionId) throws Exception {
			Handler handler = handlerClass.getConstructor(BookmarkCollection.class).newInstance(bookmarkCollection);
			handler.setSessionId(sessionId);
			return handler;
		}
		
	}

}
