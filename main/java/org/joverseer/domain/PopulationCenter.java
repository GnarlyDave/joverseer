package org.joverseer.domain;

import org.joverseer.support.NationMap;
import org.joverseer.support.ProductContainer;
import org.joverseer.support.infoSources.InfoSource;
import org.joverseer.metadata.domain.Nation;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores information about a population center from the pdf turns
 * 
 * @author Marios Skounakis
 */
public class PopulationCenter implements IBelongsToNation, IHasMapLocation, IMaintenanceCost, IEngineObject, Serializable {

    private static final long serialVersionUID = 5077983571531270227L;
    String name;
    int x;
    int y;

    PopulationCenterSizeEnum size;
    FortificationSizeEnum fortification;
    HarborSizeEnum harbor;

    Integer nationNo;

    boolean capital;
    boolean hidden;

    int loyalty;
    InfoSourceValue loyaltyEstimate;

    InformationSourceEnum informationSource;

    InfoSource infoSource;

    ProductContainer production = new ProductContainer();
    ProductContainer stores = new ProductContainer();

    boolean lostThisTurn = false;
    
    int recruits; //used in Engine
    int foodCapacity; // used in Engine
    boolean improvedThisTurn = false; // used in Engine
    boolean sieged = false; // used in Engine
    
    public PopulationCenter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PopulationCenterSizeEnum getSize() {
        return size;
    }

    public void setSize(PopulationCenterSizeEnum size) {
        this.size = size;
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

    public FortificationSizeEnum getFortification() {
        return fortification;
    }

    public void setFortification(FortificationSizeEnum fortification) {
        this.fortification = fortification;
    }

    public Nation getNation() {
        return NationMap.getNationFromNo(getNationNo());
    }

    public void setNation(Nation nation) {
        setNationNo(nation.getNumber());
    }

    public Integer getNationNo() {
        return nationNo;
    }

    public void setNationNo(Integer nationNo) {
        this.nationNo = nationNo;
    }

    public HarborSizeEnum getHarbor() {
        return harbor;
    }

    public void setHarbor(HarborSizeEnum harbor) {
        this.harbor = harbor;
    }

    public boolean getCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public InformationSourceEnum getInformationSource() {
        return informationSource;
    }

    public void setInformationSource(InformationSourceEnum informationSource) {
        this.informationSource = informationSource;
    }

    public InfoSource getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(InfoSource infoSource) {
        this.infoSource = infoSource;
    }

    public int getHexNo() {
        return getX() * 100 + getY();
    }

    public void setHexNo(int hexNo) {
        setX(hexNo / 100);
        setY(hexNo % 100);
    }

    public Integer getProduction(ProductEnum p) {
        return production.getProduct(p);
    }

    public Integer getStores(ProductEnum p) {
        return stores.getProduct(p);
    }

    public void setProduction(ProductEnum p, Integer amount) {
        production.setProduct(p, amount);
    }

    public void setStores(ProductEnum p, Integer amount) {
        stores.setProduct(p, amount);
    }


    public boolean getLostThisTurn() {
        return lostThisTurn;
    }


    public void setLostThisTurn(boolean lostThisTurn) {
        this.lostThisTurn = lostThisTurn;
    }


    public InfoSourceValue getLoyaltyEstimate() {
        return loyaltyEstimate;
    }

    public void setLoyaltyEstimate(InfoSourceValue loyaltyEstimate) {
        this.loyaltyEstimate = loyaltyEstimate;
    }

    public PopulationCenter clone() {
        PopulationCenter newPc = new PopulationCenter();
        newPc.setName(getName());
        newPc.setCapital(getCapital());
        newPc.setFortification(getFortification());
        newPc.setHarbor(getHarbor());
        newPc.setHidden(getHidden());
        newPc.setNationNo(getNationNo());
        newPc.setLoyalty(getLoyalty());
        newPc.setSize(getSize());
        newPc.setX(getX());
        newPc.setY(getY());

        // TODO BUG BUG
        // this is a bug, info source should be cloned too!
        newPc.setInfoSource(getInfoSource());
        newPc.setInformationSource(getInformationSource());

        for (ProductEnum p : ProductEnum.values()) {
            newPc.setProduction(p, getProduction(p));
            newPc.setStores(p, getStores(p));
        }
        return newPc;
    }

	public Integer getMaintenance() {
		if (getSize().equals(PopulationCenterSizeEnum.ruins)) return 0;
		int cost = 0;
		if (getHarbor().equals(HarborSizeEnum.harbor)) {
			cost += 250;
		} else if (getHarbor().equals(HarborSizeEnum.port)) {
			cost += 500;
		} 
		cost += getFortification().getSize() * 500;
		return cost;
	}

	public int getRecruits() {
		return recruits;
	}

	public void setRecruits(int recruits) {
		this.recruits = recruits;
	}

	public int getFoodCapacity() {
		return foodCapacity;
	}

	public void setFoodCapacity(int foodCapacity) {
		this.foodCapacity = foodCapacity;
	}

	public boolean isImprovedThisTurn() {
		return improvedThisTurn;
	}

	public void setImprovedThisTurn(boolean improvedThisTurn) {
		this.improvedThisTurn = improvedThisTurn;
	}
	
	


	public boolean isSieged() {
		return sieged;
	}

	public void setSieged(boolean sieged) {
		this.sieged = sieged;
	}

	public void initialize() {
		int recruits = getSize().getCode() * 100;
		setRecruits(recruits);
		int foodCapacity = 0; 
		if (getSize().equals(PopulationCenterSizeEnum.camp)) {
			foodCapacity = 100;
		} else if (getSize().equals(PopulationCenterSizeEnum.village)) {
			foodCapacity = 200;
		} else if (getSize().equals(PopulationCenterSizeEnum.town)) {
			foodCapacity = 1000;
		} else if (getSize().equals(PopulationCenterSizeEnum.majorTown)) {
			foodCapacity = 2500;
		} else if (getSize().equals(PopulationCenterSizeEnum.city)) {
			foodCapacity = 5000;
		}  
		setFoodCapacity(foodCapacity);
		setImprovedThisTurn(false); // TODO move to dif method
		setSieged(false);
	}
    
}
