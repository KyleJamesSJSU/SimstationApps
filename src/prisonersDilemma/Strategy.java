package prisonersDilemma;

import simstation.*;
import mvc.*;

import java.io.Serializable;

public abstract class Strategy implements Serializable {

    protected Prisoner myPrisoner;
    public static final int STRATEGY_COUNT = 4;
    protected StrategyType type;

    public Strategy(Prisoner p) {
        this.myPrisoner = p;
    }

    public StrategyType getType() {
        return type;
    }

    public void setMyPrisoner(Prisoner p) {
        this.myPrisoner = p;
    }

    abstract boolean cooperate(); // TRUE = cooperate, FALSE = cheat
}

enum StrategyType {
    COOPERATE,
    RANDOMLY_COOPERATE,
    CHEAT,
    TIT4TAT
}

class Cooperate extends Strategy {

    public Cooperate(Prisoner p) {
        super(p);
        type = StrategyType.COOPERATE;
    }
    public boolean cooperate() {
        return true; // always cooperate
    }

}

class RandomlyCooperate extends Strategy {

    public RandomlyCooperate(Prisoner p) {
        super(p);
        type = StrategyType.RANDOMLY_COOPERATE;
    }
    public boolean cooperate() {
        // randomly cooperate/cheat
        return Utilities.rng.nextBoolean();
    }
}

class Cheat extends Strategy {

    public Cheat(Prisoner p) {
        super(p);
        type = StrategyType.CHEAT;
    }

    public boolean cooperate() {
        // always cheat
        return false;
    }
}

class Tit4Tat extends Strategy {
    public Tit4Tat(Prisoner p) {
        super(p);
        type = StrategyType.TIT4TAT;
    }
    public boolean cooperate() {
        // cheat if last opponent cheated
        return !myPrisoner.isPartnerCheated();
    }
}