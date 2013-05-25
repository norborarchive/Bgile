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
import com.thjug.bgile.entity.Userstory;
import com.thjug.bgile.facade.UserstoryFacade;
import com.thjug.bgile.util.Constants;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@ManagedBean
@ViewScoped
public class FstoryManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FstoryManaged.class);
	private Userstory userstory;
	@Inject
	private transient UserstoryFacade facade;

	@PostConstruct
	public void initial() {
		final String storyid = (String) getStoryfromAttribute();
		if (storyid != null) {
			try {
				userstory = facade.findById(getAccountId(), Integer.valueOf(storyid));
			} catch (final Exception e) {
				LOG.error(e.getMessage(), e);
				addErrorMessage("Story: {} not found.", storyid);
			}
		} else {
			userstory = new Userstory();
		}
	}

	private String getStoryfromAttribute() {
		final List<String> attributes = (List<String>) getAttribute("ATTRIBUTES");
		return (attributes.size() > 0) ? attributes.get(0) : null;
	}

	public String saveStory() {
		try {
			final Integer boardid = Integer.valueOf(getSession().getAttribute("boardid").toString());
			userstory = (userstory.getId() == null) ? facade.create(getAccountId(), boardid, userstory) : facade.edit(
					getAccountId(), userstory);
			return redirect("board");
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addWarnMessage(e.getMessage(), Constants.EMPTY);
		}
		return null;
	}

	public String removeStory() {
		try {
			userstory = facade.remove(getAccountId(), userstory);
			return redirect("board");
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			addWarnMessage(e.getMessage(), Constants.EMPTY);
		}
		return null;
	}

	public Userstory getUserstory() {
		return userstory;
	}

	public void setUserstory(final Userstory userstory) {
		this.userstory = userstory;
	}

	public boolean isNewUserstory() {
		return userstory.getId() == null;
	}

	public Integer getBoardid() {
		return (Integer) getSession().getAttribute("boardid");
	}
}
