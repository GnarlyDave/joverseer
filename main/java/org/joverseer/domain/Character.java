package org.joverseer.domain;

import java.io.Serializable;
import java.util.ArrayList;

import org.joverseer.domain.structuredOrderResults.IStructuredOrderResult;
import org.joverseer.metadata.domain.Nation;
import org.joverseer.support.AsciiUtils;
import org.joverseer.support.NationMap;
import org.joverseer.support.infoSources.InfoSource;

/**
 * Stores information about a character reported in the turn results.
 * 
 * @author Marios Skounakis
 * 
 */
public class Character implements IBelongsToNation, IHasMapLocation, IMaintenanceCost, IEngineObject, Serializable {

	private static final long serialVersionUID = 2372359979734224557L;
	String id;
	String name;
	String title;

	Integer nationNo;

	int command;
	int commandTotal;
	int agent;
	int agentTotal;
	int emmisary;
	int emmisaryTotal;
	int mage;
	int mageTotal;
	int stealth;
	int stealthTotal;
	int challenge;
	Integer health;
	InfoSourceValue healthEstimate;
	ArrayList<IStructuredOrderResult> structuredOrderResults = new ArrayList<IStructuredOrderResult>();

	int x;
	int y;

	Boolean isHostage = null;

	ArrayList<Integer> artifacts = new ArrayList<Integer>();
	ArrayList<SpellProficiency> spells = new ArrayList<SpellProficiency>();
	ArrayList<String> hostages = new ArrayList<String>();
	int artifactInUse;

	InformationSourceEnum informationSource;
	InfoSource infoSource;

	Order[] orders = new Order[] { new Order(this), new Order(this), new Order(this) };

	int numberOfOrders = 2;

	String orderResults;
	String encounter;
	boolean startInfoDummy = false;

	CharacterDeathReasonEnum deathReason = CharacterDeathReasonEnum.NotDead;

	boolean refusingChallenges = false; // engine
	boolean inChallengeFight = false;

	public int getAgent() {
		return agent;
	}

	public void setAgent(int agent) {
		this.agent = agent;
	}

	public int getAgentTotal() {
		return agentTotal;
	}

	public void setAgentTotal(int agentTotal) {
		this.agentTotal = agentTotal;
	}

	public ArrayList<Integer> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(ArrayList<Integer> artifacts) {
		this.artifacts = artifacts;
	}

	public int getChallenge() {
		return challenge;
	}

	public void setChallenge(int challenge) {
		this.challenge = challenge;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getCommandTotal() {
		return commandTotal;
	}

	public void setCommandTotal(int commandTotal) {
		this.commandTotal = commandTotal;
	}

	public int getEmmisary() {
		return emmisary;
	}

	public void setEmmisary(int emmisary) {
		this.emmisary = emmisary;
	}

	public int getEmmisaryTotal() {
		return emmisaryTotal;
	}

	public void setEmmisaryTotal(int emmisaryTotal) {
		this.emmisaryTotal = emmisaryTotal;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public InfoSourceValue getHealthEstimate() {
		return healthEstimate;
	}

	public void setHealthEstimate(InfoSourceValue healthEstimate) {
		this.healthEstimate = healthEstimate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InfoSource getInfoSource() {
		return infoSource;
	}

	public void setInfoSource(InfoSource infoSource) {
		this.infoSource = infoSource;
	}

	public int getMage() {
		return mage;
	}

	public void setMage(int mage) {
		this.mage = mage;
	}

	public int getMageTotal() {
		return mageTotal;
	}

	public void setMageTotal(int mageTotal) {
		this.mageTotal = mageTotal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<SpellProficiency> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<SpellProficiency> spells) {
		this.spells = spells;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		this.stealth = stealth;
	}

	public int getStealthTotal() {
		return stealthTotal;
	}

	public void setStealthTotal(int stealthTotal) {
		this.stealthTotal = stealthTotal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public InformationSourceEnum getInformationSource() {
		return informationSource;
	}

	public void setInformationSource(InformationSourceEnum informationSource) {
		this.informationSource = informationSource;
	}

	public Integer getNationNo() {
		return nationNo;
	}

	public void setNationNo(Integer nationNo) {
		this.nationNo = nationNo;
	}

	public int getHexNo() {
		return getX() * 100 + getY();
	}

	public void setHexNo(int hexNo) {
		setX(hexNo / 100);
		setY(hexNo % 100);
	}

	public Order[] getOrders() {
		if (orders.length == 2) {
			orders = new Order[] { orders[0], orders[1], new Order(this) };
		}
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	public static String getIdFromName(String name) {
		String id = name.toLowerCase().substring(0, Math.min(5, name.length()));
		return AsciiUtils.convertNonAscii(id);
	}

	public static String getSpacePaddedIdFromId(String id) {
		return id + "     ".substring(0, 5 - id.length());
	}

	public String getEncounter() {
		return encounter;
	}

	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}

	public boolean hasOrderResults() {
		return getOrderResults() != null;
	}

	public String getOrderResults() {
		return orderResults;
	}

	public String getCleanOrderResults() {
		if (getOrderResults() == null)
			return "";
		return getOrderResults().replace("\r\n", " ").replace("\n", " ").replace("  ", " ");
	}

	public void setOrderResults(String orderResults) {
		this.orderResults = orderResults;
	}

	public CharacterDeathReasonEnum getDeathReason() {
		return deathReason;
	}

	public void setDeathReason(CharacterDeathReasonEnum deathReason) {
		this.deathReason = deathReason;
	}

	public static void main(String[] args) {
		System.out.println(Character.getIdFromName("Michèle"));
	}

	public Boolean getHostage() {
		return isHostage;
	}

	public void setHostage(Boolean isHostage) {
		this.isHostage = isHostage;
	}

	public int getArtifactInUse() {
		return artifactInUse;
	}

	public void setArtifactInUse(int artifactInUse) {
		this.artifactInUse = artifactInUse;
	}

	public Nation getNation() {
		return NationMap.getNationFromNo(getNationNo());
	}

	public void setNation(Nation nation) {
		if (nation == null) {
			setNationNo(0);
		} else {
			setNationNo(nation.getNumber());
		}
	}

	public ArrayList<IStructuredOrderResult> getStructuredOrderResults() {
		if (structuredOrderResults == null) {
			structuredOrderResults = new ArrayList<IStructuredOrderResult>();
		}
		return structuredOrderResults;
	}

	public ArrayList<String> getHostages() {
		if (hostages == null) {
			hostages = new ArrayList<String>();
		}
		return hostages;
	}

	public void setHostages(ArrayList<String> hostages) {
		this.hostages = hostages;
	}

	public int getNumberOfOrders() {
		if (numberOfOrders == 0)
			numberOfOrders = 2;
		return numberOfOrders;
	}

	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	public boolean isStartInfoDummy() {
		return startInfoDummy;
	}

	public void setStartInfoDummy(boolean startInfoDummy) {
		this.startInfoDummy = startInfoDummy;
	}

	public Integer getMaintenance() {
		return (getCommand() + getMage() + getAgent() + getEmmisary()) * 20;
	}

	@Override
	public Character clone() {
		Character c = new Character();
		c.setName(getName());
		c.setId(getId());
		c.setNationNo(getNationNo());
		c.setCommand(getCommand());
		c.setCommandTotal(getCommandTotal());
		c.setAgent(getAgent());
		c.setAgentTotal(getAgentTotal());
		c.setMage(getMage());
		c.setMageTotal(getMageTotal());
		c.setEmmisary(getEmmisary());
		c.setEmmisaryTotal(getEmmisaryTotal());
		c.setHealth(getHealth());
		c.setChallenge(getChallenge());
		c.setTitle(getTitle());
		c.setHexNo(getHexNo());
		c.setDeathReason(getDeathReason());
		c.setNumberOfOrders(getNumberOfOrders());
		c.setArtifactInUse(getArtifactInUse());
		c.setStealth(getStealth());
		c.setStealthTotal(getStealthTotal());
		c.setInfoSource(getInfoSource());
		c.setInformationSource(getInformationSource());
		c.setStartInfoDummy(isStartInfoDummy());
		c.setArtifacts((ArrayList<Integer>) getArtifacts().clone());
		for (SpellProficiency sp : getSpells()) {
			c.getSpells().add(new SpellProficiency(sp.getSpellId(), sp.getProficiency(), sp.getName()));
		}
		return c;
	}

	public void initialize() {
		refusingChallenges = false;
	}

	public boolean isRefusingChallenges() {
		return refusingChallenges;
	}

	public void setRefusingChallenges(boolean refusingChallenges) {
		this.refusingChallenges = refusingChallenges;
	}

	public boolean isInChallengeFight() {
		return inChallengeFight;
	}

	public void setInChallengeFight(boolean inChallengeFight) {
		this.inChallengeFight = inChallengeFight;
	}

	public String getStatString() {
		Character c = this;
		String txt = "";
		txt += getStatText("C", c.getCommand(), c.getCommandTotal());
		txt += getStatText("A", c.getAgent(), c.getAgentTotal());
		txt += getStatText("E", c.getEmmisary(), c.getEmmisaryTotal());
		txt += getStatText("M", c.getMage(), c.getMageTotal());
		txt += getStatText("S", c.getStealth(), c.getStealthTotal());
		txt += getStatText("Cr", c.getChallenge(), c.getChallenge());
		if (c.getHealth() != null) {
			txt += " H" + c.getHealth();
		}
		if (c.getDeathReason() != null && c.getDeathReason() != CharacterDeathReasonEnum.NotDead) {
			txt += " (" + c.getDeathReason().toString() + ")";
		}

		return txt;
	}

	private String getStatText(String prefix, int skill, int skillTotal) {
		if (skillTotal == 0 && skill == 0)
			return "";
		return prefix + skill + (skillTotal > skill ? "(" + skillTotal + ")" : "") + " ";
	}

	public boolean isDead() {
		return getDeathReason() != null && !getDeathReason().equals(CharacterDeathReasonEnum.NotDead);
	}

	public void addHostage(String name) {
		if (!getHostages().contains(name))
			getHostages().add(name);
	}

}
