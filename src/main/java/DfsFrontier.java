import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class DfsFrontier extends Frontier {

	private Stack<State> queue = new Stack<>();
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public State nextState() {
		// TODO Auto-generated method stub
		State state = queue.pop();
		inFrontier.remove(state.getData());
		visited.add(state.getData());
		return state;
	}

	@Override
	public void addStates(List<State> states) {
		states = Lists.reverse(states);
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
