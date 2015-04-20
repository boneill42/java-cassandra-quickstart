package com.github.boneill42.cql;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.astyanax.model.Column;
import com.netflix.astyanax.model.ColumnList;

public class WishlistCqlDaoTest {
	private static Logger LOG = LoggerFactory
			.getLogger(WishlistCqlDaoTest.class);

	@Test
	public void testAddItem() throws Exception {
		WishlistCqlDao dao = new WishlistCqlDao("localhost");
		
		//store1 |     IRL |     D |  EI33

		dao.addItem("store1", "IRL", "D", "E133", "owen.oneill", "chromebook");
	}

	public void log(ColumnList<String> columns) {
		for (Column<String> column : columns) {
			LOG.debug("[" + column.getName() + "]->[" + column.getStringValue()
					+ "]");
		}
	}
}
