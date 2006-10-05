package org.joverseer.metadata.domain;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: Sep 10, 2006
 * Time: 11:30:02 PM
 * To change this template use File | Settings | File Templates.
 */
public enum NationAllegianceEnum {
    FreePeople (1),
    DarkServants (2),
    Neutral (3);

    private final int allegiance;

    NationAllegianceEnum(int allegiance) {
       this.allegiance = allegiance;
   }
}
