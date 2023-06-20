//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.cards.purple.PressurePoints;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.PressurePointEffect;

public class ErodedPower extends AbstractPower {
    public static final String POWER_ID = "Poison";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private AbstractCreature source;
    private boolean justApplied = false;

    public ErodedPower(AbstractCreature owner, AbstractCreature source, int erodedAmt) {
        this.name = NAME;
        this.ID = "Eroded";
        this.owner = owner;
        this.source = source;
        this.amount = erodedAmt;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        this.updateDescription();
        this.loadRegion("poison");
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }

    public void updateDescription() {
        if (this.source.getPower("Lose_Memory") != null) {
            this.description = DESCRIPTIONS[0] + this.source.getPower("Lose_Memory").amount + DESCRIPTIONS[1];
        }else {
            this.description = DESCRIPTIONS[0] + 0 + DESCRIPTIONS[1];
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && this.source.getPower("Lose_Memory") != null) {
            this.flashWithoutSound();
            if (this.owner != null) {
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PressurePointEffect(this.owner.hb.cX, this.owner.hb.cY)));
            }
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.source, (AbstractPower)new MarkPower((AbstractCreature)this.owner, this.source.getPower("Lose_Memory").amount), this.source.getPower("Lose_Memory").amount));
                addToBot((AbstractGameAction)new TriggerMarksAction(new PressurePoints()));
        }
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Eroded"));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "Eroded", 1));
            }

        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Eroded");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
