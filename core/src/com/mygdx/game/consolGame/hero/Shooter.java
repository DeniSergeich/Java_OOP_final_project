package com.mygdx.game.consolGame.hero;

import com.mygdx.game.consolGame.view.AnsiColors;

import java.util.ArrayList;

public abstract class Shooter extends BaseHero implements HeroInterfase{
    //int range;
    int arrows;
    int maxArrows;
    public Shooter(Names name, String type, int hp, int maxHp, int damage, int defense, int initiative, int arrows, int maxArrows, int x, int y) {
        super(name, type, hp, maxHp, damage, defense, initiative, x, y);
        this.arrows = arrows;
        this.maxArrows = maxArrows;
    }
    @Override
    public void step(ArrayList<BaseHero> teamFoe, ArrayList<BaseHero> teamFriend) {
        if (this.isDead()) return;
        this.state = States.READY;
        if (arrows <= 0) {
            this.state = States.NOAMMO;
            return;
        }
        BaseHero nearestFoe = findNearest(getNotDeadTeamMembers(teamFoe));
        if (nearestFoe != null) {
            nearestFoe.getDamage(damage);
            System.out.printf("%s %s " + AnsiColors.ANSI_RED + "атакует" + AnsiColors.ANSI_RESET +
                    " %s %s и наносит ему %d урона\n", this.type, this.name, nearestFoe.type, nearestFoe.name, damage );
            this.arrows -= 1;
            state = States.SHOOT;
        }
        for (BaseHero person : getNotDeadTeamMembers(teamFriend)) {
            if (person == null) return;
            if (this.arrows < this.maxArrows && person.getClass() == Peasant.class && person.state.equals(States.READY)) {
                this.arrows += 1;
                person.state = States.SUPPLY;
                break;
            }
        }
    }
    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" стрел: %d/%d", this.arrows, this.maxArrows);
    }
}
