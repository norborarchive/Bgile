/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import javax.validation.constraints.Size;

/**
 *
 * @author nuboat
 */
@Entity
@Table(name = "storyorder")
@NamedQueries( { @NamedQuery(name = "Storyorder.findAll", query = "SELECT s FROM Storyorder s") })
public class Storyorder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "stateid")
	private String stateid;
	@Size(max = 2147483647)
	@Column(name = "orderby")
	private String orderby;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = "updateby")
	private Integer updateby;
	@JoinColumn(name = "board", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board board;

	public Storyorder() {
	}

	public Storyorder(Integer id) {
		this.id = id;
	}

	public Storyorder(Integer id, String stateid) {
		this.id = id;
		this.stateid = stateid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getUpdateby() {
		return updateby;
	}

	public void setUpdateby(Integer updateby) {
		this.updateby = updateby;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Storyorder)) {
			return false;
		}
		Storyorder other = (Storyorder) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.thjug.bgile.entity.Storyorder[ id=" + id + " ]";
	}
}
