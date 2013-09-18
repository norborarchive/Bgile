/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thjug.bgile.entity;

import com.thjug.bgile.define.Columnsize;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "cardorder")
@NamedQueries( {
		@NamedQuery(name = Cardorder.FIND_BY_BOARD, query = "SELECT a FROM Cardorder a WHERE a.board = ?1 "),
		@NamedQuery(name = Cardorder.FIND_BY_BOARD_AND_STATE, query = "SELECT a FROM Cardorder a WHERE a.board = ?1 AND a.stateid = ?2"), })
public class Cardorder extends Time implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_BOARD = "Cardorder.findByBoard";
	public static final String FIND_BY_BOARD_AND_STATE = "Cardorder.findByBoardAndState";

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@SequenceGenerator(name = "cardorder_seq_gen", sequenceName = "cardorder_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardorder_seq_gen")
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "stateid")
	private Integer stateid;

	@Size(max = Columnsize.MAXTEXT)
	@Column(name = "orderby")
	private String orderby;

	@JoinColumn(name = "board", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board board;

	public Cardorder() {
	}

	public Cardorder(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(final Integer stateid) {
		this.stateid = stateid;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(final String orderby) {
		this.orderby = orderby;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(final Board board) {
		this.board = board;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Cardorder)) {
			return false;
		}
		final Cardorder other = (Cardorder) object;
		if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Storyorder[ id=" + id + " ]";
	}
}
