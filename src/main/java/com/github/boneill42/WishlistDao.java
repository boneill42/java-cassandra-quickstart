package com.github.boneill42;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.ColumnList;
import com.netflix.astyanax.serializers.AnnotatedCompositeSerializer;
import com.netflix.astyanax.serializers.CompositeRangeBuilder;
import com.netflix.astyanax.serializers.StringSerializer;

public class WishlistDao extends AstyanaxDao {
    private static final Logger LOG = LoggerFactory.getLogger(WishlistDao.class);
    public static final String TABLE_NAME = "wishlist";
    private static AnnotatedCompositeSerializer<Wishlist> ENTITY_SERIALIZER = new AnnotatedCompositeSerializer<Wishlist>(
            Wishlist.class);
    private static ColumnFamily<String, Wishlist> COLUMN_FAMILY = new ColumnFamily<String, Wishlist>(TABLE_NAME,
            StringSerializer.get(), ENTITY_SERIALIZER);

    public WishlistDao(String host, String keyspace) {
        super(host, keyspace);
    }

    public void write(String rowKey, Wishlist listEntry) throws ConnectionException {
        MutationBatch mutation = this.getKeyspace().prepareMutationBatch();
        mutation.withRow(COLUMN_FAMILY, rowKey).putColumn(listEntry, new byte[0], null);
        mutation.execute();
        LOG.debug("Wrote to list [" + rowKey + "]");
    }

    public ColumnList<Wishlist> read(String rowKey) throws ConnectionException {
        OperationResult<ColumnList<Wishlist>> result = this.getKeyspace().prepareQuery(COLUMN_FAMILY).getKey(rowKey)
                .execute();
        ColumnList<Wishlist> users = result.getResult();
        LOG.debug("Read list [" + rowKey + "]");
        return users;
    }

    public ColumnList<Wishlist> find(String rowKey, String state, String zip)
            throws ConnectionException {
        CompositeRangeBuilder range = ENTITY_SERIALIZER.buildRange();
        // TODO: Track issue here: (https://github.com/Netflix/astyanax/issues/80)
        if (zip == null) {
            range = ENTITY_SERIALIZER.buildRange().withPrefix(state).greaterThanEquals("").lessThanEquals("99999");
        } else {
            range = ENTITY_SERIALIZER.buildRange().withPrefix(state).greaterThanEquals(zip).lessThanEquals(zip);
        }
        OperationResult<ColumnList<Wishlist>> result = this.getKeyspace().prepareQuery(COLUMN_FAMILY).getKey(rowKey)
                .withColumnRange(range).execute();
        ColumnList<Wishlist> users = result.getResult();
        LOG.debug("Found [" + users.size() + "] users in [" + rowKey + "]");
        return users;
    }
}
