package net.pureessence.dao;

import java.util.List;

import net.pureessence.domain.Key;
import net.pureessence.domain.Record;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDao extends JdbcDaoSupport {
    public List<Record> getRecordsByKey(Key key) {
		// implementation is not required for Mockito to work
		return null;
    }
}
