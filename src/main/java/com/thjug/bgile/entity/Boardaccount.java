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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "boardaccount")
@NamedQueries( { @NamedQuery(name = "Boardaccount.findAll", query = "SELECT b FROM Boardaccount b") })
public class Boardaccount implements Serializable, Timeable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@SequenceGenerator(name = "boardaccount_seq_gen", sequenceName = "boardaccount_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardaccount_seq_gen")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "permissionid")
	private char permissionid;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = "updateby")
	private Integer updateby;
	@JoinColumn(name = "boardid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board boardid;
	@JoinColumn(name = "accountid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Account accountid;

	public Boardaccount() {
	}

	public Boardaccount(final Integer id) {
		this.id = id;
	}

	public Boardaccount(final Integer id, final char permissionid) {
		this.id = id;
		this.permissionid = permissionid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public char getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(final char permissionid) {
		this.permissionid = permissionid;
	}

	public Board getBoardid() {
		return boardid;
	}

	public void setBoardid(final Board board) {
		this.boardid = board;
	}

	public Account getAccountid() {
		return accountid;
	}

	public void setAccountid(final Account account) {
		this.accountid = account;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(final Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public Integer getUpdateby() {
		return updateby;
	}

	public void setUpdateby(final Integer updateby) {
		this.updateby = updateby;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Boardaccount)) {
			return false;
		}
		final Boardaccount other = (Boardaccount) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Boardaccount[ id=" + id + " ]";
	}
}
