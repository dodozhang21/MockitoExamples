package net.pureessence.matcher;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import net.pureessence.dao.SearchDao;
import net.pureessence.domain.Key;
import net.pureessence.domain.Record;

import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomMatcherExampleTest {
    @Mock
    private SearchDao searchDao;

    @Mock
    private Log log;

    @InjectMocks
    private CustomMatcherExample example = new CustomMatcherExample();

    @Test
    public void keysShouldReturnResults() {
	// -----arrange
	int division = 1;
	int state = 14;
	int agency = 123;
	
	// test stubs
	Key key1 = new Key(division, state, agency, " MP", 456);
	Key key2 = new Key(division, state, agency, "RPL", 775);
	Key key3 = new Key(22, 12, 975, "HSC", 4577);
	Key key4 = new Key(division, state, agency, "HSC", 245);
	List<Key> keys = Arrays.asList(key1, key2, key3, key4);
	
	// mock objects
	Record r1 = mock(Record.class);
	Record r2 = mock(Record.class);
	
	// mocking
	when(searchDao.getRecordsByKey(argThat(new KeyDivisionStateAgencyMatcher(division, state, agency)))).thenReturn(Arrays.asList(r1, r2));

	// -----act
	Record matchingRecord = example.getClosetMatchingRecord(keys);

	// -----assert
	// should log exactly 3 times
	verify(log, times(3)).info("Found '2' records");
	assertNotNull(matchingRecord);
    }

    class KeyDivisionStateAgencyMatcher extends ArgumentMatcher<Key> {
	private Integer division;
	private Integer state;
	private Integer agency;

	public KeyDivisionStateAgencyMatcher(Integer division, Integer state,
		Integer agency) {
	    super();
	    this.division = division;
	    this.state = state;
	    this.agency = agency;
	}

	@Override
	public boolean matches(Object argument) {
	    if (argument instanceof Key) {
		Key key = (Key) argument;
		String keyStringShouldBeginWith = String.format("division '%s', state '%s', agency '%s'", 
			division, 
			state, 
			agency);
		if (key.getKeyString().startsWith(keyStringShouldBeginWith)) {
		    return true;
		}
	    }
	    return false;
	}
    }
}
