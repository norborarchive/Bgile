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

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.thjug.bgile.interceptor.Logging;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <pre>
 * 
 * 1. HashAggregate  (cost=4.55..4.56 rows=1 width=12)
 * select	current_date, c1.board, sum(c2.estimate), sum(c3.estimate)
 * from 	card c1
 * left join card c2 on c1.id = c2.id and c2.stateid in (0,1,2)
 * left join card c3 on c1.id = c3.id and c3.stateid = 3
 * group by c1.board;
 * 
 * 2. HashAggregate  (cost=59.93..62.73 rows=1 width=4)
 * select	distinct current_date, c.board,
 * (select  sum(estimate)
 * from 	card
 * where	board = c.board and stateid in (0,1,2)
 * group by board) as estimate,
 * (select  sum(estimate)
 * from 	card
 * where	board = c.board and stateid = 3
 * group by board) as done
 * from 	card c;
 * 
 * query:
 * insert into burndown (statusdate, board, estimate, done) (
 * select	current_date as statusdate, c1.board, sum(c2.estimate) as estimate, sum(c3.estimate) as done
 * from 	card c1
 * left join card c2 on c1.id = c2.id and c2.stateid in (0,1,2)
 * left join card c3 on c1.id = c3.id and c3.stateid = 3
 * group by c1.board
 * );
 * </pre>
 * 
 * @author nuboat
 */
public class DailyStatFacade {

	private static final Logger LOG = LoggerFactory.getLogger(CardFacade.class);

	@Inject
	private transient Provider<EntityManager> provider;

	@Logging
	@Transactional
	public void execute() {
		final String insert = ""
				+ "insert into burndown (statusdate, board, estimate, done) (\n"
				+ "select	current_date as statusdate, c1.board, sum(c2.estimate) as estimate, sum(c3.estimate) as done\n"
				+ "from 	card c1 \n" + "	left join card c2 on c1.id = c2.id and c2.stateid in (0,1,2)\n"
				+ "	left join card c3 on c1.id = c3.id and c3.stateid = 3\n" + "group by c1.board\n" + ");";

		final Query query = provider.get().createNativeQuery(insert);
		final int result = query.executeUpdate();

		LOG.info("Run Batch {} rows", result);
	}

}
