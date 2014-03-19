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

import com.thjug.bgile.define.Status;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author @nuboat
 */
@Entity
@NamedQueries({ @NamedQuery(name = Card.FIND_BY_BOARD_AND_STATUS, query = ""
		+ "SELECT c FROM Card c WHERE c.board = ?1 and c.statusid = ?2"), })
public class Card extends Time implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_BOARD_AND_STATUS = "Card.findByBoardAndStatus";

	@Id
	@NotNull
	@Basic(optional = false)
	@SequenceGenerator(name = "card_seq_gen", sequenceName = "card_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq_gen")
	private Integer id;

	@NotNull
	@Basic(optional = false)
	@Size(min = 1, max = 512)
	private String story;

	@NotNull
	@Basic(optional = false)
	private Integer stateid;

	@NotNull
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private Status statusid;

	@JoinColumn(name = "board", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Board board;

	@JoinColumn(name = "owner", referencedColumnName = "id")
	@ManyToOne
	private Account owner;

	private Integer estimate;

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
		return Collections.unmodifiableList(todoList);
	}

	public void setTodoList(final List<Todo> todoList) {
		this.todoList = todoList;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(final Integer estimate) {
		this.estimate = estimate;
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
		if (!(object instanceof Card)) {
			return false;
		}
		final Card other = (Card) object;
		if (id == null) {
			return false;
		}
		return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "Card[ id=" + id + " ]";
	}

}
