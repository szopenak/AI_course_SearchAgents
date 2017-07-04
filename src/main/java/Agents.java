import java.util.Arrays;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Agents {
	
	public Agents(State initial, State goal, Frontier frontier) {
		this.initial = initial;
		this.goal = goal;
		this.frontier = frontier;
	}
	private State initial;
	private State goal;
	private Frontier frontier;
	private State currentState;
	private long expandedNodes;
	
	public void start() {
		long time = System.nanoTime();
		expandedNodes = 0;
		setCurrentState(initial);
		frontier.visited.add(currentState.getData());
		while (true) {
			if (checkIsOk(currentState)) {
				Logger log = LoggerFactory.getLogger(this.getClass());
				log.info("Found: "+System.lineSeparator()+currentState.showData());
				log.info("Path: "+getPath(currentState));
				log.info("Expanded nodes: "+expandedNodes);
				log.info("Depth: "+currentState.getDepth());
				log.info("Elapsed time [ms]: "+String.valueOf((System.nanoTime()-time)/(Math.pow(10, 6))));
				log.info("############################");
				break;
			} else {
				frontier.addStates(currentState.getChildren());
				currentState = frontier.nextState();
				expandedNodes++;
			}
		}
		
	}

	private boolean checkIsOk(State state) {
		return (Arrays.deepEquals(goal.getData().getMatrix(), state.getData().getMatrix())) ? true : false;
	}

	public State getInitial() {
		return initial;
	}

	public void setInitial(State initial) {
		this.initial = initial;
	}

	public State getGoal() {
		return goal;
	}

	public void setGoal(State goal) {
		this.goal = goal;
	}

	public Frontier getFrontier() {
		return frontier;
	}

	public void setFrontier(Frontier frontier) {
		this.frontier = frontier;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	private String getPath(State state) {
		State parent = state.getParent();
		StringBuilder sb = new StringBuilder();
		Stack<String> stack = new Stack<>();
		stack.push(state.getWay());
		while (parent.getDepth()!=0) {
			stack.push(parent.getWay());
			parent = parent.getParent();
		}
		if (stack.size()>30) return "Too large to show, size: "+stack.size();
		while(!(stack.isEmpty())){
			sb.append(stack.pop()+" ");
		}
		return sb.toString();
	}

}
