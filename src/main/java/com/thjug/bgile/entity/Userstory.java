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
@NamedQueries( {
		@NamedQuery(name = Userstory.findAll, query = "SELECT u FROM Userstory u"),
		@NamedQuery(name = Userstory.findByBoardId, query = "SELECT u FROM Userstory u WHERE u.boardid = ?1"),
		@NamedQuery(name = Userstory.findByLowerestBoardIdStateId, query = "SELECT u FROM Userstory u WHERE u.lowerest = ?1 and u.boardid = ?2 and u.stateid = ?3"), })
public class Userstory implements Serializable, Timeable {

	private static final long serialVersionUID = 1L;

	public static final String findAll = "Userstory.findAll";
	public static final String findByBoardId = "Userstory.findByBoardId";
	public static final String findByLowerestBoardIdStateId = "Userstory.findByLowerestBoardIdStateId";

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
	@Column(name = "stateid")
	private char stateid;
	@Basic(optional = false)
	@NotNull
	@Column(name = "statusid")
	private char statusid;
	@Column(name = "sortorder")
	private Integer sortorder;
	@Column(name = "lowerest")
	private char lowerest;
	@Column(name = "underid")
	private Integer underid;
	@Column(name = "estimate")
	private Integer estimate;
	@Size(max = 2147483647)
	@Column(name = "description")
	private String description;
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
	@JoinColumn(name = "boardid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board boardid;

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

	public void setOwnerid(final Account owner) {
		this.ownerid = owner;
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

	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(final List<Todo> todoList) {
		this.todoList = todoList;
	}

	public Integer getSortorder() {
		return sortorder;
	}

	public void setSortorder(Integer sortorder) {
		this.sortorder = sortorder;
	}

	public char getLowerest() {
		return lowerest;
	}

	public void setLowerest(char lowerest) {
		this.lowerest = lowerest;
	}

	public Integer getUnderid() {
		return underid;
	}

	public void setUnderid(Integer underid) {
		this.underid = underid;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public Board getBoardid() {
		return boardid;
	}

	public void setBoardid(Board board) {
		this.boardid = board;
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
