package com.mygdx.game.consolGame.hero;

import java.util.ArrayList;

public interface HeroInterfase {
    void step(ArrayList<BaseHero> teamFoe, ArrayList<BaseHero> teamFriend);
    String getInfo();

}
