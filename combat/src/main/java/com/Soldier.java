package com;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.Semaphore;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.gson.annotations.Expose;

public class Soldier implements Runnable, Serializable {

	private static final long serialVersionUID = 1L;
	private static final int LEVEL_FACTOR = 25;
	private static final int LEVEL_CAP = 100;
	private static final int STAR_CAP = 10;
	private static final double VELOCITY_FACTOR = 0.9;
	private static final int HIT_POINT_FACTOR = 19;
	private static final double ATTACK_POWER_FACTOR = 0.4;
	private static final double MAGIC_POWER_FACTOR = 2.4;
	private static final int AGI_ARMOR_FACTOR = 14;
	private static final int STR_ARMOR_FACTOR = 7;
	private static final int MAGIC_RESISTANCE_FACTOR = 10;
	private static final double CRITICAL_HIT_FACTOR = 2.5;
	@Expose private String name;
	@Expose private HeroType heroType = HeroType.NONE;
	@Expose private int level;
	@Expose private int star;
	@Expose private int xp;
	@Expose private int heroPower;
	@Expose private BigDecimal strGrowth;
	@Expose private BigDecimal intGrowth;
	@Expose private BigDecimal agiGrowth;
	@Expose private int initialStr;
	@Expose private int initialInt;
	@Expose private int initialAgi;
	@Expose private int currentStr;
	@Expose private int currentInt;
	@Expose private int currentAgi;
	@Expose private int velocity;
	@Expose private int hitPoint;
	@Expose private int currentDamage;
	@Expose private int attackPower;
	@Expose private int magicPower;
	@Expose private int attackRate;
	@Expose private int magicRate;
	@Expose private int armor;
	@Expose private int magicResistance;
	@Expose private int attackCriticalRate;
	@Expose private int magicCriticalRate;
	@Expose private int attackCriticalDamage;
	@Expose private int magicCriticalDamage;
	@Expose private int bonusHealing;
	@Expose private int statusReduction;
	@Expose private Skill skill_one;
	@Expose private Skill skill_two;
	@Expose private Skill skill_three;
	@Expose private Skill skill_four;
	@Expose private Skill skill_five;
	@Expose private Equipment equip_one;
	@Expose private Equipment equip_two;
	@Expose private Equipment equip_three;
	@Expose private Equipment equip_four;
	@Expose private Equipment equip_five;
	@Expose private Equipment equip_six;
	@Expose private int energy;
	@Expose private int reach;
	@Expose private Position position;
	@Expose private int movementSpeed;

	@JsonIgnore
	private Combat combate;
	private Soldier inimigo;
	@Expose private int dodgeRate;
	@Expose private int armorPenetration;
	@Expose private int magicPenetration;
	
	@JsonIgnore
	private Team enemyTeam;
	
	@JsonIgnore
	private Semaphore semaphore;
	
	public Soldier() {
		super();
	}

	public Soldier(String name, Skill skill_one, Skill skill_two,
			Skill skill_three, Skill skill_four, Skill skill_five) {
		this(name, HeroType.NONE, 1, 1, 1, 1, BigDecimal.valueOf(1), BigDecimal
				.valueOf(1), BigDecimal.valueOf(1), new Position(0, 0), 3,
				skill_one, skill_two, skill_three, skill_four, skill_five);
	}

	public Soldier(String name, HeroType heroType, int star, int initialStr,
			int initialInt, int initialAgi, BigDecimal strGrowth,
			BigDecimal intGrowth, BigDecimal agiGrowth, Position position,
			int reach, Skill skill_one, Skill skill_two, Skill skill_three,
			Skill skill_four, Skill skill_five) {
		this.setName(name);
		this.heroType = heroType;
		this.star = star;
		this.initialStr = initialStr;
		this.initialInt = initialInt;
		this.initialAgi = initialAgi;
		this.strGrowth = strGrowth;
		this.intGrowth = intGrowth;
		this.agiGrowth = agiGrowth;
		this.position = position;
		this.reach = reach;
		this.skill_one = skill_one;
		this.skill_two = skill_two;
		this.skill_three = skill_three;
		this.skill_four = skill_four;
		this.skill_five = skill_five;

		this.level = 1;
		this.xp = 0;
		this.energy = 0;
		this.currentDamage = 0;
		this.movementSpeed = 1;

		updateVariablesAttributes();
	}

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tname: " + getName()
				+ "\n\theroType: " + heroType + "\n\tlevel: " + level
				+ "\n\tstar: " + star + "\n\txp: " + xp + "\n\theroPower: "
				+ heroPower + "\n\tstrGrowth: " + strGrowth + "\n\tintGrowth: "
				+ intGrowth + "\n\tagiGrowth: " + agiGrowth
				+ "\n\tinitialStr: " + initialStr + "\n\tinitialInt: "
				+ initialInt + "\n\tinitialAgi: " + initialAgi
				+ "\n\tcurrentStr: " + currentStr + "\n\tcurrentInt: "
				+ currentInt + "\n\tcurrentAgi: " + currentAgi
				+ "\n\tvelocity: " + velocity + "\n\thitPoint: "
				+ getHitPoint() + "\n\tattackPower: " + attackPower
				+ "\n\tmagicPower: " + magicPower + "\n\tattackRate: "
				+ attackRate + "\n\tmagicRate: " + magicRate + "\n\tarmor: "
				+ getArmor() + "\n\tmagicResistance: " + magicResistance
				+ "\n\tattackCriticalRate: " + attackCriticalRate
				+ "\n\tmagicCriticalRate: " + magicCriticalRate
				+ "\n\tattackCriticalDamage: " + attackCriticalDamage
				+ "\n\tmagicCriticalDamage: " + magicCriticalDamage
				+ "\n\tbonusHealing: " + bonusHealing + "\n\tstatusReduction: "
				+ statusReduction + "\n\tskill_one: " + skill_one
				+ "\n\tskill_two: " + skill_two + "\n\tskill_three: "
				+ skill_three + "\n\tskill_four: " + skill_four
				+ "\n\tskill_five: " + skill_five + "\n\tequip_one: "
				+ equip_one + "\n\tequip_two: " + equip_two
				+ "\n\tequip_three: " + equip_three + "\n\tequip_four: "
				+ equip_four + "\n\tequip_five: " + equip_five
				+ "\n\tequip_six: " + equip_six + "\n\tenergy: " + energy
				+ "\n\tcombate: " + combate + "\n\tinimigo: " + inimigo
				+ "\n\tdodgeRate: " + getDodgeRate() + "\n\tarmorPenetration: "
				+ armorPenetration + "\n\tmagicPenetration: "
				+ magicPenetration + "\n}";
	}

	public void updateVariablesAttributes() {
		this.currentStr = defineCurrentStr();
		this.currentInt = defineCurrentInt();
		this.currentAgi = defineCurrentAgi();
		this.velocity = defineVelocity();
		this.setHitPoint(defineHitPoint());
		this.attackPower = defineAttackPower();
		this.magicPower = defineMagicPower();
		this.attackRate = defineAttackRate();
		this.magicRate = defineMagicRate();
		this.setArmor(defineArmor());
		this.magicResistance = defineMagicResistance();
		this.armorPenetration = defineArmorPenetration();
		this.magicPenetration = defineMagicPenetration();
		this.attackCriticalRate = defineAttackCriticalRate();
		this.magicCriticalRate = defineMagicCriticalRate();
		this.attackCriticalDamage = defineAttackCriticalDamage();
		this.magicCriticalDamage = defineMagicCriticalDamage();
		this.bonusHealing = defineBonusHealing();
		this.statusReduction = defineStatusReduction();
		this.heroPower = defineHeroPower();
		this.setDodgeRate(defineDodgeRate());
	}

	private int defineCurrentHitPoint() {
		return 0;
	}

	private int defineMagicPenetration() {
		int equipBonus = equipmentBonus(BonusType.MAGICPENETRATION);
		int skillBonus = skillBonus(BonusType.MAGICPENETRATION);
		return equipBonus + skillBonus;
	}

	private int equipmentBonus(BonusType bonusType) {
		return equip_one == null ? 0
				: equip_one.getBonus(bonusType)
						+ (equip_two == null ? 0
								: equip_two.getBonus(bonusType)
										+ (equip_three == null ? 0
												: equip_three
														.getBonus(bonusType)
														+ (equip_four == null ? 0
																: equip_four
																		.getBonus(bonusType)
																		+ (equip_five == null ? 0
																				: equip_five
																						.getBonus(bonusType)
																						+ (equip_six == null ? 0
																								: equip_six
																										.getBonus(bonusType))))));
	}

	private int defineArmorPenetration() {
		int equipBonus = equipmentBonus(BonusType.ARMORPENETRATION);
		int skillBonus = skillBonus(BonusType.ARMORPENETRATION);
		return equipBonus + skillBonus;
	}

	private int defineDodgeRate() {
		int equipBonus = equipmentBonus(BonusType.DODGERATE);
		int skillBonus = skillBonus(BonusType.DODGERATE);
		return this.currentAgi + equipBonus + skillBonus;
	}

	private void gainStar() {
		if (this.star < STAR_CAP) {
			this.star++;
			this.updateVariablesAttributes();
		}
	}

	private void levelUp() {
		if (this.level < LEVEL_CAP) {
			this.level++;
			this.updateVariablesAttributes();
		}
	}

	public void gainXP(int xp) {
		int xpTotal = this.xp + xp;
		this.xp = 0;
		if (xpTotal >= necessaryXp(this.level)) {
			if (this.level < LEVEL_CAP) {
				xpTotal -= necessaryXp(this.level);
				this.levelUp();
//				System.out.println("passou para o nível " + this.level
//						+ " e sobrou " + xpTotal + " xp.");
				gainXP(xpTotal);
			} else {
				this.xp = necessaryXp(this.level) - 1;
//				System.out.println("Chegou ao nível máximo com " + this.xp
//						+ " xp.");
			}
		} else {
			this.xp = xpTotal;
//			System.out.println("ficou no nível " + this.level + " com "
//					+ this.xp + " xp.");
		}
	}

	public int baseXp(int level) {
		if (level > 1) {
			return (level * LEVEL_FACTOR + baseXp(level - 1));
		} else {
			return LEVEL_FACTOR;
		}
	}

	public int necessaryXp(int level) {
		if (level > 1) {
			return baseXp(level)
					- (level * LEVEL_FACTOR + necessaryXp(level - 1));
		} else {
			return 0;
		}
	}

	private int defineCurrentStr() {
		return this.initialStr
				+ (this.star * this.strGrowth.intValue() * this.level)
				+ equipmentBonus(BonusType.STRENGTH)
				+ skillBonus(BonusType.STRENGTH);
	}

	private int defineCurrentInt() {
		return this.initialInt
				+ (this.star * this.intGrowth.intValue() * this.level)
				+ equipmentBonus(BonusType.INTELIGENCE)
				+ skillBonus(BonusType.INTELIGENCE);
	}

	private int defineCurrentAgi() {
		return this.initialAgi
				+ (this.star * this.agiGrowth.intValue() * this.level)
				+ equipmentBonus(BonusType.AGILITY)
				+ skillBonus(BonusType.AGILITY);
	}

	private int defineStatusReduction() {
		int skillBonus = skillBonus(BonusType.STATUSREDUCTION);
		int equipBonus = equipmentBonus(BonusType.STATUSREDUCTION);
		return equipBonus + skillBonus;
	}

	private int defineBonusHealing() {
		int skillBonus = skillBonus(BonusType.BONUSHEALING);
		int equipBonus = equipmentBonus(BonusType.BONUSHEALING);
		return equipBonus + skillBonus;
	}

	private int defineHeroPower() {
		return this.currentAgi + this.currentInt + this.currentStr
				+ this.getArmor() + this.attackPower
				+ this.attackCriticalDamage + this.attackCriticalRate
				+ this.attackRate + this.bonusHealing + this.getHitPoint()
				+ this.magicCriticalDamage + this.magicCriticalRate
				* this.magicPower + this.magicRate + this.magicResistance
				+ this.velocity + this.statusReduction;
	}

	private int defineMagicCriticalDamage() {
		int skillBonus = skillBonus(BonusType.MAGICCRITICALDAMAGE);
		int equipBonus = equipmentBonus(BonusType.MAGICCRITICALDAMAGE);
		return equipBonus + skillBonus;
	}

	private int defineAttackCriticalDamage() {
		int skillBonus = skillBonus(BonusType.ATTACKCRITICALDAMAGE);
		int equipBonus = equipmentBonus(BonusType.ATTACKCRITICALDAMAGE);
		return equipBonus + skillBonus;
	}

	private int defineMagicCriticalRate() {
		int skillBonus = skillBonus(BonusType.MAGICCRITICALRATE);
		int equipBonus = equipmentBonus(BonusType.MAGICCRITICALRATE);
		return equipBonus + skillBonus;
	}

	private int defineAttackCriticalRate() {
		Double acr = (double) this.currentAgi / CRITICAL_HIT_FACTOR;
		int equipBonus = equipmentBonus(BonusType.ATTACKCRITICALRATE);
		int skillBonus = skillBonus(BonusType.ATTACKCRITICALRATE);
		return acr.intValue() + equipBonus + skillBonus;
	}

	private int defineMagicResistance() {
		Double mr = (double) this.currentInt / MAGIC_RESISTANCE_FACTOR;
		int equipBonus = equipmentBonus(BonusType.MAGICRESISTANCE);
		int skillBonus = skillBonus(BonusType.MAGICRESISTANCE);
		return mr.intValue() + equipBonus + skillBonus;
	}

	private int defineArmor() {
		Double armor = ((double) this.currentStr) / STR_ARMOR_FACTOR
				+ ((double) this.currentAgi) / AGI_ARMOR_FACTOR;
		int equipBonus = equipmentBonus(BonusType.ARMOR);
		int skillBonus = skillBonus(BonusType.ARMOR);
		return armor.intValue() + equipBonus + skillBonus;
	}

	private int defineMagicRate() {
		int skillBonus = skillBonus(BonusType.MAGICRATE);
		int equipBonus = equipmentBonus(BonusType.MAGICRATE);
		return equipBonus + skillBonus;
	}

	private int defineAttackRate() {
		int skillBonus = skillBonus(BonusType.ATTACKRATE);
		int equipBonus = equipmentBonus(BonusType.ATTACKRATE);
		return this.currentAgi + equipBonus + skillBonus;
	}

	private int skillBonus(BonusType skillBonus) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int defineMagicPower() {
		Double magicPower = this.currentInt * MAGIC_POWER_FACTOR;
		int equipBonus = equipmentBonus(BonusType.MAGICPOWER);
		int skillBonus = skillBonus(BonusType.MAGICPOWER);
		return magicPower.intValue() + equipBonus + skillBonus;
	}

	private int defineAttackPower() {
		int max = Math.max(Math.max(this.currentStr, this.currentAgi),
				this.currentInt);
		Double attackPower = (max + this.currentAgi) * ATTACK_POWER_FACTOR;
		int equipBonus = equipmentBonus(BonusType.ATTACKPOWER);
		int skillBonus = skillBonus(BonusType.ATTACKPOWER);
		return attackPower.intValue() + equipBonus + skillBonus;
	}

	private int defineHitPoint() {
		int equipBonus = equipmentBonus(BonusType.HITPOINT);
		int skillBonus = skillBonus(BonusType.HITPOINT);
		return this.currentStr * HIT_POINT_FACTOR + equipBonus + skillBonus;
	}

	private int defineVelocity() {
		Double velocity = this.currentAgi * VELOCITY_FACTOR;
		int equipBonus = equipmentBonus(BonusType.VELOCITY);
		int skillBonus = skillBonus(BonusType.VELOCITY);
		return velocity.intValue() + equipBonus + skillBonus;
	}

	@Override
	public void run() {
		synchronized (semaphore) {
			try {
					System.out.println(this.getName() + " parou!");
				semaphore.wait();
					System.out.println(this.getName() + " continuou!");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		do {
			this.inimigo = this.defineTarget(getEnemyTeam());
			if(inimigo == null) {
				System.out.println("Não encontrou inimigo");
				break;
			}
			if (this.distance(this.inimigo.position) > this.reach) {
				this.move(this.inimigo.position);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				if (!getCombate().acabou()) {
					this.ataca(inimigo);
				}
				try {
					Thread.sleep(3000 - this.currentAgi);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (!getCombate().acabou());
	}
	
	private Soldier defineTarget(Team enemies) {
		if(enemies == null) {
			return null;
		}
		return enemies.near(this.position);
	}

	public double distance(Position enemyPosition) {
		return Math.ceil((double) (Math.sqrt(Math.pow(this.position.getX()
				- enemyPosition.getX(), 2)
				+ Math.pow(this.position.getY() - enemyPosition.getY(), 2))));
	}

	public void move(Position enemyPosition) {
		if (Grid.AXIS_FACTOR.multiply(
				BigDecimal.valueOf(this.position.xDifference(enemyPosition)))
				.doubleValue() > this.position.yDifference(enemyPosition)) {
			if (this.position.leftSide(enemyPosition)) {
				this.position.setX(this.position.getX() + movementSpeed);
			} else {
				this.position.setX(this.position.getX() - movementSpeed);
			}
		} else {
			if (this.position.downSide(enemyPosition)) {
				this.position.setY(this.position.getY() + movementSpeed);
			} else {
				this.position.setY(this.position.getY() - movementSpeed);
			}
		}
	}

	public void ataca(Soldier inimigo) {
		if (inimigo.dead()) {
			return;
		}

		Attack ataque = new Attack(this.attackRate, this.attackPower,
				this.attackCriticalRate, this.attackCriticalDamage,
				this.armorPenetration);
		System.out.print(this.getName());
		if (ataque.acerta(inimigo)) {
			ataque.causaDano(inimigo);

		} else {
			System.out.println(" errou ataque em " + inimigo.getName());
		}
	}

	public boolean dead() {
		if (this.getCurrentDamage() >= this.getHitPoint()) {
			return true;
		}
		return false;
	}

	public Combat getCombate() {
		return combate;
	}

	public void setCombate(Combat combate) {
		this.combate = combate;
	}

	public void setInimigo(Soldier inimigo) {
		this.inimigo = inimigo;
	}

	public int getHitPoint() {
		return hitPoint;
	}

	public void setHitPoint(int hitPoint) {
		this.hitPoint = hitPoint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getDodgeRate() {
		return dodgeRate;
	}

	public void setDodgeRate(int dodgeRate) {
		this.dodgeRate = dodgeRate;
	}

	public int getCurrentDamage() {
		return currentDamage;
	}

	public void setCurrentDamage(int currentDamage) {
		this.currentDamage = currentDamage;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Team getEnemyTeam() {
		return enemyTeam;
	}

	public void setEnemyTeam(Team enemyTeam) {
		this.enemyTeam = enemyTeam;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public HeroType getHeroType() {
		return heroType;
	}

	public void setHeroType(HeroType heroType) {
		this.heroType = heroType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getHeroPower() {
		return heroPower;
	}

	public void setHeroPower(int heroPower) {
		this.heroPower = heroPower;
	}

	public BigDecimal getStrGrowth() {
		return strGrowth;
	}

	public void setStrGrowth(BigDecimal strGrowth) {
		this.strGrowth = strGrowth;
	}

	public BigDecimal getIntGrowth() {
		return intGrowth;
	}

	public void setIntGrowth(BigDecimal intGrowth) {
		this.intGrowth = intGrowth;
	}

	public BigDecimal getAgiGrowth() {
		return agiGrowth;
	}

	public void setAgiGrowth(BigDecimal agiGrowth) {
		this.agiGrowth = agiGrowth;
	}

	public int getInitialStr() {
		return initialStr;
	}

	public void setInitialStr(int initialStr) {
		this.initialStr = initialStr;
	}

	public int getInitialInt() {
		return initialInt;
	}

	public void setInitialInt(int initialInt) {
		this.initialInt = initialInt;
	}

	public int getInitialAgi() {
		return initialAgi;
	}

	public void setInitialAgi(int initialAgi) {
		this.initialAgi = initialAgi;
	}

	public int getCurrentStr() {
		return currentStr;
	}

	public void setCurrentStr(int currentStr) {
		this.currentStr = currentStr;
	}

	public int getCurrentInt() {
		return currentInt;
	}

	public void setCurrentInt(int currentInt) {
		this.currentInt = currentInt;
	}

	public int getCurrentAgi() {
		return currentAgi;
	}

	public void setCurrentAgi(int currentAgi) {
		this.currentAgi = currentAgi;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getMagicPower() {
		return magicPower;
	}

	public void setMagicPower(int magicPower) {
		this.magicPower = magicPower;
	}

	public int getAttackRate() {
		return attackRate;
	}

	public void setAttackRate(int attackRate) {
		this.attackRate = attackRate;
	}

	public int getMagicRate() {
		return magicRate;
	}

	public void setMagicRate(int magicRate) {
		this.magicRate = magicRate;
	}

	public int getMagicResistance() {
		return magicResistance;
	}

	public void setMagicResistance(int magicResistance) {
		this.magicResistance = magicResistance;
	}

	public int getAttackCriticalRate() {
		return attackCriticalRate;
	}

	public void setAttackCriticalRate(int attackCriticalRate) {
		this.attackCriticalRate = attackCriticalRate;
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

	public Skill getSkill_one() {
		return skill_one;
	}

	public void setSkill_one(Skill skill_one) {
		this.skill_one = skill_one;
	}

	public Skill getSkill_two() {
		return skill_two;
	}

	public void setSkill_two(Skill skill_two) {
		this.skill_two = skill_two;
	}

	public Skill getSkill_three() {
		return skill_three;
	}

	public void setSkill_three(Skill skill_three) {
		this.skill_three = skill_three;
	}

	public Skill getSkill_four() {
		return skill_four;
	}

	public void setSkill_four(Skill skill_four) {
		this.skill_four = skill_four;
	}

	public Skill getSkill_five() {
		return skill_five;
	}

	public void setSkill_five(Skill skill_five) {
		this.skill_five = skill_five;
	}

	public Equipment getEquip_one() {
		return equip_one;
	}

	public void setEquip_one(Equipment equip_one) {
		this.equip_one = equip_one;
	}

	public Equipment getEquip_two() {
		return equip_two;
	}

	public void setEquip_two(Equipment equip_two) {
		this.equip_two = equip_two;
	}

	public Equipment getEquip_three() {
		return equip_three;
	}

	public void setEquip_three(Equipment equip_three) {
		this.equip_three = equip_three;
	}

	public Equipment getEquip_four() {
		return equip_four;
	}

	public void setEquip_four(Equipment equip_four) {
		this.equip_four = equip_four;
	}

	public Equipment getEquip_five() {
		return equip_five;
	}

	public void setEquip_five(Equipment equip_five) {
		this.equip_five = equip_five;
	}

	public Equipment getEquip_six() {
		return equip_six;
	}

	public void setEquip_six(Equipment equip_six) {
		this.equip_six = equip_six;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public int getArmorPenetration() {
		return armorPenetration;
	}

	public void setArmorPenetration(int armorPenetration) {
		this.armorPenetration = armorPenetration;
	}

	public int getMagicPenetration() {
		return magicPenetration;
	}

	public void setMagicPenetration(int magicPenetration) {
		this.magicPenetration = magicPenetration;
	}

	public Soldier getInimigo() {
		return inimigo;
	}

}
