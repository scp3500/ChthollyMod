//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.Iterator;
import java.util.UUID;

public class IncreaseMiscAction_N extends AbstractGameAction {
    private int miscIncrease;
    private UUID uuid;
    public int magicNumber;

    public IncreaseMiscAction_N(UUID targetUUID,int magicNumber, int miscIncrease) {
        this.uuid = targetUUID;
        this.miscIncrease = miscIncrease;
        this.magicNumber = magicNumber;
    }


    public void update() {
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!c.uuid.equals(this.uuid))
                continue;
            c.misc += this.miscIncrease;
            c.applyPowers();
            c.baseMagicNumber = c.misc;
            c.isMagicNumberModified = false;
        }
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            c.misc += this.miscIncrease;
            c.applyPowers();
            c.baseMagicNumber = c.misc;
            this.magicNumber = c.baseMagicNumber;
        }
        this.isDone = true;
    }
}
