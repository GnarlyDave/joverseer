package org.joverseer.support.infoSources;


/**
 * Information wrt to Hastage info as found in pdf files
 * 
 * @author Marios Skounakis
 *
 */
public class HostageInfoSource extends InfoSource {
    private static final long serialVersionUID = 566193776197455348L;
    int nationNo;

    public HostageInfoSource(int turnNo, int nationNo) {
        this.nationNo = nationNo;
        setTurnNo(turnNo);
    }

    public int getNationNo() {
        return nationNo;
    }

    public void setNationNo(int nationNo) {
        this.nationNo = nationNo;
    }

    public String getDescription() {
        return String.valueOf(nationNo);
    }
    
    public String toString() {
    	return "Hostage";
    }
}
