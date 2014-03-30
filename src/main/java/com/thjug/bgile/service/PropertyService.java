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

import com.thjug.bgile.entity.Property;

/**
 *
 * @author nuboat
 */
public class PropertyService extends AbstractService<Property> {

	public PropertyService() {
		super(Property.class);
	}

	public Property findById(final String id) {
		return find(id);
	}

}
