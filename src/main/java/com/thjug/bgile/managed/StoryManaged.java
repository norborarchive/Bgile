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
import com.thjug.bgile.facade.ProjectFacade;
import com.thjug.bgile.facade.UserstoryFacade;
import com.thjug.bgile.util.Constants;
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
public class StoryManaged extends AbstractManaged {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(StoryManaged.class);

	private Userstory userstory;

	@Inject
	private transient UserstoryFacade userstoryFacade;

	@Inject
	private transient ProjectFacade projectFacade;

	@PostConstruct
	public void initial() {
		userstory = new Userstory();
	}

	public String saveStory() {
		try {
			userstory.setUpdateby(2); // FIXME

			if (userstory.getId() == null) {
				userstory.setProjectid(projectFacade.findById(1)); // FIXME
				userstory = userstoryFacade.create(userstory);
			} else {
				userstory = userstoryFacade.edit(userstory);
			}

			return "board?faces-redirect=true";
		} catch (final Exception e) {
			addErrorMessage(e.getMessage(), Constants.EMPTY);

			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	public String removeStory() {
		try {
			userstory.setUpdateby(1); // FIXME
			userstoryFacade.remove(userstory);

			return "board?faces-redirect=true";
		} catch (final Exception e) {
			addErrorMessage(e.getMessage(), Constants.EMPTY);

			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	public Userstory getUserstory() {
		return userstory;
	}

	public void setUserstory(final Userstory userstory) {
		this.userstory = userstory;
	}

}

//	private Account selectedOwner;

//	private List<Account> ownerList;
//
//	@Inject
//	private transient AccountFacade accountFacade;
//
//  @PostConstruct
//	public void initial() {
//		try {
//			ownerList = accountFacade.findAllStaff();
//		} catch (final Exception e) {
//			final Account account = new Account();
//			account.setId(-1);
//			account.setFirstname("Mr. Dummy");
//			account.setLastname(Constants.EMPTY);
//			ownerList = new LinkedList<>();
//			ownerList.add(account);
//
//			LOG.error(e.getMessage(), e);
//		}
//	}
//
//	public List<Account> getOwnerList() {
//		return ownerList;
//	}
//
//	public void setOwnerList(final List<Account> ownerList) {
//		this.ownerList = ownerList;
//	}
//
//	public Account getSelectedOwner() {
//		return selectedOwner;
//	}
//
//	public void setSelectedOwner(final Account selectedOwner) {
//		this.selectedOwner = selectedOwner;
//	}
