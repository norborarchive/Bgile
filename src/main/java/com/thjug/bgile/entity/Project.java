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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "project")
@NamedQueries( { @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
		@NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"), })
public class Project implements Serializable, Timeable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "statusid")
	private char statusid;
	@Basic(optional = false)
	@NotNull
	@Column(name = "isenable")
	private char isenable;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "pgname")
	private String pgname;
	@Size(max = 512)
	@Column(name = "description")
	private String description;
	@Size(max = 256)
	@Column(name = "logopath")
	private String logopath;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = "updateby")
	private Integer updateby;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectid")
	private List<Projectaccount> projectaccountList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectid")
	private List<Userstory> userstoryList;

	public Project() {
	}

	public Project(final Integer id) {
		this.id = id;
	}

	public Project(final Integer id, final char statusid, final char isenable, final String pgname) {
		this.id = id;
		this.statusid = statusid;
		this.isenable = isenable;
		this.pgname = pgname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public char getStatusid() {
		return statusid;
	}

	public void setStatusid(final char statusid) {
		this.statusid = statusid;
	}

	public char getIsenable() {
		return isenable;
	}

	public void setIsenable(final char isenable) {
		this.isenable = isenable;
	}

	public String getPgname() {
		return pgname;
	}

	public void setPgname(final String pgname) {
		this.pgname = pgname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getLogopath() {
		return logopath;
	}

	public void setLogopath(final String logopath) {
		this.logopath = logopath;
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

	public List<Projectaccount> getProjectaccountList() {
		return projectaccountList;
	}

	public void setProjectaccountList(final List<Projectaccount> projectaccountList) {
		this.projectaccountList = projectaccountList;
	}

	public List<Userstory> getUserstoryList() {
		return userstoryList;
	}

	public void setUserstoryList(final List<Userstory> userstoryList) {
		this.userstoryList = userstoryList;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Project)) {
			return false;
		}
		final Project other = (Project) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Project[ id=" + id + " ]";
	}

}
