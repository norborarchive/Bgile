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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "todo")
public class Todo extends Time implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 512)
	@Column(name = "description")
	private String description;

	@JoinColumn(name = "card", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Card card;

	public Todo() {
	}

	public Todo(final Integer id) {
		this.id = id;
	}

	public Todo(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(final Card card) {
		this.card = card;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Todo)) {
			return false;
		}
		final Todo other = (Todo) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Todo[ id=" + id + " ]";
	}

}
