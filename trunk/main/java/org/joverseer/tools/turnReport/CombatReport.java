package org.joverseer.tools.turnReport;

import org.joverseer.domain.Combat;

public class CombatReport extends BaseReportObject {
	String popInfo;
	String popOutcome;
	String participants;
	String winners;
	Combat c;
	
	public CombatReport(Combat c) {
		setHexNo(c.getHexNo());
		this.c = c;
	}

	public String getPopInfo() {
		return popInfo;
	}

	public void setPopInfo(String popInfo) {
		this.popInfo = popInfo;
	}

	public String getPopOutcome() {
		return popOutcome;
	}

	public void setPopOutcome(String popOutcome) {
		this.popOutcome = popOutcome;
	}
	
	

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getWinners() {
		return winners;
	}

	public void setWinners(String winners) {
		this.winners = winners;
	}

	@Override
	public String getHtmlString() {
		return 	
		//appendTd(getNationStr()) +
		//appendTd(getModification()) +
		appendTd(getNationsStr()) +
				appendTd(getHexNoStr()) +
				appendTd(getParticipants()) +
				appendTd(getPopInfo()) +
				appendTd(getWinners()) +
				appendTd(getPopOutcome()) +
				appendTd(getNotes()) +
				appendTd(getLinks());
	}

	@Override
	public String getLinks() {
		String str = super.getLinks();
		if (c != null) 
			str += " <a href='http://event?combat=" + c.getHexNo() +"'>Report</a>";
		return str;
	}
	
	
}
