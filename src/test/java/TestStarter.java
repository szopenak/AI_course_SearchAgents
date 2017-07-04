import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStarter {
	
	private Logger log;

	@Before
	public void setUp() throws Exception {
		log = LoggerFactory.getLogger(this.getClass());
	}
	
	@Test
	public void bfsTest() {
		log.warn("Startint bfs test...");
		Starter.main(new String[] {"bfs","6,1,8,4,0,2,7,3,5"});
		Starter.main(new String[] {"bfs","8,6,4,2,1,3,5,7,0"});
	}
	
	@Test
	public void dfsTest() {
		log.warn("Startint dfs test...");
		Starter.main(new String[] {"dfs","6,1,8,4,0,2,7,3,5"});
		Starter.main(new String[] {"dfs","8,6,4,2,1,3,5,7,0"});
	}

}
