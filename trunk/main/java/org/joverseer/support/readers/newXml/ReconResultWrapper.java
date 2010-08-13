package org.joverseer.support.readers.newXml;

import java.util.ArrayList;

import org.joverseer.domain.Army;
import org.joverseer.game.Game;
import org.joverseer.game.Turn;
import org.joverseer.support.readers.pdf.OrderResult;
import org.joverseer.support.readers.xml.TurnXmlReader;

public class ReconResultWrapper implements OrderResult {

	ArrayList<Army> armies = new ArrayList<Army>(); 
	
	public void updateGame(Game game, Turn turn, int nationNo, String character) {
		for (Army a : armies) {
			TurnXmlReader.addArmy(a, game, turn, true);
		}
	}
	
	public void addArmy(Army a) {
		armies.add(a);
	}

}
