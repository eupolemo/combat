package com;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.Semaphore;

import com.util.HeroLoader;
import com.util.JsonReader;

public class Runner {
	
	static HeroLoader heroLoader = new HeroLoader();

	public static void main(String[] args) throws Exception {
//		 jsonTest();
		// movementTest();
		 combatTest();
//		threadTest();
	}

	private static void threadTest() {
		Controller target = new Controller();
		new Thread(target).start();
	}

	public static class Controller implements Runnable {
		Semaphore s = new Semaphore(0);

		@Override
		public void run() {
			for(int i = 1; i < 100; i++) {
				new Thread(new Test(i, s)).start();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("notificando!");
			synchronized (s) {
				s.notifyAll();
			}
		}
	}

	public static class Test implements Runnable {
		private int i;
		private Semaphore s;

		public Test(int i) {
			this.i = i;
		}

		public Test(int i, Semaphore s) {
			this.s = s;
			this.i = i;
		}

		@Override
		public void run() {
//			while (s.availablePermits() == 0) {
			System.out.println(this.i + "-" + s.availablePermits());
				try {
					synchronized (s) {
						
						s.wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			}
			System.out.println("teste " + i + " passou");
		}

	}

	private static void jsonTest() throws IOException {
		JsonReader leitor = new JsonReader();
		String json = leitor.loadJson("cavaleirobrancotank.json");

		Soldier soldier = (Soldier) leitor.serialize(json, Soldier.class);
		System.out.println(soldier);
		soldier.updateVariablesAttributes();
		System.out.println(soldier);
	}

	private static void movementTest() {
		Soldier soldadoDefensor = new Soldier("Atacante", HeroType.NONE, 5, 10,
				20, 30, BigDecimal.valueOf(1l), BigDecimal.valueOf(2l),
				BigDecimal.valueOf(3l), new Position(0, 0), 3, new Skill(
						"skill 01"), new Skill("skill 02"), new Skill(
						"skill 03"), new Skill("skill 04"), new Skill(
						"skill 05"));
		Soldier soldadoAtacante = new Soldier("Defensor", HeroType.NONE, 5, 30,
				20, 10, BigDecimal.valueOf(3l), BigDecimal.valueOf(2l),
				BigDecimal.valueOf(1l), new Position(60, 18), 3, new Skill(
						"skill 01"), new Skill("skill 02"), new Skill(
						"skill 03"), new Skill("skill 04"), new Skill(
						"skill 05"));
		System.out.println("Distância antes de movemento: "
				+ soldadoAtacante.distance(soldadoDefensor.getPosition()));
		while (soldadoAtacante.distance(soldadoDefensor.getPosition()) > 3) {
			soldadoAtacante.move(soldadoDefensor.getPosition());
		}
		System.out.println("Distância após o movemento: "
				+ soldadoAtacante.distance(soldadoDefensor.getPosition()));
		System.out.println("Atacante: " + soldadoAtacante.getPosition()
				+ " - Defensor: " + soldadoDefensor.getPosition());
	}

	private static void combatTest() throws Exception {
		Soldier blackKnight = heroLoader.loadHero(HeroClass.BLACK_NIGHT, 100000);
		Soldier whiteKnight = heroLoader.loadHero(HeroClass.WHITE_KNIGHT, 100000);
		Soldier ninjaAssassin = heroLoader.loadHero(HeroClass.NINJA_ASSASSIN, 100000);
		
		blackKnight.setPosition(new Position(60, 18));
		whiteKnight.setPosition(new Position(0, 0));
		ninjaAssassin.setPosition(new Position(0, 3));

		System.out.println(whiteKnight.getName() + ": " + whiteKnight.getLevel() + " - " + blackKnight.getName() + ": " + blackKnight.getLevel());
		
		Combat combate = new Combat();
		Team attackTeam = new Team();
		attackTeam.getSoldiers().add(whiteKnight);
		attackTeam.getSoldiers().add(ninjaAssassin);
		combate.setAttackTeam(attackTeam);
		Team defenseTeam = new Team();
		defenseTeam.getSoldiers().add(blackKnight);
		combate.setDefenseTeam(defenseTeam);

		 System.out.println(blackKnight);
		 System.out.println(whiteKnight);

		Thread t = new Thread(combate);
		t.start();
	}
}
