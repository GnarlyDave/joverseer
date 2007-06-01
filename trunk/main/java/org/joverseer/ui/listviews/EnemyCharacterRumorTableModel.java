package org.joverseer.ui.listviews;

import org.joverseer.ui.domain.EnemyCharacterRumorWrapper;
import org.springframework.context.MessageSource;

/**
 * Table model for Enemy Character Rumors
 * 
 * @author Marios Skounakis
 */
public class EnemyCharacterRumorTableModel extends ItemTableModel {
    public EnemyCharacterRumorTableModel(MessageSource messageSource) {
        super(EnemyCharacterRumorWrapper.class, messageSource);
    }

    protected String[] createColumnPropertyNames() {
        return new String[]{"name", "turnNo", "startChar", "reportedTurns"};
    }

    protected Class[] createColumnClasses() {
        return new Class[]{String.class, String.class, Boolean.class, String.class};
    }

}
