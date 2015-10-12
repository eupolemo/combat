package com;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Skill implements Serializable{

	private static final long serialVersionUID = 1L;
	@Expose private String name;
	@Expose private int level;
	public int dodgeRate = 0;
	public int armorPenetration = 0;
	public int magicPenetration = 0;
	public int magicRate = 0;
	public int magicCriticalRate;
	public int attackCriticalDamage;
	public int magicCriticalDamage;
	public int bonusHealing;
	public int statusReduction;
	
	public Skill() {
		this(null);
	}

	public Skill(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDodgeRate() {
		return dodgeRate;
	}

	public void setDodgeRate(int dodgeRate) {
		this.dodgeRate = dodgeRate;
	}

	public int getArmorPenetration() {
		return armorPenetration;
	}

	public void setArmorPenetration(int armorPenetration) {
		this.armorPenetration = armorPenetration;
	}

	public int getMagicRate() {
		return magicRate;
	}

	public void setMagicRate(int magicRate) {
		this.magicRate = magicRate;
	}

	public int getMagicCriticalRate() {
		return magicCriticalRate;
	}

	public void setMagicCriticalRate(int magicCriticalRate) {
		this.magicCriticalRate = magicCriticalRate;
	}

	public int getAttackCriticalDamage() {
		return attackCriticalDamage;
	}

	public void setAttackCriticalDamage(int attackCriticalDamage) {
		this.attackCriticalDamage = attackCriticalDamage;
	}

	public int getMagicCriticalDamage() {
		return magicCriticalDamage;
	}

	public void setMagicCriticalDamage(int magicCriticalDamage) {
		this.magicCriticalDamage = magicCriticalDamage;
	}

	public int getBonusHealing() {
		return bonusHealing;
	}

	public void setBonusHealing(int bonusHealing) {
		this.bonusHealing = bonusHealing;
	}

	public int getStatusReduction() {
		return statusReduction;
	}

	public void setStatusReduction(int statusReduction) {
		this.statusReduction = statusReduction;
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", level=" + level + "]";
	}

}
