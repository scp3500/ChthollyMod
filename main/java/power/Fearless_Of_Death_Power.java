package power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Fearless_Of_Death_Power extends AbstractPower {
    public static final String POWER_ID = "Fearless_Of_Death";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final int lose_HP = 5;

    public Fearless_Of_Death_Power(AbstractCreature owner, int drawAmount) {
        this.name = NAME;
        this.ID = "Fearless_Of_Death";
        this.owner = owner;
        this.amount = drawAmount;
        this.updateDescription();
        this.loadRegion("brutality");
    }

    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3] + lose_HP + DESCRIPTIONS[4];
        }

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            addToTop((AbstractGameAction) new DrawCardAction(this.owner, this.amount));
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        if (this.owner.getPower("The_Happiest") != null && this.owner.currentHealth <= lose_HP) {
            this.addToBot((AbstractGameAction) new LoseHPAction(this.owner, this.owner, 0));
        } else {
            this.addToBot((AbstractGameAction) new LoseHPAction(this.owner, this.owner, lose_HP));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Fearless_Of_Death");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
