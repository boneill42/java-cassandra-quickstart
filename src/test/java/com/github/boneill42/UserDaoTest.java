package com.github.boneill42;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.astyanax.model.Column;
import com.netflix.astyanax.model.ColumnList;

public class UserDaoTest {
	private static Logger LOG = LoggerFactory.getLogger(UserDaoTest.class);

	@Test
	public void testRead() throws Exception {
		UserDao dao = new UserDao("localhost:9160", "wishlists");
		log(dao.read("bart.simpson"));
	}

	@Test
	public void testWrite() throws Exception {
		UserDao dao = new UserDao("localhost:9160", "wishlists");
		Map<String, String> child = new HashMap<String, String>();
		child.put("firstname", "the");
		child.put("lastname", "devil");
		child.put("planeofexistence", "hell");
		dao.write("the.devil", child);
	}

	@Test
	public void testReadDevilChild() throws Exception {
		UserDao dao = new UserDao("localhost:9160", "wishlists");
		log(dao.read("the.devil"));
	}

//	@Test
//	public void testDelete() throws Exception {
//		UserDao dao = new UserDao("localhost:9160", "wishlists");
//		dao.delete("the.devil");
//	}

	public void log(ColumnList<String> columns) {
		for (Column<String> column : columns) {
			LOG.debug("[" + column.getName() + "]->[" + column.getStringValue()
					+ "]");
		}
	}
}
