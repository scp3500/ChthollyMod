//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import power.Lose_Memory_Power;

import java.util.ArrayList;
import java.util.Iterator;

public class RecallDrawAction extends AbstractGameAction {
    public static int amount;

    public RecallDrawAction(AbstractPlayer source, int maxAmount) {
        this.setValues(this.target, source);
        this.actionType = ActionType.WAIT;
        if (AbstractDungeon.player.getPower("Lose_Memory") != null) {
            amount = Math.min(maxAmount, AbstractDungeon.player.getPower("Lose_Memory").amount);
        }
    }

    public void update() {
        if (AbstractDungeon.player.getPower("Lose_Memory") != null) {
            this.addToTop(new DrawCardAction(this.source, amount));
        }
        this.isDone = true;
    }
}
