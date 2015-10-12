package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Combat implements Runnable {
	
	private long round = 3l;
	private List<Soldier> ordemDeCombate = new ArrayList<Soldier>();
	private long tempoAtual;
	private boolean acabou;
	private Team attackTeam;
	private Team defenseTeam;
	private Semaphore semaphore = new Semaphore(0);

	@Override
	public void run() {
		this.setAcabou(false);
		attackTeam.defineCombat(this);
		defenseTeam.defineCombat(this);
		attackTeam.defineEnemies(defenseTeam);
		defenseTeam.defineEnemies(attackTeam);
		List<Soldier> soldiers = new ArrayList<Soldier>();
		soldiers.addAll(attackTeam.getSoldiers());
		soldiers.addAll(defenseTeam.getSoldiers());
		for (Soldier soldier : soldiers) {
			soldier.setSemaphore(semaphore);
			new Thread(soldier).start();
		}
//		for (Soldier soldier1 : attackTeam.getSoldiers()) {
//			soldier1.setSemaphore(semaphore);
//			new Thread(soldier1).start();
//			System.out.println("continuando inicializacao!");
//		}
//		for (Soldier soldier : defenseTeam.getSoldiers()) {
//			soldier.setSemaphore(semaphore);
//			new Thread(soldier).start();
//			System.out.println("continuando inicializacao!");
//		}
		try {
			synchronized (this) {
				this.wait(2000);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		long tempoInicial = System.currentTimeMillis();
		long tempoFinal = 90 * 1000;
		
		synchronized (semaphore) {
			semaphore.notifyAll();
		}
		
		do {
			if(attackTeam == null || defenseTeam == null) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			this.tempoAtual = System.currentTimeMillis();
		} while(tempoAtual - tempoInicial < tempoFinal && !attackTeam.lost() && !defenseTeam.lost());
		this.setAcabou(true);
		System.out.println("Combate Encerrou! Tempo: " + (tempoAtual-tempoInicial)/1000 + " segundos." );
	}

	public synchronized boolean acabou() {
		return isAcabou();
	}

	public synchronized boolean isAcabou() {
		return acabou;
	}

	public synchronized void setAcabou(boolean acabou) {
		this.acabou = acabou;
	}

	public Team getAttackTeam() {
		return attackTeam;
	}

	public void setAttackTeam(Team attackTeam) {
		this.attackTeam = attackTeam;
	}

	public Team getDefenseTeam() {
		return defenseTeam;
	}

	public void setDefenseTeam(Team defenseTeam) {
		this.defenseTeam = defenseTeam;
	}
}
