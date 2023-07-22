package com.mygdx.game.consolGame.hero;

import java.util.ArrayList;

public class Peasant extends BaseHero{
    public Peasant(Names name, int x, int y) {
        super(name,"Крестьянин", 30, 5, 1, 1, 1, x, y);
    }

    @Override
    public void step(ArrayList<BaseHero> teamFoe, ArrayList<BaseHero> teamFriend) {
        if (this.isDead()) return;
        state = States.READY;
    }
}
