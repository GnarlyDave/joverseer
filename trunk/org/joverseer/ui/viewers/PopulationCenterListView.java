package org.joverseer.ui.viewers;

import org.joverseer.game.TurnElementsEnum;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 14 ��� 2006
 * Time: 5:13:12 ��
 * To change this template use File | Settings | File Templates.
 */
public class PopulationCenterListView extends ItemListView {
    public PopulationCenterListView() {
        super(TurnElementsEnum.PopulationCenter, PopulationCenterTableModel.class);
    }


}
