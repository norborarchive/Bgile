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

import com.thjug.bgile.define.Columnsize;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author @nuboat
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Cardorder.FIND_BY_BOARD, query = "SELECT a FROM Cardorder a WHERE a.board = ?1 "),
		@NamedQuery(name = Cardorder.FIND_BY_BOARD_AND_STATE,
				query = "SELECT a FROM Cardorder a WHERE a.board = ?1 AND a.stateid = ?2"), })
public class Cardorder extends Time implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_BOARD = "Cardorder.findByBoard";
	public static final String FIND_BY_BOARD_AND_STATE = "Cardorder.findByBoardAndState";

	@Id
	@NotNull
	@Basic(optional = false)
	@SequenceGenerator(name = "cardorder_seq_gen", sequenceName = "cardorder_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardorder_seq_gen")
	private Integer id;

	@NotNull
	@Basic(optional = false)
	private Integer stateid;

	@Size(max = Columnsize.MAXTEXT)
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
		if (!(object instanceof Cardorder)) {
			return false;
		}
		final Cardorder other = (Cardorder) object;
		if (id == null) {
			return false;
		}
		return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "Storyorder[ id=" + id + " ]";
	}
}
