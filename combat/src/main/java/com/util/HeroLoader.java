package com.util;

import java.io.IOException;

import com.HeroClass;
import com.Soldier;

public class HeroLoader {
	private JsonReader jsonReader;
	
	public HeroLoader() {
		jsonReader = new JsonReader();
	}
	
	public Soldier loadHero(HeroClass heroClass) {
		return loadHero(heroClass, 0);
	}

	public Soldier loadHero(HeroClass heroClass, int xp) {
		String json = null;
		try {
			json = jsonReader.loadJson(heroClass.getJsonFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Soldier soldier = (Soldier)jsonReader.serialize(json, Soldier.class);
		soldier.gainXP(xp);
		soldier.updateVariablesAttributes();
		return soldier;
	}
}
