package org.joverseer.metadata.domain;

import java.io.Serializable;


public class SpellInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2027799051162482988L;
    String name;
    Integer number;
    Integer orderNumber;
    String requiredInfo;
    String requirements;
    String difficulty;
    String description;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getNumber() {
        return number;
    }
    
    public void setNumber(Integer number) {
        this.number = number;
    }
    
    public Integer getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public String getRequiredInfo() {
        return requiredInfo;
    }
    
    public void setRequiredInfo(String requiredInfo) {
        this.requiredInfo = requiredInfo;
    }
    
    public String getRequirements() {
        return requirements;
    }
    
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
    
    public SpellDifficultyEnum getDifficultyLevel() {
        if (getDifficulty().equals("E")) return SpellDifficultyEnum.Easy;
        if (getDifficulty().equals("A")) return SpellDifficultyEnum.Average;
        if (getDifficulty().equals("H")) return SpellDifficultyEnum.Hard;
        return null;
    }
}
