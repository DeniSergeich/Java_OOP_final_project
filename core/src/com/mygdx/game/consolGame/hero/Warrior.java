package com.mygdx.game.consolGame.hero;

import com.mygdx.game.consolGame.map.Coordinates;
import com.mygdx.game.consolGame.map.Directions;
import com.mygdx.game.consolGame.view.AnsiColors;

import java.util.ArrayList;

public abstract class Warrior extends BaseHero{
    public Warrior(Names name, String type, int hp, int maxHp, int damage, int defense, int initiative, int x, int y) {
        super(name,type, hp, maxHp, damage, defense, initiative, x, y);
    }
    @Override
    public void step(ArrayList<BaseHero> teamFoe, ArrayList<BaseHero> teamFriend) {
        if (this.isDead()) return;
        BaseHero nearestFoe = findNearest(getNotDeadTeamMembers(teamFoe));
        if (nearestFoe == null) return;
        if (this.attack(nearestFoe))return;
        this.move(nearestFoe, getNotDeadTeamMembers(teamFriend), getNotDeadTeamMembers(teamFoe));
    }
    @Override
    public String getInfo() {
        return super.getInfo();
    }
    public boolean attack(BaseHero enemy) {
        if (this.getCoordinates().getDistance(enemy.getCoordinates()) == 1) {
            enemy.getDamage(damage);
            System.out.printf("%s %s атакует %s %s и наносит ему %d урона\n", this.type, this.name, enemy.type, enemy.name, damage );
            state = States.ATTACK;
            return true;
        }
        return false;
    }
    public void move(BaseHero enemy, ArrayList<BaseHero> team1, ArrayList<BaseHero> team2) {
        Directions direction = this.getCoordinates().getDirection(enemy.getCoordinates());
        switch (direction) {
            case SOUTH:
                if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0] + 1, this.getCoordinates().toArray()[1]))
                        && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0] + 1, this.getCoordinates().toArray()[1])))
                    this.position.setCoordinates(this.getCoordinates().toArray()[0] + 1, this.getCoordinates().toArray()[1]);
                state = States.MOVE;
                break;
            case NORTH:
                if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0] - 1, this.getCoordinates().toArray()[1]))
                        && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0] - 1, this.getCoordinates().toArray()[1])))
                    this.position.setCoordinates(this.getCoordinates().toArray()[0] - 1, this.getCoordinates().toArray()[1]);
                state = States.MOVE;
                break;
            case WEST:
                if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] - 1))
                        && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] - 1)))
                    this.position.setCoordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] - 1);
                state = States.MOVE;
                break;
            case EAST:
                if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] + 1))
                        && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] + 1)))
                    this.position.setCoordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] + 1);
                state = States.MOVE;
                break;
        }
    }
    private boolean checkStepAheadIsAvailable(ArrayList<BaseHero> team, Coordinates coordinates) {
        for (BaseHero character : team) {
            if (!character.isDead() && coordinates.isEqual(character.getCoordinates())) return false;
        }
        return true;
    }
}
