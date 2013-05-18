package net.mariusgundersen.bookmarks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	        qvc.setHandleFactory(new BookmarkHandlerFactory());
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
		String sessionId = request.getSession().getId();
		
		response.setContentType("text/json");
		PrintWriter pw = response.getWriter();
		
		if(path.length != 2){
			pw.print("{\"error\":\"wrong path: "+request.getPathInfo()+"\"}");
		}else{
			String type = path[0];
			String name = path[1];
			String parameters = request.getParameter("parameters");
			if(csrfTokenIsCorrect(type, request.getParameter("csrfToken"), request.getSession()))
				pw.print(execute(type, name, parameters, sessionId));
			else
				pw.print("{\"error\":\"wrong csrfToken\"}");
		}
	}
	
	private boolean csrfTokenIsCorrect(String type, String csrfToken, HttpSession httpSession) {
		return true;
	}
	
	protected String execute(String type, String name, String parameters, String sessionId){
		switch(type){
			case "command": 
				return endpoint.command(name, parameters, sessionId);
			case "query": 
				return endpoint.query(name, parameters, sessionId);
			case "constraints": 
				return endpoint.constraints(name);
			default: 
				return "{\"error\": \"unknown type\"}";
		}
	}
	
	public class BookmarkHandlerFactory implements HandlerFactory{
		
		private SessionStore<BookmarkCollection> sessionStore;

		public BookmarkHandlerFactory() {
			this.sessionStore = new SessionStore<BookmarkCollection>() {
				protected BookmarkCollection createSessionObject() {
					return new BookmarkCollection();
				}
			};
		}

		@Override
		public Handler create(Class<? extends Handler> handlerClass, String sessionId) throws Exception {			
			Handler handler = handlerClass.getConstructor(BookmarkCollection.class).newInstance(sessionStore.getSessionObject(sessionId));
			handler.setSessionId(sessionId);
			return handler;
		}
		
	}

}
