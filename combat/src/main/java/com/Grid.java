package com;

import java.math.BigDecimal;

public class Grid {
	public static final int REACH = 3;
	public static final int WIDTH = 18;
	public static final int HEIGHT = 60; //FULL HD or 40 to HD
	public static final int MOVE_FACTOR = 32;
	public static final BigDecimal AXIS_FACTOR = BigDecimal.valueOf((double)WIDTH/(double)HEIGHT);
	
//	public void movement(Position attack, Position defense, int reach) {
//		while(distance(attack, defense) > reach) {
//			move(attack, defense, 1);
//		}
//	}
}
