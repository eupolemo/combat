package com;

public enum HeroClass {
	WHITE_KNIGHT(1, "White Knight", "cavaleirobrancotank.json"),
	BLACK_NIGHT(2, "Black Knight", "cavaleironegrotank.json"),
	NINJA_ASSASSIN(3, "Ninja Assassin", "ninjaassassinodps.json");
	
	private int id;
	private String description;
	private String jsonFile;

	private HeroClass(int id, String description, String jsonFile) {
		this.id = id;
		this.description = description;
		this.jsonFile = jsonFile;
	}

	public String getJsonFile() {
		return jsonFile;
	}
}
