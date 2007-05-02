// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces fieldsfirst safe 
// Source File Name:   SpellList.java

package com.middleearthgames.orderchecker;

import java.util.Vector;

// Referenced classes of package com.middleearthgames.orderchecker:
//            Spell, Character

class SpellList
{

    private static final int SPELL_LISTS = 18;
    private static final int HEALING_MASTERY = 0;
    private static final int HEALING_WAYS = 1;
    private static final int BARRIER_MASTERY = 2;
    private static final int RESISTANCE_MASTERY = 3;
    private static final int FIRE_MASTERY = 4;
    private static final int WORD_MASTERY = 5;
    private static final int WIND_MASTERY = 6;
    private static final int DARK_SUMMONS = 7;
    private static final int SPIRIT_MASTERY = 8;
    private static final int CONJURING_WAYS = 9;
    private static final int MOVEMENT_MASTERY = 10;
    private static final int RETURN_MASTERY = 11;
    private static final int TELEPORT_MASTERY = 12;
    private static final int PERCEPTIONS = 13;
    private static final int DIVINATIONS = 14;
    private static final int ARTIFACT_LORE = 15;
    private static final int SCRYING = 16;
    private static final int HIDDEN_VISIONS = 17;
    private Vector spells[];

    SpellList()
    {
        spells = new Vector[18];
        for(int i = 0; i < 18; i++)
        {
            spells[i] = new Vector();
        }

        configureLists();
    }

    boolean hasSpellRequisite(Character character, Spell spell)
    {
        if(spell.getDifficulty() == lowestDifficulty(spell.getList()))
        {
            return true;
        }
        int size = character.getSpellCount();
        int spellList[] = character.getSpells();
        for(int i = 0; i < size; i++)
        {
            Spell charSpell = findSpell(spellList[i]);
            if(charSpell != null && charSpell.getList() == spell.getList() && charSpell.getDifficulty() <= spell.getDifficulty())
            {
                return true;
            }
        }

        return false;
    }

    boolean isSpellInLostList(int spellNum)
    {
        Spell spell = findSpell(spellNum);
        switch(spell.getList())
        {
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 12: // '\f'
            return true;

        case 10: // '\n'
        case 11: // '\013'
        default:
            return false;
        }
    }

    Spell findSpell(int spellNum)
    {
        for(int i = 0; i < 18; i++)
        {
            int size = spells[i].size();
            for(int j = 0; j < size; j++)
            {
                Spell spell = (Spell)spells[i].get(j);
                if(spell.getSpellNumber() == spellNum)
                {
                    return spell;
                }
            }

        }

        return null;
    }

    private int lowestDifficulty(int list)
    {
        int easiest = 2;
        int size = spells[list].size();
        for(int i = 0; i < size && easiest != 0; i++)
        {
            Spell spell = (Spell)spells[list].get(i);
            if(spell.getDifficulty() < easiest)
            {
                easiest = spell.getDifficulty();
            }
        }

        return easiest;
    }

    private void configureLists()
    {
        spells[0].add(((Object) (new Spell(2, "Minor Heal", 0, 120, 0))));
        spells[0].add(((Object) (new Spell(8, "Heal True", 2, 120, 0))));
        spells[1].add(((Object) (new Spell(4, "Major Heal", 0, 120, 1))));
        spells[1].add(((Object) (new Spell(6, "Greater Heal", 1, 120, 1))));
        spells[2].add(((Object) (new Spell(102, "Barriers", 0, 225, 2))));
        spells[2].add(((Object) (new Spell(106, "Deflections", 1, 225, 2))));
        spells[2].add(((Object) (new Spell(112, "Shields", 2, 225, 2))));
        spells[2].add(((Object) (new Spell(114, "Barrier Walls", 2, 225, 2))));
        spells[3].add(((Object) (new Spell(104, "Resistances", 0, 225, 3))));
        spells[3].add(((Object) (new Spell(108, "Blessings", 1, 225, 3))));
        spells[3].add(((Object) (new Spell(110, "Protections", 2, 225, 3))));
        spells[3].add(((Object) (new Spell(116, "Force Walls", 2, 225, 3))));
        spells[4].add(((Object) (new Spell(202, "Call Fire", 0, 225, 4))));
        spells[4].add(((Object) (new Spell(204, "Wild Flames", 0, 225, 4))));
        spells[4].add(((Object) (new Spell(206, "Wall of Fire", 0, 225, 4))));
        spells[4].add(((Object) (new Spell(232, "Fire Bolts", 1, 225, 4))));
        spells[4].add(((Object) (new Spell(234, "Fire Balls", 1, 225, 4))));
        spells[4].add(((Object) (new Spell(236, "Fire Storms", 1, 225, 4))));
        spells[4].add(((Object) (new Spell(240, "Summon Fire Spirits", 0, 225, 4))));
        spells[5].add(((Object) (new Spell(208, "Words of Pain", 0, 225, 5))));
        spells[5].add(((Object) (new Spell(210, "Words of Calm", 0, 225, 5))));
        spells[5].add(((Object) (new Spell(212, "Words of Paralysis", 0, 225, 5))));
        spells[5].add(((Object) (new Spell(220, "Words of Agony", 1, 225, 5))));
        spells[5].add(((Object) (new Spell(222, "Words of Stun", 1, 225, 5))));
        spells[5].add(((Object) (new Spell(224, "Words of Command", 1, 225, 5))));
        spells[5].add(((Object) (new Spell(242, "Words of Death", 0, 225, 5))));
        spells[6].add(((Object) (new Spell(214, "Call Winds", 0, 225, 6))));
        spells[6].add(((Object) (new Spell(216, "Wild Winds", 0, 225, 6))));
        spells[6].add(((Object) (new Spell(218, "Wall of Wind", 0, 225, 6))));
        spells[6].add(((Object) (new Spell(226, "Chill Bolts", 1, 225, 6))));
        spells[6].add(((Object) (new Spell(228, "Frost Balls", 1, 225, 6))));
        spells[6].add(((Object) (new Spell(230, "Wind Storms", 1, 225, 6))));
        spells[6].add(((Object) (new Spell(238, "Summon Wind Spirits", 0, 225, 6))));
        spells[7].add(((Object) (new Spell(244, "Fearful Hearts", 1, 225, 7))));
        spells[7].add(((Object) (new Spell(246, "Summon Storms", 1, 225, 7))));
        spells[7].add(((Object) (new Spell(248, "Fanaticism", 1, 225, 7))));
        spells[8].add(((Object) (new Spell(502, "Weakness", 0, 330, 8))));
        spells[8].add(((Object) (new Spell(504, "Sickness", 1, 330, 8))));
        spells[8].add(((Object) (new Spell(506, "Curses", 2, 330, 8))));
        spells[9].add(((Object) (new Spell(508, "Conjure Mounts", 0, 330, 9))));
        spells[9].add(((Object) (new Spell(510, "Conjure Food", 1, 330, 9))));
        spells[9].add(((Object) (new Spell(512, "Conjure Hordes", 1, 330, 9))));
        spells[10].add(((Object) (new Spell(302, "Long Stride", 0, 825, 10))));
        spells[10].add(((Object) (new Spell(304, "Fast Stride", 1, 825, 10))));
        spells[10].add(((Object) (new Spell(306, "Path Mastery", 2, 825, 10))));
        spells[11].add(((Object) (new Spell(308, "Capital Return", 0, 825, 11))));
        spells[11].add(((Object) (new Spell(310, "Major Return", 1, 825, 11))));
        spells[11].add(((Object) (new Spell(312, "Return True", 2, 825, 11))));
        spells[12].add(((Object) (new Spell(314, "Teleport", 1, 825, 12))));
        spells[13].add(((Object) (new Spell(402, "Perceive Allegiance", 0, 940, 13))));
        spells[13].add(((Object) (new Spell(404, "Perceive Relations", 0, 940, 13))));
        spells[13].add(((Object) (new Spell(408, "Perceive Nationality", 0, 940, 13))));
        spells[13].add(((Object) (new Spell(422, "Perceive Power", 1, 940, 13))));
        spells[13].add(((Object) (new Spell(424, "Perceive Mission", 1, 940, 13))));
        spells[13].add(((Object) (new Spell(432, "Perceive Secrets", 2, 940, 13))));
        spells[14].add(((Object) (new Spell(406, "Divine Army", 0, 940, 14))));
        spells[14].add(((Object) (new Spell(410, "Divine Allegiance Forces", 0, 940, 14))));
        spells[14].add(((Object) (new Spell(417, "Divine Characters w/Forces", 1, 940, 14))));
        spells[14].add(((Object) (new Spell(419, "Divine Nation Forces", 1, 940, 14))));
        spells[14].add(((Object) (new Spell(426, "Divine Army True", 2, 940, 14))));
        spells[15].add(((Object) (new Spell(412, "Research Artifact", 0, 940, 15))));
        spells[15].add(((Object) (new Spell(418, "Locate Artifact", 1, 940, 15))));
        spells[15].add(((Object) (new Spell(428, "Locate Artifact True", 2, 940, 15))));
        spells[16].add(((Object) (new Spell(413, "Scry Population Center", 0, 940, 16))));
        spells[16].add(((Object) (new Spell(414, "Scry Hex", 0, 940, 16))));
        spells[16].add(((Object) (new Spell(415, "Scry Area", 1, 940, 16))));
        spells[16].add(((Object) (new Spell(436, "Scry Character", 2, 940, 16))));
        spells[17].add(((Object) (new Spell(416, "Reveal Production", 0, 940, 17))));
        spells[17].add(((Object) (new Spell(420, "Reveal Character", 1, 940, 17))));
        spells[17].add(((Object) (new Spell(430, "Reveal Character True", 2, 940, 17))));
        spells[17].add(((Object) (new Spell(434, "Reveal Population Center", 2, 940, 17))));
    }
}
