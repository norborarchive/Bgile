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
public interface CardFacade extends AbstractFacade {

	public static final char STATE0 = '0';
	public static final char STATE1 = '1';
	public static final char STATE2 = '2';
	public static final char STATE3 = '3';

	public Card create(final Integer accountid, final Integer boardid, final Card story) throws Exception;

	public Card edit(final Integer accountid, final Card story) throws Exception;

	public Card remove(final Integer accountid, final Card story) throws Exception;

	public Card findById(final Integer accountid, final Integer storyid) throws Exception;

	public List<Card> findAllByBoardId(final Integer accountid, final Integer boardid) throws Exception;

	public Card move(final Integer accountid, final Integer storyid, final char fromstate, final char tostate)
			throws Exception;

}
