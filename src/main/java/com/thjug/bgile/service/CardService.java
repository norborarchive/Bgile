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
package com.thjug.bgile.service;

import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Card;
import static com.thjug.bgile.facade.CardFacade.STATE0;
import java.util.List;

/**
 *
 * @author @nuboat
 */
public final class CardService extends AbstractService<Card> {

	public CardService() {
		super(Card.class);
	}

	public List<Card> findByBoard(final Board board) {
		final List<Card> storys = findAll(Card.findByBoardAndStatus, board, LIVE);

		return storys;
	}

	public Card createNewStory(final Card story) {
		story.setStateid(STATE0);
		story.setStatusid(LIVE);
		return create(story);
	}

}
