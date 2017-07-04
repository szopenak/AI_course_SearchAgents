import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BfsFrontier extends Frontier {

	private Queue<State> queue = new LinkedList<>();
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public State nextState() {
		// TODO Auto-generated method stub
		State state = queue.poll();
		inFrontier.remove(state.getData());
		visited.add(state.getData());
		return state;
	}

	@Override
	public void addStates(List<State> states) {
		states.stream().filter(a-> !visited.contains(a.getData())).filter(a-> !inFrontier.contains(a.getData())).forEach(state -> {
			if (state.getDepth() > maxDepth) {
				maxDepth = state.getDepth();
				//log.info("Going down to level "+maxDepth);
			}
			queue.add(state);
			inFrontier.add(state.getData());
		});
	}

}
