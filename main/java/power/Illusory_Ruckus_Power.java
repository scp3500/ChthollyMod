//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Illusory_Ruckus_Power extends AbstractPower {
    public static final String POWER_ID = "Illusory_Ruckus";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public Illusory_Ruckus_Power(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Illusory_Ruckus";
        this.owner = owner;
        this.amount = -1;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("corruption");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[1];
    }

    public void onCardDraw(AbstractCard card) {
        if (card.type == CardType.ATTACK) {
            card.setCostForTurn(-9);
        }

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.ATTACK) {
            this.flash();
            action.exhaustCard = true;
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Illusory_Ruckus");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
