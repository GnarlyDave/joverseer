package org.joverseer.ui.domain;

import org.joverseer.metadata.GameTypeEnum;
import org.springframework.rules.RulesSource;
import org.springframework.rules.Rules;
import org.springframework.rules.PropertyConstraintProvider;
import org.springframework.rules.factory.Constraints;
import org.springframework.rules.constraint.property.PropertyConstraint;
import org.springframework.core.closure.Constraint;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 19 ��� 2006
 * Time: 10:39:55 ��
 * To change this template use File | Settings | File Templates.
 */
public class NewGame implements PropertyConstraintProvider {
    GameTypeEnum gameType;
    Integer number = null;
    Integer nationNo = null;
    Rules rules = null;

    public GameTypeEnum getGameType() {
        return gameType;
    }

    public void setGameType(GameTypeEnum gameType) {
        this.gameType = gameType;
    }

    public Integer getNationNo() {
        return nationNo;
    }

    public void setNationNo(Integer nationNo) {
        this.nationNo = nationNo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PropertyConstraint getPropertyConstraint(String string) {
        if (rules == null) {
            rules = new Rules();
            rules.add("nationNo", Constraints.instance().gt(0));
            rules.add("number", Constraints.instance().gt(0));
        }
        return rules.getPropertyConstraint(string);
    }
}
