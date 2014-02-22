/**
 * Attribution CC BY This license lets others distribute, remix, tweak, and build upon your work, even commercially, as
 * long as they credit you for the original creation. This is the most accommodating of licenses offered. Recommended
 * for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/ http://creativecommons.org/licenses/by/3.0/legalcode
 */
package com.thjug.bgile.facade;

import com.thjug.bgile.define.Status;
import com.thjug.bgile.entity.Account;
import com.thjug.bgile.entity.BoardAccount;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 
 * @author nuboat
 */
public class BoardAccountFacadeNGTest extends AbstractFacadeNGTest {

	private static final Logger LOG = LoggerFactory.getLogger(BoardAccountFacadeNGTest.class);

	@Test
	public void testFindAllByAccount() {
		final Account account = new Account(2);
		final BoardAccountFacade instance = injector.getInstance(BoardAccountFacade.class);
		final List<BoardAccount> result = instance.findAllByAccount(account);

		for (final BoardAccount b : result) {
			assertNotEquals(b.getBoard().getStatusid(), Status.D);
		}
	}

}
