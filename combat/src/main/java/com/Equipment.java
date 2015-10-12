package com;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<BonusType, Integer> equipmentBonus;
	private String name;

	public Equipment() {
		this(null);
	}
	
	public Equipment(String name) {
		this.setName(name);
		equipmentBonus = new HashMap<BonusType, Integer>();
	}
	
	public void setBonus(BonusType bonusType, Integer value) {
		equipmentBonus.put(bonusType, value);
	}
	
	public Integer getBonus(BonusType bonusType) {
		if(equipmentBonus.containsKey(bonusType)) {
			return equipmentBonus.get(bonusType);
		}
		return 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int levelRequirement;
	public int dodgeRate;
	public int armorPenetration;
	public int magicPenetration;
	public int magicRate;
	public int magicCriticalRate;
	public int attackCriticalDamage;
	public int magicCriticalDamage;
	public int bonusHealing;
	public int statusReduction;
	public int currentStr;
	public int currentInt;
	public int currentAgi;
}
