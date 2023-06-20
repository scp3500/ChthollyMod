//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Near_Death_Power extends AbstractPower {
    public static final String POWER_ID = "Near_Death";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private boolean justApplied = false;
    public static final int NDP_Number = 3;

    public Near_Death_Power(AbstractCreature owner, int amount, boolean isSourceMonster) {
        this.name = NAME;
        this.ID = "Near_Death";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("firebreathing");
        if (AbstractDungeon.actionManager.turnHasEnded && isSourceMonster) {
            this.justApplied = true;
        }

        this.type = PowerType.BUFF;
        this.isTurnBased = true;
    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Near_Death"));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "Near_Death", 1));
            }

        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + NDP_Number + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + NDP_Number + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        }

    }

    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            //addToBot((AbstractGameAction) new DamageAction((AbstractCreature) this.owner, new DamageInfo((AbstractCreature) this.owner, NDP_Number, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            //this.addToBot((AbstractGameAction) new LoseHPAction(this.owner, null, NDP_Number));
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(NDP_Number, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Near_Death");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}

