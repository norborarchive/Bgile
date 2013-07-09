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

import com.thjug.bgile.define.Permission;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "boardaccount")
@NamedQueries( { @NamedQuery(name = BoardAccount.FIND_BY_ACCOUNT_AND_BOARD, query = "SELECT b FROM BoardAccount b WHERE b.account = ?1 and b.board = ?2"), })
public class BoardAccount extends Time implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_ACCOUNT_AND_BOARD = "Boardaccount.findByAccountAndBoard";
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@SequenceGenerator(name = "boardaccount_seq_gen", sequenceName = "boardaccount_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardaccount_seq_gen")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "permissionid")
	private Permission permissionid;

	@JoinColumn(name = "board", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board board;
	@JoinColumn(name = "account", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Account account;

	public BoardAccount() {
	}

	public BoardAccount(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Permission getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(final Permission permissionid) {
		this.permissionid = permissionid;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(final Board board) {
		this.board = board;
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
		if (!(object instanceof BoardAccount)) {
			return false;
		}
		final BoardAccount other = (BoardAccount) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BoardAccount[ id=" + id + " ]";
	}

}
