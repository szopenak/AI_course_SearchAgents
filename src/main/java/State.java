import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.primitives.Ints;

public class State {
	private final Matrix data;
	private State parent;
	private String way;
	private int depth;

	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public State getParent() {
		return parent;
	}
	public void setParent(State parent) {
		this.parent = parent;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public State(Matrix data) {
		this.data = data;
	}
	public State(Matrix data, String way, State parent) {
		this(data);
		setParent(parent);
		setWay(way);
		setDepth(parent.getDepth()+1);
	}
	
	public Matrix getData() {
		return data;
	}
	
	List<State> getChildren() {
		int xy [] = getZero();
		List<State> children = new ArrayList<>();

		if (xy[0] > 0) {
			int[][] copy = (int[][]) Arrays.stream(this.getData().getMatrix()).map(el -> el.clone()).toArray($ -> this.getData().getMatrix().clone());
			int temp = copy[xy[0]-1][xy[1]];
			copy[xy[0]][xy[1]] = temp;
			copy[xy[0]-1][xy[1]] = 0;
			children.add(new State(new Matrix(copy), "Up", this));
		}
		
		if (xy[0] < 2) {
			int[][] copy = (int[][]) Arrays.stream(this.getData().getMatrix()).map(el -> el.clone()).toArray($ -> this.getData().getMatrix().clone());
			int temp = copy[xy[0]+1][xy[1]];
			copy[xy[0]][xy[1]] = temp;
			copy[xy[0]+1][xy[1]] = 0;
			children.add(new State(new Matrix(copy), "Down", this));
		}
		
		if (xy[1] > 0) {
			int[][] copy = (int[][]) Arrays.stream(this.getData().getMatrix()).map(el -> el.clone()).toArray($ -> this.getData().getMatrix().clone());
			int temp = copy[xy[0]][xy[1]-1];
			copy[xy[0]][xy[1]] = temp;
			copy[xy[0]][xy[1]-1] = 0;
			children.add(new State(new Matrix(copy), "Left", this));
		}
		
		if (xy[1] < 2) {
			int[][] copy = (int[][]) Arrays.stream(this.getData().getMatrix()).map(el -> el.clone()).toArray($ -> this.getData().getMatrix().clone());
			int temp = copy[xy[0]][xy[1]+1];
			copy[xy[0]][xy[1]] = temp;
			copy[xy[0]][xy[1]+1] = 0;
			children.add(new State(new Matrix(copy), "Right", this));
		}
		return children;
	}
	
	int[] getZero() {
		for(int x = 0; x<3; x++) {
			int pos = Ints.indexOf(this.getData().getMatrix()[x], 0);
			if (pos != -1) {
				return new int[]{x,pos};
			}
		}
		return null;
	}
	
	public String showData() {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x<3 ; x++) {
			sb.append("[ ");
			for(int y = 0; y<3 ; y++) {
				sb.append(data.getMatrix()[x][y]).append(" ");
			}
			sb.append("]"+System.lineSeparator());
		}
		return sb.toString();
	}

}
class Matrix {
	private int[][] matrix;
	
	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public Matrix(int[][] matrix) {
		super();
		this.matrix = matrix;
	}

	public int hashCode() {
		return Arrays.deepHashCode(matrix);
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Matrix)) {
			return false;
		}
		for (int row = 0; row <3 ; row++){
			if (!(Arrays.equals(this.matrix[row], ((Matrix)o).getMatrix()[row]  ) )) {
				return false;
			}
		}
		return true;
	}
}
