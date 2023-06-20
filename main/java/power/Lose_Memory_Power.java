package power;

import characters.seles;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class Lose_Memory_Power extends AbstractPower {
    public static final String POWER_ID = "Lose_Memory";
    private static final PowerStrings powerStrings;
    private static final int GET_POWER = 2;

    public static int amount_N;

    public Lose_Memory_Power(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Lose_Memory";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("mantra");
        this.type = PowerType.DEBUFF;
        GameActionManager var10000 = AbstractDungeon.actionManager;
        var10000.mantraGained += amount;
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + powerStrings.DESCRIPTIONS[1];
    }

    public void stackPower(int stackAmount) {
        //super.stackPower(stackAmount);
        if (this.amount == -1) {
            System.out.println("000");
        } else {
            this.fontScale = 8.0F;
            //判断每次获取失忆时获取格挡
            if (this.owner.getPower("Unforgettable") != null && stackAmount > 0) {
                this.owner.getPower("Unforgettable").flash();
                this.addToBot(new GainBlockAction(this.owner, 4 * this.owner.getPower("Unforgettable").amount, Settings.FAST_MODE));
            }
            //表示不获得失忆
            if (this.owner.getPower("Guard") != null && stackAmount > 0) {
                this.owner.getPower("Guard").flash();
                this.owner.gainGold(stackAmount);
                AbstractDungeon.effectList.add(new RainingGoldEffect(stackAmount * 2, true));
            }
            //表示获得失忆
            else {
                this.amount += stackAmount;
            }
        }
        amount_N += stackAmount;
        if ((this.amount >= 0 && this.amount < 5) || AbstractDungeon.player.getPower("Lose_Memory") == null) {
            AbstractDungeon.player.img = ImageMaster.loadImage(seles.SELES_STAND);
        }
        if (this.amount == 5) {
            AbstractDungeon.player.img = ImageMaster.loadImage(seles.SELES_STAND_Mid);
        }
        if (this.amount > 5 && this.amount < 10) {
            AbstractDungeon.player.img = ImageMaster.loadImage(seles.SELES_STAND_Mid);
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this.owner, (AbstractCreature) this.owner, new StrengthPower((AbstractCreature) this.owner, GET_POWER), GET_POWER));
        }
        if (this.amount >= 10) {
            AbstractDungeon.player.img = ImageMaster.loadImage(seles.SELES_STAND_End);
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
            //addToBot((AbstractGameAction) new LoseHPAction(this.owner, this.owner, 99999));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this.owner, (AbstractCreature) this.owner, (AbstractPower) new EndTurnDeathPower_New((AbstractCreature) this.owner)));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this.owner, (AbstractCreature) this.owner, new StrengthPower((AbstractCreature) this.owner, GET_POWER), GET_POWER));
            //addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "Lose_Memory"));
            if (this.amount != 10) {
                this.amount -= 10;
            }
            if (this.amount < 0) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Lose_Memory"));
                AbstractDungeon.player.img = ImageMaster.loadImage(seles.SELES_STAND);
            }
        }
        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Lose_Memory"));
            AbstractDungeon.player.img = ImageMaster.loadImage(seles.SELES_STAND);
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Lose_Memory");
    }
}

