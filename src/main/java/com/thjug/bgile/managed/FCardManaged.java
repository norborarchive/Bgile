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
package com.thjug.bgile.managed;

import com.google.inject.Inject;
import javax.faces.bean.ManagedBean;
import com.thjug.bgile.entity.Card;
import com.thjug.bgile.facade.CardFacade;
import com.thjug.bgile.util.Constants;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class FCardManaged extends AccountAbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FCardManaged.class);
	private Card card;
	@Inject
	private transient CardFacade facade;

	@PostConstruct
	public void initial() {
		final String cardid = getCardidfromAttribute();
		if (cardid != null) {
			try {
				card = facade.findById(getPrincipal().getId(), Integer.valueOf(cardid));
			} catch (final Exception e) {
				LOG.error(e.getMessage(), e);
				addErrorMessage("Card: {} not found.", cardid);
			}
		} else {
			card = new Card();
		}
	}

	private String getCardidfromAttribute() {
		final List<String> attributes = getAttribute("ATTRIBUTES");
		return (attributes.size() > 1) ? attributes.get(1) : null;
	}

	public String saveStory() {
		try {
			final Integer boardid = Integer.valueOf(getSession().getAttribute("boardid").toString());
			card = (card.getId() == null) ? facade.create(getPrincipal().getId(), boardid, card) : facade.edit(
					getPrincipal().getId(), card);
			return redirect("board");
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addWarnMessage(e.getMessage(), Constants.EMPTY);
		}
		return null;
	}

	public String removeStory() {
		try {
			card = facade.remove(getPrincipal().getId(), card);
			return redirect("board");
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addWarnMessage(e.getMessage(), Constants.EMPTY);
		}
		return null;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(final Card card) {
		this.card = card;
	}

	public boolean isNewCard() {
		return card.getId() == null;
	}

	public Integer getBoardid() {
		return (Integer) getSession().getAttribute("boardid");
	}
}
