//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.CardTagEnum;

public class Magic_ChainPower extends AbstractPower {
    public static final String POWER_ID = "Magic_Chain";
    private static final PowerStrings powerStrings;

    public Magic_ChainPower(AbstractCreature owner, int drawAmt) {
        this.name = powerStrings.NAME;
        this.ID = "Magic_Chain";
        this.owner = owner;
        this.amount = drawAmt;
        this.updateDescription();
        this.loadRegion("evolve");
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = powerStrings.DESCRIPTIONS[0];
        } else {
            this.description = powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2];
        }

    }

    public void onCardDraw(AbstractCard card) {
        if (card.hasTag(CardTagEnum.Magic) && !this.owner.hasPower("No Draw")) {
            this.flash();
            this.addToBot(new DrawCardAction(this.owner, this.amount));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Magic_Chain");
    }
}
