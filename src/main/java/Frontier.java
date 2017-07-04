import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public abstract class Frontier {
	Set <Matrix> visited;
	Set <Matrix> inFrontier;
	int maxDepth = 0;

	public abstract State nextState();
	public abstract void addStates(List<State> states);
	public Frontier() {
		super();
		this.visited = new HashSet<>();
		this.inFrontier = new HashSet<>();
	}
}
