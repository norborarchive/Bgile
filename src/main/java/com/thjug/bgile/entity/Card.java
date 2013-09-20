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
import com.thjug.bgile.define.Status;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "CARD")
@NamedQueries( { @NamedQuery(name = Card.FIND_BY_BOARD_AND_STATUS, query = "SELECT c FROM Card c WHERE c.board = ?1 and c.statusid = ?2"), })
public class Card extends Time implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_BOARD_AND_STATUS = "Card.findByBoardAndStatus";

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
	@SequenceGenerator(name = "card_seq_gen", sequenceName = "card_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq_gen")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = Columnsize.S1, max = Columnsize.S512)
	@Column(name = "story")
	private String story;
	@Basic(optional = false)
	@NotNull
	@Column(name = "stateid")
	private Integer stateid;
	@Basic(optional = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "statusid")
	private Status statusid;
	@JoinColumn(name = "board", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board board;
	@JoinColumn(name = "owner", referencedColumnName = "id")
	@ManyToOne
	private Account owner;
	@Column(name = "estimate")
	private Integer estimate;
	@Column(name = "description")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
	private List<Todo> todoList;

	public Card() {
	}

	public Card(final Integer id) {
		this.id = id;
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

	public Account getOwner() {
		return owner;
	}

	public void setOwner(final Account owner) {
		this.owner = owner;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(final Integer stateid) {
		this.stateid = stateid;
	}

	public Status getStatusid() {
		return statusid;
	}

	public void setStatusid(final Status statusid) {
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

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Card)) {
			return false;
		}
		final Card other = (Card) object;
		if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Card[ id=" + id + " ]";
	}

}
