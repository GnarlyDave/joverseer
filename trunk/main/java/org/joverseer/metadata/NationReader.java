package org.joverseer.metadata;

import org.joverseer.metadata.domain.Nation;
import org.joverseer.metadata.domain.NationAllegianceEnum;

import java.io.IOException;
import java.util.ArrayList;


public class NationReader implements MetadataReader {
    Object[][] nations_2950 =
            new Object[][]{
                    {"Unknown", "Un", new Object[]{}},
                    {"Woodmen", "Wm", new Object[]{}},
                    {"Northmen", "Nm", new Object[]{SNAEnum.ShipsWith750Timber}},
                    {"Riders of Rohan", "RoR", new Object[]{}},
                    {"Dúnadan Rangers", "DR", new Object[]{}},
                    {"Silvan Elves", "Sil", new Object[]{}},
                    {"Northern Gondor", "NG", new Object[]{SNAEnum.FortificationsWithHalfTimber}},
                    {"Southern Gondor", "SG", new Object[]{SNAEnum.FortificationsWithHalfTimber}},
                    {"Dwarves", "Dwa", new Object[]{}},
                    {"Sinda Elves", "Sin", new Object[]{SNAEnum.ShipsWith500Timber}},
                    {"Noldo Elves", "No", new Object[]{}},
                    {"Witch-king", "WK", new Object[]{}},
                    {"Dragon Lord", "DL", new Object[]{}},
                    {"Dog Lord", "DoL", new Object[]{}},
                    {"Cloud Lord", "CL", new Object[]{}},
                    {"Blind Sorcerer", "BS", new Object[]{}},
                    {"Ice King", "IK", new Object[]{}},
                    {"Quiet Avenger", "QA", new Object[]{}},
                    {"Fire King", "FK", new Object[]{SNAEnum.FreeHire}},
                    {"Long Rider", "LR", new Object[]{}},
                    {"Dark Lieutenants", "DkL", new Object[]{}},
                    {"Corsairs", "Co", new Object[]{SNAEnum.ShipsWith750Timber}},
                    {"Rhûn Easterlings", "Ea", new Object[]{}},
                    {"Dunlendings", "Du", new Object[]{}},
                    {"White Wizard", "WW", new Object[]{SNAEnum.FreeHire}},
                    {"Khand Easterlings", "Kh", new Object[]{}},
            };

    Object[][] nations_1650 =
        new Object[][]{
                {"Unknown", "Un", new Object[]{}},
                {"Woodmen", "Wm", new Object[]{}},
                {"Northmen", "Nm", new Object[]{SNAEnum.ShipsWith750Timber}},
                {"Éothraim", "Eo", new Object[]{}},
                {"Arthedain", "Ar", new Object[]{}},
                {"Cardolan", "Ca", new Object[]{SNAEnum.FreeHire}},
                {"Northern Gondor", "NG", new Object[]{SNAEnum.FortificationsWithHalfTimber}},
                {"Southern Gondor", "SG", new Object[]{SNAEnum.FortificationsWithHalfTimber}},
                {"Dwarves", "Dwa", new Object[]{}},
                {"Sinda Elves", "Sin", new Object[]{SNAEnum.ShipsWith500Timber}},
                {"Noldo Elves", "No", new Object[]{}},
                {"Witch-king", "WK", new Object[]{}},
                {"Dragon Lord", "DL", new Object[]{}},
                {"Dog Lord", "DoL", new Object[]{}},
                {"Cloud Lord", "CL", new Object[]{}},
                {"Blind Sorcerer", "BS", new Object[]{}},
                {"Ice King", "IK", new Object[]{}},
                {"Quiet Avenger", "QA", new Object[]{}},
                {"Fire King", "FK", new Object[]{SNAEnum.FreeHire}},
                {"Long Rider", "LR", new Object[]{}},
                {"Dark Lieutenants", "DkL", new Object[]{}},
                {"Corsairs", "Co", new Object[]{SNAEnum.ShipsWith750Timber}},
                {"Haradwaith", "Ha", new Object[]{SNAEnum.FreeHire}},
                {"Dunlendings", "Du", new Object[]{}},
                {"Rhudaur", "Ru", new Object[]{SNAEnum.FreeHire}},
                {"Easterlings", "Ea", new Object[]{}},
        };
    
    Object[][] nations_BOFA = 
    	new Object[][]{
    		{"Unknown", "Un", new Object[]{}},
    		{"North Kingdom", "NK", new Object[]{}},
    		{"South Kingdom", "SK", new Object[]{}},
    		{"Unplayed Nat III", "UN3", new Object[]{}},
    		{"Unplayed Nat IV", "UN4", new Object[]{}},
    		{"Unplayed V", "UN5", new Object[]{}},
    		{"Unplayed VI", "UN6", new Object[]{}},
    		{"Unplayed VII", "UN7", new Object[]{}},
    		{"Unplayed VIII", "UN8", new Object[]{}},
    		{"Unplayed IX", "UN8", new Object[]{}},
    		{"Goblins", "Go", new Object[]{SNAEnum.FreeHire}},
    		{"Warg Riders", "Wa", new Object[]{SNAEnum.FreeHire}},
    		{"Elves", "El", new Object[]{SNAEnum.FreeHire}},
    		{"Dwarves", "Dwa", new Object[]{SNAEnum.FreeHire}},
    		{"Northmen", "Nmen", new Object[]{SNAEnum.FreeHire}},
    		{"Unplayed XV", "UN15", new Object[]{}},
    		{"Unplayed XVI", "UN16", new Object[]{}},
    		{"Unplayed XVII", "UN17", new Object[]{}},
    		{"Unplayed XVIII", "UN18", new Object[]{}},
    		{"Unplayed XIX", "UN19", new Object[]{}},
    		{"Unplayed XX", "UN20", new Object[]{}},
    		{"Unplayed XXI", "UN21", new Object[]{}},
    		{"Unplayed XXII", "UN22", new Object[]{}},
    		{"Unplayed XXIII", "UN23", new Object[]{}},
    		{"Unplayed XXIV", "UN24", new Object[]{}},
    		{"Unplayed XXV", "UN25", new Object[]{}}
    };
    
    Object[][] nations_1000= 
        new Object[][]{
                {"Unknown", "Un", new Object[]{}},
                {"North Kingdom", "NK", new Object[]{}},
                {"South Kingdom", "SK", new Object[]{}},
                {"Nation I", "N01", new Object[]{}},
                {"Nation II", "N02", new Object[]{}},
                {"Nation III", "N03", new Object[]{}},
                {"Nation IV", "N04", new Object[]{}},
                {"Nation V", "N05", new Object[]{}},
                {"Nation VI", "N06", new Object[]{}},
                {"Nation VII", "N07", new Object[]{}},
                {"Nation VIII", "N08", new Object[]{}},
                {"Nation IX", "N09", new Object[]{}},
                {"Nation X", "N10", new Object[]{}},
                {"Nation XI", "N11", new Object[]{}},
                {"Nation XII", "N12", new Object[]{}},
                {"Nation XIII", "N13", new Object[]{}},
                {"Nation XIV", "N14", new Object[]{}},
                {"Nation XV", "N15", new Object[]{}},
                {"Nation XVI", "N16", new Object[]{}},
                {"Nation XVII", "N17", new Object[]{}},
                {"Nation XVII", "N18", new Object[]{}},
                {"Nation XIX", "N19", new Object[]{}},
                {"Nation XX", "N20", new Object[]{}},
                {"Nation XXI", "N21", new Object[]{}},
                {"Nation XXII", "N22", new Object[]{}},
                {"Nation XXIII", "N23", new Object[]{}},
                {"Nation XXIV", "N24", new Object[]{}},
                {"Unplayed XXII", "N25", new Object[]{}},
    };
    
    private void addSNAs(Nation n, Object[] snas) {
        for (Object sna : snas) {
            n.getSnas().add((SNAEnum)sna);
        }
    }
    
    public void load(GameMetadata gm) throws IOException, MetadataReaderException {
        ArrayList nations = new ArrayList();
        if (gm.getGameType() == GameTypeEnum.game2950) {
            for (int i=0; i<26; i++) {
                String shortName = (String)nations_2950[i][1];
                String name = (String)nations_2950[i][0];
                Nation n = new Nation(i, name, shortName);
                if (n.getNumber() <= 10) {
                    n.setAllegiance(NationAllegianceEnum.FreePeople);
                } else if (n.getNumber() <= 20) {
                    n.setAllegiance(NationAllegianceEnum.DarkServants);
                } else {
                    n.setAllegiance(NationAllegianceEnum.Neutral);
                }
                addSNAs(n, (Object[])nations_2950[i][2]);
                nations.add(n);
            }
        } else if (gm.getGameType() == GameTypeEnum.game1650) {
            for (int i=0; i<26; i++) {
                String shortName = (String)nations_1650[i][1];
                String name = (String)nations_1650[i][0];
                Nation n = new Nation(i, name, shortName);
                if (n.getNumber() <= 10) {
                    n.setAllegiance(NationAllegianceEnum.FreePeople);
                } else if (n.getNumber() <= 20) {
                    n.setAllegiance(NationAllegianceEnum.DarkServants);
                } else {
                    n.setAllegiance(NationAllegianceEnum.Neutral);
                }
                addSNAs(n, (Object[])nations_1650[i][2]);
                nations.add(n);
            }
        } else if (gm.getGameType() == GameTypeEnum.gameFA) {
            //TODO fix
            for (int i=0; i<26; i++) {
                String shortName = (String)nations_1000[i][1];
                String name = (String)nations_1000[i][0];
                Nation n = new Nation(i, name, shortName);
                if (n.getNumber() <= 10) {
                    n.setAllegiance(NationAllegianceEnum.FreePeople);
                } else if (n.getNumber() <= 20) {
                    n.setAllegiance(NationAllegianceEnum.DarkServants);
                } else {
                    n.setAllegiance(NationAllegianceEnum.Neutral);
                }
                addSNAs(n, (Object[])nations_1000[i][2]);
                nations.add(n);
            }
        }
        else if (gm.getGameType() == GameTypeEnum.gameBOFA) {
            for (int i=0; i<26; i++) {
                String shortName = (String)nations_BOFA[i][1];
                String name = (String)nations_BOFA[i][0];
                Nation n = new Nation(i, name, shortName);
                if (n.getNumber() >= 10 && n.getNumber() <= 11) {
                    n.setAllegiance(NationAllegianceEnum.DarkServants);
                } else if (n.getNumber() >= 12 && n.getNumber() <= 14) {
                    n.setAllegiance(NationAllegianceEnum.FreePeople);
                } else {
                    n.setAllegiance(NationAllegianceEnum.Neutral);
                }
                addSNAs(n, (Object[])nations_BOFA[i][2]);
                nations.add(n);
            }
        }
        gm.setNations(nations);

    }
}
