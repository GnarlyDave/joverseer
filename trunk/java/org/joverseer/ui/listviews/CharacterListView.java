package org.joverseer.ui.listviews;

import org.joverseer.game.TurnElementsEnum;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 14 ��� 2006
 * Time: 4:10:19 ��
 * To change this template use File | Settings | File Templates.
 */
public class CharacterListView extends ItemListView {
    public CharacterListView() {
        super(TurnElementsEnum.Character, CharacterTableModel.class);
    }
}
