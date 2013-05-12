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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "userstory")
@NamedQueries( { @NamedQuery(name = Userstory.findAll, query = "SELECT u FROM Userstory u"),
		@NamedQuery(name = Userstory.findByProjectId, query = "SELECT u FROM Userstory u WHERE u.projectid = ?1"), })
public class Userstory implements Serializable, Timeable {

	private static final long serialVersionUID = 1L;

	public static final String findAll = "Userstory.findAll";
	public static final String findByProjectId = "Userstory.findByProjectId";

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@SequenceGenerator(name = "userstory_seq_gen", sequenceName = "userstory_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userstory_seq_gen")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 512)
	@Column(name = "story")
	private String story;
	@JoinColumn(name = "ownerid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Account ownerid;
	@Basic(optional = false)
	@NotNull
	@Column(name = "estimate")
	private int estimate;
	@Basic(optional = false)
	@NotNull
	@Column(name = "stateid")
	private char stateid;
	@Basic(optional = false)
	@NotNull
	@Column(name = "statusid")
	private char statusid;
	@Size(max = 2147483647)
	@Column(name = "description")
	private String description;
	@Basic(optional = false)
	@NotNull
	@Column(name = "sortorder")
	private int sortorder;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = "updateby")
	private Integer updateby;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userstoryid")
	private List<Todo> todoList;
	@JoinColumn(name = "projectid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Project projectid;
	@JoinColumn(name = "iterationid", referencedColumnName = "id")
	@ManyToOne
	private Iteration iterationid;

	public Userstory() {
	}

	public Userstory(final Integer id) {
		this.id = id;
	}

	public Userstory(final Integer id, final String story, final int estimate, final char stateid) {
		this.id = id;
		this.story = story;
		this.estimate = estimate;
		this.stateid = stateid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getStory() {
		return story;
	}

	public void setStory(final String story) {
		this.story = story;
	}

	public Account getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(final Account ownerid) {
		this.ownerid = ownerid;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(final int estimate) {
		this.estimate = estimate;
	}

	public char getStateid() {
		return stateid;
	}

	public void setStateid(final char stateid) {
		this.stateid = stateid;
	}

	public char getStatusid() {
		return statusid;
	}

	public void setStatusid(final char statusid) {
		this.statusid = statusid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getSortorder() {
		return sortorder;
	}

	public void setSortorder(final int sortorder) {
		this.sortorder = sortorder;
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

	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(final List<Todo> todoList) {
		this.todoList = todoList;
	}

	public Project getProjectid() {
		return projectid;
	}

	public void setProjectid(final Project projectid) {
		this.projectid = projectid;
	}

	public Iteration getIterationid() {
		return iterationid;
	}

	public void setIterationid(final Iteration iterationid) {
		this.iterationid = iterationid;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Userstory)) {
			return false;
		}
		final Userstory other = (Userstory) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Userstory[ id=" + id + " ]";
	}

}
