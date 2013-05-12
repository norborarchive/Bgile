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
package com.thjug.bgile.facade;

import com.thjug.bgile.facade.impl.UserstoryFacadeImpl;
import java.util.List;

import com.google.inject.ImplementedBy;
import com.thjug.bgile.entity.Userstory;

/**
 *
 * @author @nuboat
 */
@ImplementedBy(UserstoryFacadeImpl.class)
public interface UserstoryFacade {

	public Userstory create(final Userstory story) throws Exception;

	public Userstory edit(final Userstory story) throws Exception;

	public void remove(final Userstory story) throws Exception;

	public List<Userstory> findAllByProjectid(final Integer projectid) throws Exception;

}
