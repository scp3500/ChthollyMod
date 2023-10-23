//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import patches_cht.CardTagEnum;

import java.util.Iterator;

public class AllMagicToHandAction extends AbstractGameAction {
    private AbstractPlayer p;

    public AllMagicToHandAction() {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.p.discardPile.size() > 0) {
            Iterator var1 = this.p.discardPile.group.iterator();

            label21:
            while(true) {
                AbstractCard card;
                do {
                    if (!var1.hasNext()) {
                        break label21;
                    }

                    card = (AbstractCard)var1.next();
                } while(!card.hasTag(CardTagEnum.Magic));

                this.addToBot(new DiscardToHandAction(card));
            }
        }

        this.tickDuration();
        this.isDone = true;
    }
}
