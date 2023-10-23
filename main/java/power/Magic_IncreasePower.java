//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches_cht.CardTagEnum;

public class Magic_IncreasePower extends AbstractPower {
    public static final String POWER_ID = "Magic_Increase";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public Magic_IncreasePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Magic_Increase";
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.loadRegion("echo");
    }

    public void updateDescription() {
            this.description = DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.hasTag(CardTagEnum.Magic)){
            this.flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Magic_Increase");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
