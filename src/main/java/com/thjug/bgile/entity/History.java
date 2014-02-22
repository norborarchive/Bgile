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
 * 
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

	private Integer accountid;

	private Integer refid;

	private Integer actionid;

	@Size(max = Columnsize.MAXTEXT)
	private String reason;

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

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(final Integer accountid) {
		this.accountid = accountid;
	}

	public Integer getRefid() {
		return refid;
	}

	public void setRefid(final Integer refid) {
		this.refid = refid;
	}

	public Integer getActionid() {
		return actionid;
	}

	public void setActionid(final Integer actionid) {
		this.actionid = actionid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not
		// set
		if (!(object instanceof History)) {
			return false;
		}
		final History other = (History) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "History[ id=" + id + " ]";
	}
}
