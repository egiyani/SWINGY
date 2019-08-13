package swingy.mvc.models.Enemy;

import swingy.mvc.models.Artifact.Artifact;

public class DragonPriest extends Enemy{

    public DragonPriest(int lvl, int attack, int protection, int hitp, int exp, Artifact art) {
        super(lvl, attack, protection, hitp, exp, art);
        int idType = 1;
        super.setIdType(idType);
    }

}