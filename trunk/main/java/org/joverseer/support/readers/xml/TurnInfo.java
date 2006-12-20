package org.joverseer.support.readers.xml;

import org.joverseer.support.Container;


public class TurnInfo {
    Container popCentres;
    Container characters;
    Container armies;

    NationInfoWrapper nationInfoWrapper;
    EconomyWrapper economy;

    int gameNo;
    int turnNo;
    int nationNo;
    String gameType;

    public Container getCharacters() {
        return characters;
    }

    public void setCharacters(Container characters) {
        this.characters = characters;
    }

    public Container getPopCentres() {
        return popCentres;
    }

    public void setPopCentres(Container popCentres) {
        this.popCentres = popCentres;
    }

    public Container getArmies() {
        return armies;
    }

    public void setArmies(Container armies) {
        this.armies = armies;
    }

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getNationNo() {
        return nationNo;
    }

    public void setNationNo(int nationNo) {
        this.nationNo = nationNo;
    }

    public int getTurnNo() {
        return turnNo;
    }

    public void setTurnNo(int turnNo) {
        this.turnNo = turnNo;
    }

    public NationInfoWrapper getNationInfoWrapper() {
        return nationInfoWrapper;
    }

    public void setNationInfoWrapper(NationInfoWrapper nationInfoWrapper) {
        this.nationInfoWrapper = nationInfoWrapper;
    }

    public EconomyWrapper getEconomy() {
        return economy;
    }

    public void setEconomy(EconomyWrapper economy) {
        this.economy = economy;
    }

}