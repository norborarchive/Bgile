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

import com.thjug.bgile.facade.impl.ProjectFacadeImpl;
import com.google.inject.ImplementedBy;
import com.thjug.bgile.entity.Project;
import java.util.List;

/**
 *
 * @author @nuboat
 */
@ImplementedBy(ProjectFacadeImpl.class)
public interface ProjectFacade {

	public Project findById(final Integer id) throws Exception;

	public Project create(final Project project) throws Exception;

	public Project edit(final Project project) throws Exception;

	public void remove(final Project project) throws Exception;

	public List<Project> findAllByAccount(final Integer accountid) throws Exception;

}
