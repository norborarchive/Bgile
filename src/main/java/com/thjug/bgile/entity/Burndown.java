/**
 * <pre>
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
 * </pre>
 */
package com.thjug.bgile.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nuboat
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Burndown.FIND_BY_BOARD, query = ""
			+ "SELECT b FROM Burndown b WHERE b.board = ?1 ORDER BY b.statusdate"),})
public class Burndown implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_BOARD = "Burndown.findByBoard";

	@Id
	private Integer id;

	@JoinColumn(name = "board", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board board;

	@Temporal(TemporalType.TIMESTAMP)
	private Date statusdate;

	private Integer estimate;

	private Integer done;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(final Board board) {
		this.board = board;
	}

	public Date getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(final Date statusdate) {
		this.statusdate = statusdate;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(final Integer estimate) {
		this.estimate = estimate;
	}

	public Integer getDone() {
		return done;
	}

	public void setDone(final Integer done) {
		this.done = done;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof Burndown)) {
			return false;
		}
		final Burndown other = (Burndown) object;
		if (id == null) {
			return false;
		}
		return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "Burndown[ id=" + id + " ]";
	}

}
