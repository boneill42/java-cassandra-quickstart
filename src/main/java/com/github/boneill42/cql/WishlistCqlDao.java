package com.github.boneill42.cql;

import static com.datastax.driver.core.querybuilder.QueryBuilder.add;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.querybuilder.Update.Where;

public class WishlistCqlDao {
    private static final Logger LOG = LoggerFactory.getLogger(WishlistCqlDao.class);

    public static final String KEYSPACE = "wishlists";
    public static final String TABLE = "wishlist";
    protected static Cluster cluster;
    protected static Session session;

    public WishlistCqlDao(String host) {
        try {
            cluster = Cluster.builder().addContactPoints(host).build();
            session = cluster.connect();
            session.execute("USE " + KEYSPACE);
        } catch (NoHostAvailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void addItem(String tenant, String country, String state, String zip, String userId, String item) throws NoHostAvailableException {
        Where update = update(KEYSPACE, TABLE).with(add("wishlist", item))
        		.where(eq("tenant", tenant))
        		.and(eq("country", country))
        		.and(eq("state", state))
        		.and(eq("zip", zip))
        		.and(eq("userId", userId));
//        LOG.debug("Raw [" + query + "]");
        LOG.debug("QueryBuilder [" + update.toString() + "]");
        session.execute(update);
    }

}
