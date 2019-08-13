package swingy.mvc.models.Enemy;

import swingy.mvc.models.Artifact.Artifact;

public class Vampire extends Enemy{

    public Vampire(int lvl, int attack, int protection, int hitp, int exp, Artifact art) {
        super(lvl, attack, protection, hitp, exp, art);
        int idType = 4;
        super.setIdType(idType);
    }

}