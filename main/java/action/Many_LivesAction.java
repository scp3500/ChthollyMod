//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class Many_LivesAction extends AbstractGameAction {
    private boolean upgraded = false;

    public Many_LivesAction(boolean upgraded) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.upgraded = upgraded;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.changeAllCardsInGroup(p.hand);
            this.changeAllCardsInGroup(p.drawPile);
            this.changeAllCardsInGroup(p.discardPile);
            this.changeAllCardsInGroup(p.exhaustPile);
            this.isDone = true;
        }

    }

    private void changeAllCardsInGroup(CardGroup cardGroup) {
        Iterator var2 = cardGroup.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (!upgraded && c.exhaust) {
                c.exhaust = false;
                c.applyPowers();
            }
            if (upgraded) {
                c.exhaust = !c.exhaust;
                c.applyPowers();
            }
        }
    }
}
