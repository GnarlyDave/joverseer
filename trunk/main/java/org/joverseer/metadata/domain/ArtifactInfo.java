package org.joverseer.metadata.domain;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Background information for artifacts. It holds the number, name, alignment, 
 * starting owner and powers for each artifact.
 * 
 * @author Marios Skounakis
 *
 */

public class ArtifactInfo implements Serializable {
    private static final long serialVersionUID = -2804713282789639647L;
    public static final String EMPTY_POWER = "Unknown" ;
    String name;
    int no;
    ArrayList<String> powers ;
    String alignment;
    String owner;
    int currentlyHiddenPopCenter = -1;

    public ArtifactInfo() {
    	powers = new ArrayList<String>(2) ;
    	powers.add(EMPTY_POWER) ;
    	powers.add(EMPTY_POWER) ;
    }
    
    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<String> powers) {
        this.powers = powers;
    }

    public String getPower1() {
        if (powers.size() == 0) {
            return "";
        }
        return powers.get(0).toString();
    }

    public String getPower2() {
        if (powers.size() < 2) {
            return "";
        }
        return powers.get(1).toString(); 
    }

	public void setPower(int index, String updatedPower) {
		if (powers.size() <= index) {
			powers.add(updatedPower);
		} else {
			powers.set(index, updatedPower);
		}
	}
	
	public String getBonusType() {
		if (getPower1() == null) return null;
		if (getPower1().startsWith("Command")) return "Command";
		if (getPower1().startsWith("Mage")) return "Mage";
		if (getPower1().startsWith("Agent")) return "Agent";
		if (getPower1().startsWith("Emmisary")) return "Emissary";
		if (getPower1().startsWith("Emissary")) return "Emissary";
		if (getPower1().startsWith("Stealth")) return "Stealth";
		if (getPower1().startsWith("Combat")) return "Combat";
		return null;
	}
	
	public int getBonusRank() {
		if (getBonusType() == null) return 0;
		String power1 = getPower1();
		if (power1.endsWith("*")) {
			power1 = power1.substring(0, power1.length()-1);
		}
		String[] ps = power1.split(" ");
		try {
			if (ps.length == 2) {
				return Integer.parseInt(ps[1]);
			}
		}
		catch (Exception exc) {
			return 0;
		}
		return 0;
		
	}
	
	public int getCurrentlyHiddenPopCenter() {
		return currentlyHiddenPopCenter;
	}

	public void setCurrentlyHiddenPopCenter(int currentlyHiddenPopCenter) {
		this.currentlyHiddenPopCenter = currentlyHiddenPopCenter;
	}
}
