package Coupon.Progect.CouponProject.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LoggingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import Coupon.Progect.CouponProject.Entities.LoggedUser;

/**
 * <h2>WebFilter</h2> A class that is responsible on the security of the server
 * contains one method - doFilter
 * 
 * @author pavel
 *
 */
@WebFilter("/rest/*")
public class CustomerFilter implements Filter {

	/**
	 * A method that verifies that a session is exciting and that the Request that
	 * Arrives to the server contains the correct logged user on it .
	 *
	 * 
	 * @param servletRequest  - Defines an object to provide client request
	 *                        information to a servlet. The servlet container
	 *                        creates a ServletRequest object and passes it as an
	 *                        argument to the servlet's service method.
	 * @param servletResponse - Defines an object to assist a servlet in sending a
	 *                        response to the client.The servlet container creates a
	 *                        ServletResponse object and passes it as an argument to
	 *                        the servlet's service method.
	 * @param chain     - A FilterChain is an object provided by the servlet
	 *                        container to the developer giving a view into the
	 *                        invocation chain of a filtered request for a
	 *                        resource.Filters use the FilterChain to invoke the
	 *                        next filter in the chain, or if the calling filter is
	 *                        the last filter in the chain, to invoke the resource
	 *                        at the end of the chain.
	 *
	 * @throws ServletException -.
	 * @throws IOException -.
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = (HttpSession) request.getSession(false);
		if (session == null) {

			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		LoggedUser loggedUser = (LoggedUser) request.getSession(false).getAttribute("LoggedUser");

		if (loggedUser == null) {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		chain.doFilter(request, servletResponse);

	}

}
