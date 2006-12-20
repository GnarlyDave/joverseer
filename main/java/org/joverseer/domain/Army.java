package org.joverseer.domain;

import org.joverseer.support.infoSources.InfoSource;
import org.joverseer.metadata.domain.NationAllegianceEnum;

import java.io.Serializable;
import java.util.ArrayList;


public class Army implements IBelongsToNation, IHasMapLocation, Serializable {

    int nationNo;
    int x;
    int y;

    String commanderName;
    String commanderTitle;

    NationAllegianceEnum nationAllegiance;

    InformationSourceEnum informationSource;
    InfoSource infoSource;

    ArmySizeEnum size;
    int troopCount;
    boolean navy;

    ArrayList<ArmyElement> elements = new ArrayList<ArmyElement>();

    ArrayList characters = new ArrayList();

    Integer food = null; // null when food not set
    Boolean fed = null; // null when fed not set
    Boolean cavalry = null; // null when cavalry not set

    public ArrayList getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList characters) {
        this.characters = characters;
    }

    public String getCommanderName() {
        return commanderName;
    }

    public void setCommanderName(String commanderName) {
        this.commanderName = commanderName;
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

    public NationAllegianceEnum getNationAllegiance() {
        return nationAllegiance;
    }

    public void setNationAllegiance(NationAllegianceEnum nationAllegiance) {
        this.nationAllegiance = nationAllegiance;
    }

    public int getNationNo() {
        return nationNo;
    }

    public void setNationNo(int nationNo) {
        this.nationNo = nationNo;
    }

    public boolean isNavy() {
        return navy;
    }

    public void setNavy(boolean navy) {
        this.navy = navy;
    }

    public ArmySizeEnum getSize() {
        return size;
    }

    public void setSize(ArmySizeEnum size) {
        this.size = size;
    }

    public int getTroopCount() {
        return troopCount;
    }

    public void setTroopCount(int troopCount) {
        this.troopCount = troopCount;
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

    public String getCommanderTitle() {
        return commanderTitle;
    }

    public void setCommanderTitle(String commanderTitle) {
        this.commanderTitle = commanderTitle;
    }

    public ArrayList<ArmyElement> getElements() {
        return elements;
    }

    public Boolean isFed() {
        return fed;
    }

    public void setFed(boolean fed) {
        this.fed = fed;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public Boolean computeFed() {
        if (isFed() != null) {
            return isFed();
        }
        // todo compute fed with respect to food and consumption
        // returning null if fed cannot be computed
        return null;
    }

    public String getHexNo() {
        return String.valueOf(getX() * 100 + getY());
    }

    public void setHexNo(String hexNo) {
        int hexN = Integer.parseInt(hexNo);
        setX(hexN / 100);
        setY(hexN % 100);
    }

    public Boolean computeCavalry() {
        if (isCavalry() != null) {
            return isCavalry();
        }
        // todo compute cavalry with respect to troop synthesis
        // returning null if fed cannot be computed
        return null;
    }

    public Boolean isCavalry() {
        return cavalry;
    }

    public void setCavalry(Boolean cavalry) {
        this.cavalry = cavalry;
    }

}
