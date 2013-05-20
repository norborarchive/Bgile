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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author @nuboat
 */
@Entity
@Table(name = "account")
@NamedQueries( { @NamedQuery(name = Account.findAll, query = "SELECT a FROM Account a"),
		@NamedQuery(name = Account.findByTypeid, query = "SELECT a FROM Account a WHERE a.typeid = ?1"),
		@NamedQuery(name = Account.findByUsername, query = "SELECT a FROM Account a WHERE a.username = ?1"),
		@NamedQuery(name = Account.findAllCount, query = "SELECT COUNT(a) FROM Account a"), })
public class Account implements Serializable, Converterable, Timeable {

	private static final long serialVersionUID = 1L;

	public static final String findAll = "Account.findAll";
	public static final String findByTypeid = "Account.findByTypeid";
	public static final String findByUsername = "Account.findByUsername";
	public static final String findAllCount = "Account.findAllCount";

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "account_seq_gen", sequenceName = "account_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "typeid")
	private char typeid;
	@Basic(optional = false)
	@NotNull
	@Column(name = "enable")
	private char enable;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 64)
	@Column(name = "username")
	private String username;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 64)
	@Column(name = "passwd")
	private String passwd;
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "email")
	private String email;
	@Size(max = 64)
	@Column(name = "firstname")
	private String firstname;
	@Size(max = 64)
	@Column(name = "lastname")
	private String lastname;
	@Size(max = 32)
	@Column(name = "twitter")
	private String twitter;
	@Size(max = 128)
	@Column(name = "bio")
	private String bio;
	@Size(max = 256)
	@Column(name = "avatarpath")
	private String avatarpath;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = "updateby")
	private Integer updateby;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountid")
	private List<Boardaccount> boardaccountList;
	@OneToMany(mappedBy = "ownerid")
	private List<Userstory> userstoryList;

	public Account() {
	}

	public Account(final Integer id) {
		this.id = id;
	}

	public Account(final Integer id, final char typeid, final char enable, final String username, final String passwd,
			final String email) {
		this.id = id;
		this.typeid = typeid;
		this.enable = enable;
		this.username = username;
		this.passwd = passwd;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public char getTypeid() {
		return typeid;
	}

	public void setTypeid(final char typeid) {
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

	public char getEnable() {
		return enable;
	}

	public void setEnable(char enable) {
		this.enable = enable;
	}

	public List<Boardaccount> getBoardaccountList() {
		return boardaccountList;
	}

	public void setBoardaccountList(final List<Boardaccount> boardaccountList) {
		this.boardaccountList = boardaccountList;
	}

	public List<Userstory> getUserstoryList() {
		return userstoryList;
	}

	public void setUserstoryList(final List<Userstory> userstoryList) {
		this.userstoryList = userstoryList;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
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
