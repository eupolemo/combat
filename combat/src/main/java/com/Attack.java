package com;

import java.util.Random;

public class Attack {
	private static final int CHANCE_ACERTO = 50;

	private int acerto;
	private int attackRate;
	private int attackPower;
	private int attackCriticalRate;
	private int attackCriticalDamage;
	private int armorPenetration;

	public Attack(int attackRate, int attackPower, int attackCriticalRate, int attackCriticalDamage, int armorPenetration) {
		Random random = new Random();
		this.acerto = random.nextInt(101);
		this.attackRate = attackRate;
		this.attackPower = attackPower;
		this.attackCriticalRate = attackCriticalRate;
		this.attackCriticalDamage = attackCriticalDamage;
		this.armorPenetration = armorPenetration;
	}

	public boolean hits(Soldier inimigo) {
		int chanceAcerto = CHANCE_ACERTO + (this.attackRate - inimigo.getDodgeRate())/100;
		int chanceAcertoFinal = chanceAcerto > 99 ? 99 : (chanceAcerto > 0 ? chanceAcerto : 1);
		return this.acerto <= chanceAcertoFinal ? true : false;
	}

	public String causeDamage(Soldier inimigo) {
		String log = "";
		if(this.acerto < (1 + (attackCriticalRate/100))) {
			int dano = (attackPower * 2) + (attackPower * attackCriticalDamage/100);
			inimigo.setCurrentDamage(inimigo.getCurrentDamage() + dano);
			 log = " acerta " + inimigo.getName() + " CRITICAMENTE causando " + dano + " de dano e sobrando " + (inimigo.getHitPoint() - inimigo.getCurrentDamage()) + " pontos de vida.";
		} else if (this.acerto <= this.attackRate) {
			int dano = attackPower - (attackPower * ((inimigo.getArmor() - this.armorPenetration)/10))/100;
			inimigo.setCurrentDamage(inimigo.getCurrentDamage() + dano);
			log = " acerta " + inimigo.getName() + " causando " + dano + " de dano e sobrando " + (inimigo.getHitPoint() - inimigo.getCurrentDamage()) + " pontos de vida.";
		}
		return log;
	}

}
