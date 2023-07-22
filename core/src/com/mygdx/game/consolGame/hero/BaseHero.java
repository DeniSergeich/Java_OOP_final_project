package com.mygdx.game.consolGame.hero;

import com.mygdx.game.consolGame.map.Coordinates;

import java.util.ArrayList;

public abstract class BaseHero implements HeroInterfase{
    protected Names name;
    protected String type;
    protected int hp;
    protected int maxHp;
    protected int damage;
    protected int defense;
    protected int initiative;
    public Coordinates position;
    protected States state;
    public BaseHero(Names name, String type, int hp, int maxHp, int damage, int defense, int initiative, int x, int y){
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.maxHp = maxHp;
        this.damage = damage;
        this.defense = defense;
        this.initiative = initiative;
        this.position = new Coordinates(x, y);
        this.state = States.READY;
    }
    public Coordinates getCoordinates(){
        return position;
    }
    protected BaseHero findNearest(ArrayList<BaseHero> team){
        if (team.size() == 0) return null;
        BaseHero nearest = team.get(0);
        for (BaseHero person: team) {
            if (!person.state.equals(States.DEAD)
                && this != person
                && position.getDistance(person.getCoordinates()) < position.getDistance(nearest.getCoordinates()))
                nearest = person;
        }
        return nearest;
    }
    public String getInfo() {
        return String.format("%s %s hp: %d",
                this.type, this.name.name(), this.hp);
    }
    public boolean isDead() {
        return state.equals(States.DEAD);
    }
    public int getInitiative(){
        return this.initiative;
    }
    protected void getHealing(int healPoints) {
        hp += healPoints;
        if (hp > maxHp) hp = maxHp;
    }
    protected void getDamage(int damagePoints) {
        hp -= damagePoints;
        if (hp <= 0) {
            hp = 0;
            state = States.DEAD;
        }
    }
    ArrayList<BaseHero> getNotDeadTeamMembers(ArrayList<BaseHero> team) {
        ArrayList<BaseHero> notDeadTeamMembers = new ArrayList<>();
        for (BaseHero person: team) {
            if (!person.isDead()) notDeadTeamMembers.add(person);
        }
        return notDeadTeamMembers;
    }
    @Override
    public String toString(){
        return this.type;
    }
}
