package com;

public enum BonusType {
	STRENGTH(1, "Strength"),
	INTELIGENCE(2, "Inteligence"),
	AGILITY(3, "Agility"),
	VELOCITY(4, "Velocity"),
	ATTACKPOWER(5, "Attack Power"),
	MAGICPOWER(6, "Magic Power"),
	ATTACKRATE(7, "Attack Rate"),
	MAGICRATE(8, "Magic Rate"),
	ARMOR(9, "Armor"),
	MAGICRESISTANCE(10, "Magic Resistance"),
	ATTACKCRITICALRATE(11, "Attack Critical Rate"),
	MAGICCRITICALRATE(12, "Magic Critical Rate"),
	ATTACKCRITICALDAMAGE(13, "Attack Critical Damage"),
	MAGICCRITICALDAMAGE(14, "Magic Critical Damage"),
	BONUSHEALING(15, "Bonus Healing"),
	STATUSREDUCTION(16, "Status Reduction"),
	DODGERATE(17, "Dodge Rate"),
	ARMORPENETRATION(18, "Armor Penetration"),
	MAGICPENETRATION(19, "Magic Penetration"),
	HITPOINT(20, "Hitpoint");
	
	
	private int id;
	private String description;

	BonusType(int id, String description) {
		this.id = id;
		this.description = description;
	}
}
