package org.joverseer.support.readers.pdf;

import java.util.ArrayList;

import org.joverseer.domain.Company;


public class CompanyWrapper {
    String commanderName;
    int hexNo;
    ArrayList<String> members = new ArrayList<String>();
    
    public String getCommanderName() {
        return commanderName;
    }
    
    public void setCommanderName(String commanderName) {
        this.commanderName = commanderName;
    }
    
    public int getHexNo() {
        return hexNo;
    }
    
    public void setHexNo(int hexNo) {
        this.hexNo = hexNo;
    }
    
    public void addMember(String member) {
        members.add(member);
    }

    
    public ArrayList<String> getMembers() {
        return members;
    }

    
    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
    
    public Company getCompany() {
        Company c = new Company();
        c.setHexNo(hexNo);
        c.setCommander(getCommanderName());
        for (String m : getMembers()) {
            c.addMember(m);
        }
        return c;
    }
    
    
    
}
