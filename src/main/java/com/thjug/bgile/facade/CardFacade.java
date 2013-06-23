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

import com.thjug.bgile.facade.impl.CardFacadeImpl;
import java.util.List;

import com.google.inject.ImplementedBy;
import com.thjug.bgile.entity.Card;

/**
 *
 * @author @nuboat
 */
@ImplementedBy(CardFacadeImpl.class)
public interface CardFacade {

	Card create(final Integer accountid, final Integer boardid, final Card story);

	Card edit(final Integer accountid, final Card story);

	Card remove(final Integer accountid, final Card story);

	Card findById(final Integer accountid, final Integer storyid);

	List<Card> findAllByBoardId(final Integer accountid, final Integer boardid);

	Card move(final Integer accountid, final Integer storyid, final char fromstate, final char tostate);

}
