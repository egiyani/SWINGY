package swingy.mvc.models.Enemy;

import swingy.mvc.models.Artifact.Artifact;

public class Falmer extends Enemy{

    public Falmer(int lvl, int attack, int protection, int hitp, int exp, Artifact art) {
        super(lvl, attack, protection, hitp, exp, art);
        int idType = 3;
        super.setIdType(idType);
    }

}