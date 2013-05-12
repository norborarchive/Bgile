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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "projectaccount")
@NamedQueries( {
		@NamedQuery(name = "Projectaccount.findAll", query = "SELECT p FROM Projectaccount p"),
		@NamedQuery(name = "Projectaccount.findById", query = "SELECT p FROM Projectaccount p WHERE p.id = :id"),
		@NamedQuery(name = "Projectaccount.findByPermissionid", query = "SELECT p FROM Projectaccount p WHERE p.permissionid = :permissionid"),
		@NamedQuery(name = "Projectaccount.findByCreated", query = "SELECT p FROM Projectaccount p WHERE p.created = :created"),
		@NamedQuery(name = "Projectaccount.findByUpdated", query = "SELECT p FROM Projectaccount p WHERE p.updated = :updated"),
		@NamedQuery(name = "Projectaccount.findByUpdateby", query = "SELECT p FROM Projectaccount p WHERE p.updateby = :updateby") })
public class Projectaccount implements Serializable, Timeable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
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
	@JoinColumn(name = "projectid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Project projectid;
	@JoinColumn(name = "accountid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Account accountid;

	public Projectaccount() {
	}

	public Projectaccount(final Integer id) {
		this.id = id;
	}

	public Projectaccount(final Integer id, final char permissionid) {
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

	public Project getProjectid() {
		return projectid;
	}

	public void setProjectid(final Project projectid) {
		this.projectid = projectid;
	}

	public Account getAccountid() {
		return accountid;
	}

	public void setAccountid(final Account accountid) {
		this.accountid = accountid;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Projectaccount)) {
			return false;
		}
		final Projectaccount other = (Projectaccount) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Projectaccount[ id=" + id + " ]";
	}

}
