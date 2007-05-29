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
public class PopulationCenter implements IBelongsToNation, IHasMapLocation, Serializable {
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

        //TODO BUG BUG
        //this is a bug, info source should be cloned too!
        newPc.setInfoSource(getInfoSource());
        newPc.setInformationSource(getInformationSource());

        for (ProductEnum p : ProductEnum.values()) {
            newPc.setProduction(p, getProduction(p));
            newPc.setStores(p, getStores(p));
        }
        return newPc;
    }
}
