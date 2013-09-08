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
package com.thjug.bgile.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "authensession")
@Cacheable
@NamedQueries( {
		@NamedQuery(name = AuthenSession.FIND_BY_ID, query = "SELECT a FROM AuthenSession a WHERE a.id = ?1"),
		@NamedQuery(name = AuthenSession.FIND_BY_ACCOUNT, query = "SELECT a FROM AuthenSession a WHERE a.account = ?1"), })
public class AuthenSession extends Time implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Session.findById";
	public static final String FIND_BY_ACCOUNT = "Session.findByAccount";

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", nullable = false)
	private String id;

	@NotNull
	@Column(name = "rememberme", nullable = false)
	private boolean rememberMe;

	@JoinColumn(name = "account", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Account account;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(final boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof AuthenSession)) {
			return false;
		}
		final AuthenSession other = (AuthenSession) object;
		if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AuthenSession[ id=" + id + " ]";
	}

}
