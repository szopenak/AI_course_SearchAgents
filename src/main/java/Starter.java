import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Starter {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Starter.class);
		final String BFS = "bfs";
		final String DFS = "dfs";

		if(args.length < 2) {
			log.error("2 arguments needed - agent type and matrix delimited by commas");
			return;
		}
		
		// Prepare arguments and two basic states
		String type = args[0];
		String numbers = args[1];
		List<String> separatedNumbers = Arrays.asList(numbers.split(","));

		State initial = new State(new Matrix(createMatrix(separatedNumbers)));
		log.info("Initial matrix:"+System.lineSeparator()+initial.showData());

		Collections.sort(separatedNumbers);
		State goal = new State(new Matrix(createMatrix(separatedNumbers)));
		log.info("Goal matrix:"+System.lineSeparator()+goal.showData());
		
		// Choose agent and start
		Frontier frontier;
		if (type.equals(BFS)) {
			frontier = new BfsFrontier();
		} else {
			frontier = new DfsFrontier();
		}
		
		Agents agent = new Agents(initial, goal, frontier);
		log.info("Starting searching...");
		agent.start();
	}
	
	/**
	 * Create 3x3 matrix based on input number list
	 * @param data
	 * @return
	 */
	static private int[][] createMatrix(List<String>data) {
		int matrix[][] = new int[3][3];
		for (int x = 0; x<3 ; x++) {
			for(int y = 0; y<3 ; y++) {
				matrix[x][y] = Integer.valueOf(data.get(x*3+y));
			}
		}
		return matrix;
	}

}
