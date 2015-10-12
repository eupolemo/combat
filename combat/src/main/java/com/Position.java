package com;

public class Position {

	private int x; //WIDTH
	private int y; //HEIGHT
	
	Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean leftSide(Position target) {
		if(this.x < target.getX()) {
			return true;
		}
		return false;
	}
	
	public boolean downSide(Position target) {
		if(this.y < target.getY()) {
			return true;
		}
		return false;
	}
	
	public int xDifference(Position target) {
		if(this.leftSide(target)) {
			return target.getX() - this.getX();
		}
		return this.getX() - target.getX();
	}
	
	public int yDifference(Position target) {
		if(this.downSide(target)){
			return target.getY() - this.getY();
		}
		return this.getY() - target.getY();
	}
	@Override
	public String toString() {
		return "Position (" + x + "," + y + ")";
	}
}
