package org.joverseer.ui.listviews.advancedCharacterListView;

import org.joverseer.domain.CharacterDeathReasonEnum;
import org.joverseer.support.infoSources.InfoSource;
import org.joverseer.tools.infoCollectors.artifacts.ArtifactWrapper;
import org.joverseer.tools.infoCollectors.characters.AdvancedCharacterWrapper;
import org.joverseer.tools.infoCollectors.characters.CharacterAttributeWrapper;
import org.joverseer.ui.listviews.ItemTableModel;
import org.springframework.context.MessageSource;

/**
 * Table model for the AdvancedCharacterWrapper class
 * 
 * @author Marios Skounakis
 */
public class AdvancedCharacterTableModel extends ItemTableModel {

    public AdvancedCharacterTableModel(MessageSource messageSource) {
        super(AdvancedCharacterWrapper.class, messageSource);
    }

    protected String[] createColumnPropertyNames() {
        return new String[] {"name", "hexNo", "nationNo", "command", "agent", "emmisary", "mage", "stealth", "health",
                "challenge", "a0", "a1", "a2", "a3", "a4", "a5", "travellingWith", "deathReason", "infoSource",
                "turnNo", "dragonPotential"};
    }

    protected Class[] createColumnClasses() {
        return new Class[] {String.class, String.class, String.class, CharacterAttributeWrapper.class,
                CharacterAttributeWrapper.class, CharacterAttributeWrapper.class, CharacterAttributeWrapper.class,
                CharacterAttributeWrapper.class, CharacterAttributeWrapper.class, CharacterAttributeWrapper.class,
                ArtifactWrapper.class, ArtifactWrapper.class, ArtifactWrapper.class, ArtifactWrapper.class,
                ArtifactWrapper.class, ArtifactWrapper.class, String.class, CharacterDeathReasonEnum.class,
                InfoSource.class, String.class, Integer.class};
    }

}
