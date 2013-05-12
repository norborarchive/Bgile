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
package com.thjug.bgile.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @nuboat
 */
@Path("/hello")
public final class EchoService {

	private static final Logger LOG = LoggerFactory.getLogger(EchoService.class);

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public final Response getJson(@PathParam("param") final String msg) {
		final String echomsg = msg.length() <= 24 ? msg : msg.substring(0, 24);

		LOG.info("Echo: {}", echomsg);
		return Response.status(200).entity(echomsg).build();
	}

}
