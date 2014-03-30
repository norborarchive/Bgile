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

import com.google.inject.persist.Transactional;
import com.thjug.bgile.entity.Property;
import com.thjug.bgile.interceptor.Logging;
import com.thjug.bgile.service.PropertyService;
import javax.inject.Inject;

/**
 *
 * @author nuboat
 */
public class PropertyFacade {

	@Inject
	private PropertyService service;

	@Logging
	@Transactional
	public String getString(final String id) {
		final Property property = service.findById(id);
		return property != null ? property.getString() : null;
	}

}
