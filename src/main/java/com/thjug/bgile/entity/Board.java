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

import com.thjug.bgile.define.Enable;
import com.thjug.bgile.define.Private;
import com.thjug.bgile.define.Status;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.Cache;

/**
 * 
 * @author nuboat
 */
@Entity
@Cacheable
@Cache(expiry = 300_000)
public class Board extends Time implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@SequenceGenerator(name = "board_seq_gen", sequenceName = "board_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
	private Integer id;

	@NotNull
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private Status statusid;

	@NotNull
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private Enable enableid;

	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private Private privateid;

	@NotNull
	@Basic(optional = false)
	@Size(min = 1, max = 128)
	private String boardname;

	@Size(max = 512)
	private String description;

	@Size(max = 256)
	private String logopath;

	private Integer maxcard;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board", fetch = FetchType.LAZY)
	private List<Burndown> burndownList;

	public Board() {
	}

	public Board(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Status getStatusid() {
		return statusid;
	}

	public void setStatusid(final Status statusid) {
		this.statusid = statusid;
	}

	public Enable getEnableid() {
		return enableid;
	}

	public void setEnableid(final Enable enableid) {
		this.enableid = enableid;
	}

	public Private getPrivateid() {
		return privateid;
	}

	public void setPrivateid(final Private privateid) {
		this.privateid = privateid;
	}

	public String getBoardname() {
		return boardname;
	}

	public void setBoardname(final String boardname) {
		this.boardname = boardname;
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

	public Integer getMaxcard() {
		return maxcard;
	}

	public void setMaxcard(final Integer maxcard) {
		this.maxcard = maxcard;
	}

	public List<Burndown> getBurndownList() {
		return burndownList;
	}

	public void setBurndownList(final List<Burndown> burndownList) {
		this.burndownList = burndownList;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof Board)) {
			return false;
		}
		final Board other = (Board) object;
		if (id == null) {
			return false;
		}
		return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "Project[ id=" + id + " ]";
	}

}
