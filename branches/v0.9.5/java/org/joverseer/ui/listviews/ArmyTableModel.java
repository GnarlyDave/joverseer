package org.joverseer.ui.listviews;

import java.util.ArrayList;

import org.joverseer.domain.Army;
import org.joverseer.domain.ArmyElement;
import org.joverseer.domain.ArmyElementType;
import org.joverseer.tools.CombatUtils;
import org.joverseer.ui.support.GraphicUtils;
import org.springframework.context.MessageSource;

public class ArmyTableModel extends ItemTableModel {

	public ArmyTableModel(MessageSource messageSource) {
		super(Army.class, messageSource);
	}

	protected String[] createColumnPropertyNames() {
		return new String[]{"hexNo", "nationNo", "commanderName", "size", "info", "totalTroops", "enHI", "food", "characters"};
	}

	protected Class[] createColumnClasses() {
		return new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class}; 
	}

	protected Object getValueAtInternal(Object object, int i) {
		Army army = (Army)object;
		if (i == 2) {
			String commander = army.getCommanderName();
			return GraphicUtils.parseName(commander);
		} else if (i == 4) {
			if (army.getElements().size() > 0) {
				String txt = "";
	            for (ArmyElement element : army.getElements()) {
	            	txt += (txt.equals("") ? "" : " ")
	                        + element.getDescription();
	            }
	            return txt;
			} else if (army.getTroopCount() > 0) {
				return "~" + army.getTroopCount() + " men";
			} else {
				return "-";
			} 
		} else if (i == 5) {
			if (army.getElements().size() > 0) {
				int count = 0;
				for (ArmyElement el : army.getElements()) {
					if (el.getArmyElementType() != ArmyElementType.WarMachimes &&
							el.getArmyElementType() != ArmyElementType.Warships &&
							el.getArmyElementType() != ArmyElementType.Transports) {
						count += el.getNumber();
					}
				}
				return String.valueOf(count);
			} else if (army.getTroopCount() > 0) {
				return String.valueOf(army.getTroopCount());
			} else {
				return "-";
			}
		} else if (i==6) { 
			if (army.getElements().size() > 0) {
				return CombatUtils.getNakedHeavyInfantryEquivalent(army);
			} else {
				return null;
			}
		} 
		else if (i == 8) { 
			String chars = "";
			for (String ch : (ArrayList<String>)army.getCharacters()) {
				chars += (chars.equals("") ? "" : ", ") + ch;
			}
			return chars;
		} 
		else {
			return super.getValueAtInternal(object, i);
		}
	}
	
	

}
