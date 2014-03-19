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
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * History tell who did what on which time.
 * @author @nuboat
 */
@Entity
public class History extends Time implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Basic(optional = false)
	@SequenceGenerator(name = "history_seq_gen", sequenceName = "history_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_seq_gen")
	private Integer id;

	private Integer boardid;
	
	private Integer cardid;

	@Size(max = Columnsize.MAXTEXT)
	private String olddetail;

	@Size(max = Columnsize.MAXTEXT)
	private String newdetail;

	public History() {
	}

	public History(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getBoardid() {
		return boardid;
	}

	public void setBoardid(final Integer boardid) {
		this.boardid = boardid;
	}

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(final Integer cardid) {
		this.cardid = cardid;
	}

	public String getOlddetail() {
		return olddetail;
	}

	public void setOlddetail(final String olddetail) {
		this.olddetail = olddetail;
	}

	public String getNewdetail() {
		return newdetail;
	}

	public void setNewdetail(final String newdetail) {
		this.newdetail = newdetail;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof History)) {
			return false;
		}
		final History other = (History) object;
		if (id == null) {
			return false;
		}
		return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "History[ id=" + id + " ]";
	}
}
