package swingy.mvc.models.Enemy;

import swingy.mvc.models.Artifact.Artifact;

public class Draugr extends Enemy{

    public Draugr(int lvl, int attack, int protection, int hitp, int exp, Artifact art) {
        super(lvl, attack, protection, hitp, exp, art);
        int idType = 2;
        super.setIdType(idType);
    }

}