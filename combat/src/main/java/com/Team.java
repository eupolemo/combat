package com;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable,Runnable {
	private List<Soldier> soldiers;
	private List<Thread> threads;
	
	public Team() {
		soldiers = new ArrayList<Soldier>();
		threads = new ArrayList<Thread>();
	}

	public List<Soldier> getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(List<Soldier> team) {
		this.soldiers = team;
	}

	public Soldier near(Position position) {
		Soldier soldierNear = null;
		for (Soldier soldado : this.soldiers) {
			if(soldierNear == null) {
				soldierNear = soldado;
			} else {
				if(soldierNear.distance(position) > soldado.distance(position)) {
					soldierNear = soldado;
				}
			}
		}
		
		return soldierNear;
	}

	public void defineCombat(Combat combat) {
		for (Soldier soldier : this.soldiers) {
			soldier.setCombate(combat);
		}
	}
	
	public boolean lost() {
		for (Soldier soldier : this.soldiers) {
			if(soldier.dead() == false) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void run() {
		for (Soldier soldier : this.soldiers) {
			new Thread(soldier).start();
		}
	}

	public void defineEnemies(Team team) {
		for (Soldier soldier : this.soldiers) {
			soldier.setEnemyTeam(team);
		}
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}
}
