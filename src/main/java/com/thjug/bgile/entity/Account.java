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

import com.thjug.bgile.define.Accounttype;
import com.thjug.bgile.define.Columnsize;
import com.thjug.bgile.define.Enable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "account")
@NamedQueries( {
		@NamedQuery(name = Account.COUNT_ALL, query = "SELECT COUNT(a) FROM Account a"),
		@NamedQuery(name = Account.FIND_BY_USERNAME, query = "SELECT a FROM Account a WHERE UPPER(a.username) = ?1"),
		@NamedQuery(name = Account.FIND_LIKE_KEYWORD, query = " SELECT a FROM Account a "
				+ " WHERE UPPER(a.username) LIKE ?1 " + "		OR UPPER(a.email) LIKE ?1"
				+ "		OR CONCAT(UPPER(a.firstname), UPPER(a.lastname)) LIKE ?1"), })
public class Account extends Time implements Serializable, Converterable {

	private static final long serialVersionUID = 1L;
	public static final String COUNT_ALL = "Account.countAll";
	public static final String FIND_BY_USERNAME = "Account.findByUsername";
	public static final String FIND_LIKE_KEYWORD = "Account.findByKeyword";

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "account_seq_gen", sequenceName = "account_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "typeid")
	private Accounttype typeid;
	@Basic(optional = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "enableid")
	private Enable enableid;
	@Basic(optional = false)
	@NotNull
	@Size(min = Columnsize.S1, max = Columnsize.S64)
	@Column(name = "username")
	private String username;
	@Basic(optional = false)
	@NotNull
	@Size(min = Columnsize.S1, max = Columnsize.S64)
	@Column(name = "passwd")
	private String passwd;
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
	@Basic(optional = false)
	@NotNull
	@Size(min = Columnsize.S1, max = Columnsize.S128)
	@Column(name = "email")
	private String email;
	@Size(max = Columnsize.S64)
	@Column(name = "firstname")
	private String firstname;
	@Size(max = Columnsize.S64)
	@Column(name = "lastname")
	private String lastname;
	@Size(max = Columnsize.S32)
	@Column(name = "twitter")
	private String twitter;
	@Size(max = Columnsize.S128)
	@Column(name = "bio")
	private String bio;
	@Size(max = Columnsize.S256)
	@Column(name = "avatarpath")
	private String avatarpath;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	private List<BoardAccount> boardaccountList;
	@OneToMany(mappedBy = "owner")
	private List<Card> cardList;

	public Account() {
	}

	public Account(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Accounttype getTypeid() {
		return typeid;
	}

	public void setTypeid(final Accounttype typeid) {
		this.typeid = typeid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(final String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(final String twitter) {
		this.twitter = twitter;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(final String bio) {
		this.bio = bio;
	}

	public String getAvatarpath() {
		return avatarpath;
	}

	public void setAvatarpath(final String avatarpath) {
		this.avatarpath = avatarpath;
	}

	public Enable getEnableid() {
		return enableid;
	}

	public void setEnableid(final Enable enableid) {
		this.enableid = enableid;
	}

	public List<BoardAccount> getBoardaccountList() {
		return boardaccountList;
	}

	public void setBoardaccountList(final List<BoardAccount> boardaccountList) {
		this.boardaccountList = boardaccountList;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(final List<Card> cardList) {
		this.cardList = cardList;
	}

	public String getDisplayname() {
		return lastname + " " + firstname;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(final Object object) {
		// Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Account)) {
			return false;
		}
		final Account other = (Account) object;
		if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Account[ id=" + id + " ]";
	}

	@Override
	public String getItemValue() {
		return this.id.toString();
	}

}
