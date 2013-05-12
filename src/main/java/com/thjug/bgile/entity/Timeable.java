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

/**
 *
 * @author @nuboat
 */
public interface Timeable {

	public Date getCreated();

	public void setCreated(final Date created);

	public Date getUpdated();

	public void setUpdated(final Date updated);

}
