//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;

import cards.SavePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Magic_FocusPower extends AbstractPower {
    public static final String POWER_ID = "Magic_Focus";
    private static final PowerStrings powerStrings;

    public Magic_FocusPower(AbstractCreature owner, int bladeAmt) {
        this.name = powerStrings.NAME;
        this.ID = "Magic_Focus";
        this.owner = owner;
        this.amount = bladeAmt;
        this.updateDescription();
        this.loadRegion("infiniteBlades");
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new SavePower(), this.amount, false));
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
        } else {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Magic_Focus");
    }
}
