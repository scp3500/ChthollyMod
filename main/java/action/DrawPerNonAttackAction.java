//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pathes.CardTagEnum;

import java.util.ArrayList;
import java.util.Iterator;

public class DrawPerNonAttackAction extends AbstractGameAction {
    public int count = 0;

    public DrawPerNonAttackAction() {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = ActionType.DRAW;
    }

    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (!c.hasTag(CardTagEnum.Magic)) {
                cardsToExhaust.add(c);
            }
        }

        var2 = cardsToExhaust.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            count++;
        }
        this.addToTop(new DrawCardAction(AbstractDungeon.player,count));

        var2 = cardsToExhaust.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }

        this.isDone = true;
    }
}
