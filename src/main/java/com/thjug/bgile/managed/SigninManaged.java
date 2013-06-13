/*
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
 */
package com.thjug.bgile.managed;

import com.thjug.bgile.entity.Account;
import com.thjug.bgile.security.Encrypter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class SigninManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public String authen() {
		final UsernamePasswordToken token = new UsernamePasswordToken(username, Encrypter.cipher(password));
		final Subject subject = SecurityUtils.getSubject();
		try {
			token.setRememberMe(true);
			subject.login(token);

			final Account account = (Account) subject.getPrincipal();
			getSession().setAttribute(Account.class.getSimpleName(), account.getId());
		} catch (final UnknownAccountException | IncorrectCredentialsException e) {
			addWarnMessage("Username Or Password not correct.", null);
			return null;
		} catch (final LockedAccountException e) {
			addWarnMessage("Username wasn't activated", null);
			return null;
		}
		return redirect("dashboard");
	}

	public String logout() {
		SecurityUtils.getSubject().logout();
		getSession().invalidate();
		return redirect("home");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
