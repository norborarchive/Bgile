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
@Table(name = "board")
public class Board implements Serializable, Timeable {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
	private List<Storyorder> storyorderList;

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@SequenceGenerator(name = "board_seq_gen", sequenceName = "board_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "statusid")
	private char statusid;
	@Basic(optional = false)
	@NotNull
	@Column(name = "enable")
	private char enable;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "boardname")
	private String boardname;
	@Size(max = 512)
	@Column(name = "description")
	private String description;
	@Size(max = 256)
	@Column(name = "logopath")
	private String logopath;
	@Size(max = 256)
	@Column(name = "maxcard")
	private int maxcard;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = "updateby")
	private Integer updateby;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
	private List<Boardaccount> boardaccountList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
	private List<Card> cardList;

	public Board() {
	}

	public Board(final Integer id) {
		this.id = id;
	}

	public Board(final Integer id, final char statusid, final char enable, final String boardname) {
		this.id = id;
		this.statusid = statusid;
		this.enable = enable;
		this.boardname = boardname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public char getStatusid() {
		return statusid;
	}

	public void setStatusid(final char statusid) {
		this.statusid = statusid;
	}

	public char getEnable() {
		return enable;
	}

	public void setEnable(char enable) {
		this.enable = enable;
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

	public int getMaxcard() {
		return maxcard;
	}

	public void setMaxcard(int maxcard) {
		this.maxcard = maxcard;
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

	public List<Boardaccount> getBoardaccountList() {
		return boardaccountList;
	}

	public void setBoardaccountList(List<Boardaccount> boardaccountList) {
		this.boardaccountList = boardaccountList;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(final List<Card> cardList) {
		this.cardList = cardList;
	}

	public List<Storyorder> getStoryorderList() {
		return storyorderList;
	}

	public void setStoryorderList(List<Storyorder> storyorderList) {
		this.storyorderList = storyorderList;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Board)) {
			return false;
		}
		final Board other = (Board) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Project[ id=" + id + " ]";
	}

}
