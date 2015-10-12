package com;

public enum HeroType {
	NONE(1, "None"),
	DWARF(2, "Dwarf"),
	NAGA(3, "Naga"),
	Elf(4, "Elf"),
	UNDEAD(5, "Undead");
	
	public int id;
	public String description;

	HeroType(int id, String description) {
		this.id = id;
		this.description = description;
	}
}
