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

import com.thjug.bgile.define.Permission;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.BoardAccount;
import com.thjug.bgile.facade.AccountFacade;
import com.thjug.bgile.facade.GrantFacade;
import com.thjug.bgile.util.StringUtility;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import org.primefaces.component.selectonebutton.SelectOneButton;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ViewScoped
@ManagedBean(name = "grant")
public class GrantManaged extends BgileAbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(GrantManaged.class);

	private Board board;
	private BoardAccount boardaccount;
	private String accountname;
	private List<Account> accounts;
	private List<BoardAccount> grants;

	@Inject
	private transient AccountFacade facade;

	@Inject
	private transient GrantFacade grantFacade;

	@PostConstruct
	public void initial() {
		final Integer boardid = getBoardIdfromAttribute();
		if (boardid == null) {
			setRedirect("dashboard");
			return;
		}

		boardaccount = grantFacade.getBoardAccount(getPrincipal().getId(), boardid);
		if (boardaccount == null || boardaccount.getPermissionid() == Permission.R
				|| boardaccount.getPermissionid() == Permission.W) {
			setRedirect("dashboard");
			return;
		}

		board = boardaccount.getBoard();
		if (board == null) {
			setRedirect("dashboard");
		}

		grants = grantFacade.getAccessAccount(board);

	}

	public void changePermission(final AjaxBehaviorEvent event) {
		final SelectOneButton ui = (SelectOneButton) event.getComponent();
		final Integer accountid = (Integer) ui.getAttributes().get("accountid");
		final Permission newPermission = (Permission) ui.getLocalValue();

		LOG.info("Id: {} Permission: {}", accountid, newPermission);
		grantFacade.editAccountToBoard(getPrincipal().getId(), accountid, board.getId(), newPermission);

		FacesContext.getCurrentInstance().renderResponse();
	}

	public List<Account> findAccounts(final String accountname) {
		if (StringUtility.isNotEmpty(accountname)) {
			accounts = facade.containsKeyword(accountname);
		}
		return accounts;
	}

	public void handleSelect(final SelectEvent event) {
		accountname = null;

		final Account account = (Account) event.getObject();
		LOG.info("Id: {} Name: {}", account.getId(), account.getFirstname());

		for (final BoardAccount bacc : grants) {
			if (account.equals(bacc.getAccount())) {
				return;
			}
		}

		grants.add(grantFacade.addAccountToBoard(getPrincipal().getId(), account, board));
	}

	public boolean isOwner() {
		return boardaccount.getPermissionid() == Permission.O;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(final String accountname) {
		this.accountname = accountname;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(final List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<BoardAccount> getGrants() {
		return grants;
	}

	public void setGrants(final List<BoardAccount> grants) {
		this.grants = grants;
	}

}
