package com.mygdx.game.consolGame.hero;

import com.mygdx.game.consolGame.view.AnsiColors;

import java.util.ArrayList;

public abstract class Magic extends BaseHero implements HeroInterfase{
    int mana;
    int maxMana;
    public Magic(Names name, String type, int hp, int maxHp, int damage, int defense, int initiative, int mana, int maxMana, int x, int y) {
        super(name,type, hp, maxHp, damage, defense, initiative, x, y);
        this.mana = mana;
        this.maxMana = maxMana;
    }
    private BaseHero findMostDamaged(ArrayList<BaseHero> team) {
        if (team.size() == 0) return null;
        BaseHero mostDamaged = team.get(0);
        for (BaseHero person : team) {
            if (!person.state.equals(States.DEAD)
                    && person.hp < person.maxHp
                    && person.hp/person.maxHp < mostDamaged.hp/mostDamaged.maxHp) {
                mostDamaged = person;
            }
        }
        if (mostDamaged.hp == mostDamaged.maxHp) {
            return null;
        }
        return mostDamaged;
    }

    public void step(ArrayList<BaseHero> teamFoe, ArrayList<BaseHero> teamFriend) {
        if (this.isDead()) return;
        if (mana < maxMana) mana += 1;
        if (mana < damage) {
            state = States.NOMANA;
            return;
        }
        BaseHero damagedFriend = findMostDamaged(getNotDeadTeamMembers(teamFriend));
        if (damagedFriend != null) {
            damagedFriend.getHealing(damage);
            System.out.printf("%s %s  лечит %s %s и восстанавливает ему %d здоровья\n", this.type, this.name, damagedFriend.type, damagedFriend.name, damage );
            mana -= damage;
            state = States.CAST;
        }
    }
    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" мана: %d/%d", this.mana, this.maxMana);
    }
}
