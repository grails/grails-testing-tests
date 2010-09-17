import org.mortbay.jetty.Server
import org.mortbay.jetty.servlet.Context
import org.mortbay.jetty.servlet.ServletHolder

import javax.servlet.ServletException
import javax.servlet.http.*

class TestHttpServer {
	protected server
	boolean started

	Closure get
	Closure post
	Closure put
	Closure delete

	void start() {
		if (!started) {
			server = new Server(0)
			def context = new Context(server, "/")
			context.addServlet(new ServletHolder(new TestHttpServerServlet(this)), "/*")
			server.start()
			started = true
		}
	}

	void stop() {
		if (started) {
			server.stop()
			started = false
		}
	}

	def getPort() {
		server?.connectors[0].localPort
	}

	def getBaseUrl() {
			"http://localhost:$port/"
	}

}

class TestHttpServerServlet extends HttpServlet {
	private server

	TestHttpServerServlet(server) {
		this.server = server
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (server.get) {
			server.get?.call(req, res)
		} else {
		 super.doGet(req, res)
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (server.post) {
			server.post?.call(req, res)
		} else {
		 super.doPost(req, res)
		}
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (server.put) {
			server.put?.call(req, res)
		} else {
		 super.doPut(req, res)
		}
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (server.delete) {
			server.delete?.call(req, res)
		} else {
		 super.doDelete(req, res)
		}
	}

}