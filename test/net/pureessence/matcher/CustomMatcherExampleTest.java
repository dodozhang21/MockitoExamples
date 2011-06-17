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
		int state = 1;
		int postalCode = 90001;
		
		// test stubs
		Key key1 = new Key(state, postalCode, "ML", 456);
		Key key2 = new Key(state, postalCode, "UIY", 775);
		Key key3 = new Key(22, 12, "OKJ", 4577);
		Key key4 = new Key(state, postalCode, "IKJ", 245);
		List<Key> keys = Arrays.asList(key1, key2, key3, key4);
		
		// mock objects
		Record r1 = mock(Record.class);
		Record r2 = mock(Record.class);
		
		// mocking
		when(searchDao.getRecordsByKey(argThat(new KeyStatePostalCodeMatcher(state, postalCode)))).thenReturn(Arrays.asList(r1, r2));
	
		// -----act
		Record matchingRecord = example.getClosetMatchingRecord(keys);
	
		// -----assert
		verify(log, times(3)).info("Found '2' records");
		verify(log, times(1)).info("Found '0' records");
		assertNotNull(matchingRecord);
    }

    class KeyStatePostalCodeMatcher extends ArgumentMatcher<Key> {
		private Integer state;
		private Integer postalCode;
	
		public KeyStatePostalCodeMatcher(Integer state, Integer postalCode) {
			super();
			this.state = state;
			this.postalCode = postalCode;
		}

		@Override
		public boolean matches(Object argument) {
		    if (argument instanceof Key) {
				Key key = (Key) argument;
				String keyStringShouldBeginWith = String.format("state '%s', postalCode '%s'", 
																	state, 
																	postalCode);
				if (key.getKeyString().startsWith(keyStringShouldBeginWith)) {
				    return true;
				}
		    }
		    return false;
		}
    }
}
