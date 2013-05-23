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
public interface UserstoryFacade extends AbstractFacade {

	public static final char STATE0 = '0';
	public static final char STATE1 = '1';
	public static final char STATE2 = '2';
	public static final char STATE3 = '3';

	public Userstory create(final Integer accountid, final Integer boardid, final Userstory story) throws Exception;

	public Userstory edit(final Integer accountid, final Userstory story) throws Exception;

	public Userstory remove(final Integer accountid, final Userstory story) throws Exception;

	public Userstory findById(final Integer accountid, final Integer storyid) throws Exception;

	public List<Userstory> findAllByBoardId(final Integer accountid, final Integer boardid) throws Exception;

}
