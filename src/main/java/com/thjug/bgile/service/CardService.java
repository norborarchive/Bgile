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

import com.thjug.bgile.define.Status;
import com.thjug.bgile.entity.Board;
import com.thjug.bgile.entity.Card;
import java.util.List;

/**
 * 
 * @author @nuboat
 */
public class CardService extends AbstractService<Card> {

	public CardService() {
		super(Card.class);
	}

	public List<Card> findCardList(final Board board) {

		return findAll(Card.FIND_BY_BOARD_AND_STATUS, board, Status.L);
	}

	public Card createCard(final Card card) {

		card.setStateid(0);
		card.setStatusid(Status.L);

		return create(card);
	}

}
