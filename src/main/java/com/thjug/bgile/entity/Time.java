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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author @nuboat
 */
@MappedSuperclass
public abstract class Time implements Timeable {

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = "updateby")
	private Integer updateby;

	@Override
	public Date getCreated() {
		return (created != null) ? (Date) created.clone() : null;
	}

	@Override
	public void setCreated(final Date created) {
		this.created = (Date) created.clone();
	}

	@Override
	public Date getUpdated() {
		return (updated != null) ? (Date) updated.clone() : null;
	}

	@Override
	public void setUpdated(final Date updated) {
		this.updated = (Date) updated.clone();
	}

	@Override
	public Integer getUpdateby() {
		return updateby;
	}

	@Override
	public void setUpdateby(final Integer updateby) {
		this.updateby = updateby;
	}

}
