package net.pureessence.matcher;

import java.util.List;

import net.pureessence.dao.SearchDao;
import net.pureessence.domain.Key;
import net.pureessence.domain.Record;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomMatcherExample {
    @Autowired
    private SearchDao searchDao;
    
    @Autowired
    private Log log;
    
    protected Record getClosetMatchingRecord(List<Key> keys) {
	Record matchingRecord = null;
	for(Key key : keys) {
	    List<Record> results = searchDao.getRecordsByKey(key);
	    log.info(String.format("Found '%s' records", results.size()));
	    for(Record result : results) {
		// some logic to match the data into the key...
		// ... maybe in a if statement
		matchingRecord = result;
	    }
	}
	return matchingRecord;
    }
}
