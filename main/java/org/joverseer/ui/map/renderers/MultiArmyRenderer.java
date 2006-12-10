package org.joverseer.ui.map.renderers;

import org.joverseer.ui.map.MapMetadata;
import org.joverseer.domain.*;
import org.joverseer.game.Game;
import org.joverseer.ui.map.ColorPicker;
import org.joverseer.game.Turn;
import org.joverseer.game.TurnElementsEnum;
import org.joverseer.support.GameHolder;
import org.joverseer.metadata.domain.Nation;
import org.joverseer.metadata.domain.NationAllegianceEnum;
import org.springframework.richclient.application.Application;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 6 ��� 2006
 * Time: 10:55:11 ��
 * To change this template use File | Settings | File Templates.
 */
public class MultiArmyRenderer extends ImageRenderer {
    MapMetadata mapMetadata = null;

    public boolean appliesTo(Object obj) {
        return Army.class.isInstance(obj);
    }

    private void init() {
        mapMetadata = (MapMetadata) Application.instance().getApplicationContext().getBean("mapMetadata");
    }

    private boolean isArmyFp(Army army) {
//        //todo make decision based on allegiance, not nation no
//        return army.getNationNo() <= 10;
        return (army.getNationAllegiance() == NationAllegianceEnum.FreePeople);
    }

    public void render(Object obj, Graphics2D g, int x, int y) {
        if (mapMetadata == null) init();

        Army army = (Army)obj;
        Game game = ((GameHolder)Application.instance().getApplicationContext().getBean("gameHolder")).getGame();
        Turn turn = game.getTurn();

        ArrayList<Army> armiesInHex = turn.getContainer(TurnElementsEnum.Army).findAllByProperty("hexNo", army.getHexNo());

        boolean isArmyFp = isArmyFp(army);
        // find index of army in armiesInHex of same allegiance
        int i = 0;
        int j = 0;
        for (Army a : armiesInHex) {
            if (isArmyFp(a) == isArmyFp) {
                if (a == army) break;
                i++;
            }
        }
        // render up to five armies
        if (i >= 3) {
            j = 1;
            i = i - 3;
            if (i > 2) {
                return;
            }
        }

        BufferedImage armyImage = null;
        NationAllegianceEnum allegiance = army.getNationAllegiance();
        if (allegiance == NationAllegianceEnum.Neutral) {
            //todo pick game's nation opposite allegiance
            allegiance = NationAllegianceEnum.DarkServants;
        }
        armyImage = getImage("army." + allegiance.toString() + ".image");

        BufferedImage img = copyImage(armyImage);
        Color color1 = ColorPicker.getInstance().getColor1(army.getNationNo());
        Color color2 = ColorPicker.getInstance().getColor2(army.getNationNo());
        if (allegiance == NationAllegianceEnum.FreePeople) {
            changeColor(img, Color.red, color1);
            changeColor(img, Color.black, color2);
        } else {
            changeColor(img, Color.red, color2);
            changeColor(img, Color.black, color1);
        }


        int w = 9;
        int h = 9;
        int dx = mapMetadata.getGridCellWidth() * mapMetadata.getHexSize() * 1 / 5;
        int dy = mapMetadata.getGridCellHeight() * mapMetadata.getHexSize() * 13 / 20 + h * j;

        if (isArmyFp(army)) {
            dx = mapMetadata.getGridCellWidth() * mapMetadata.getHexSize() / 2 - (w) * (i + 1) - 1;
        } else {
            dx = mapMetadata.getGridCellWidth() * mapMetadata.getHexSize() / 2 + (w) * i + 1;
        }
        g.drawImage(img, x + dx, y + dy, null);
    }
}
