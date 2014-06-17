/**
 * <pre>
 * Attribution
 * CC BY
 * This license lets others distribute, remix, tweak,
 * and build upon your work, even commercially,
 * as long as they credit you for the original creation.
 * This is the most accommodating of licenses offered.
 * Recommended for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/
 * http://creativecommons.org/licenses/by/3.0/legalcode
 * </pre>
 */
package com.thjug.bgile.servlet;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.AuthenSession;
import com.thjug.bgile.facade.AuthenSessionFacade;
import com.thjug.bgile.guice.GuiceInjectorFactory;
import com.thjug.bgile.security.Encrypter;
import com.thjug.bgile.util.StringUtility;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nuboat
 */
@WebServlet(name = "SigninServlet", urlPatterns = {"/dosignin"})
public class SigninServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SigninServlet.class);

	private final transient AuthenSessionFacade facade;

	public SigninServlet() {
		facade = GuiceInjectorFactory.getInjector().getInstance(AuthenSessionFacade.class);
	}

	public void processRequest(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		try {
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");

			if (StringUtility.isEmpty(username) || StringUtility.isEmpty(password)) {
				throw new IllegalArgumentException();
			}

			final String ciphertext = Encrypter.cipher(password);
			LOG.debug("Authen with Username: {} Password {}", username, ciphertext);

			final AuthenticationToken token = new UsernamePasswordToken(username, ciphertext, true);
			final Subject subject = SecurityUtils.getSubject();

			subject.login(token);

			final Account account = (Account) SecurityUtils.getSubject().getPrincipal();
			final AuthenSession authenSession = facade.saveSession(account, subject.isRemembered());
			response.addCookie(createCookie("bgile_auth_token", authenSession.getId()));

			response.sendRedirect("dashboard");
			return;
		} catch (final IllegalArgumentException e) {
			request.getSession().setAttribute("SIGNIN_ERROR_MSG", "Username and Password should not be null.");
		} catch (final UnknownAccountException | IncorrectCredentialsException e) {
			request.getSession().setAttribute("SIGNIN_ERROR_MSG", "Username Or Password does not correct.");
		} catch (final LockedAccountException e) {
			request.getSession().setAttribute("SIGNIN_ERROR_MSG", "Username are not activated.");
		}

		response.sendRedirect("signin");
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("signin");
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private Cookie createCookie(final String cookieName, final String value) {
		final Cookie userCookie = new Cookie(cookieName, value);
		userCookie.setMaxAge(31_536_000);
		return userCookie;
	}

}
