package org.joverseer.ui.viewers;

import org.joverseer.game.TurnElementsEnum;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 14 ��� 2006
 * Time: 5:45:04 ��
 * To change this template use File | Settings | File Templates.
 */
public class NationEconomyListView extends ItemListView {
    public NationEconomyListView() {
        super(TurnElementsEnum.NationEconomy, NationEconomyTableModel.class);
    }
}
