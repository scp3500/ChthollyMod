package power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class EndTurnDeathPower_New extends AbstractPower {
    public static final String POWER_ID = "EndTurnDeathPower_New";
    private static final PowerStrings powerStrings;

    public EndTurnDeathPower_New(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = "EndTurnDeathPower_New";
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.loadRegion("end_turn_death");
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new VFXAction(new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
        if (this.owner.getPower("The_Happiest") == null) {
            this.addToBot(new LoseHPAction(this.owner, this.owner, 896));
        } else {
            this.addToBot(new LoseHPAction(this.owner, this.owner, 0));
        }
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "EndTurnDeathPower_New"));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("EndTurnDeathPower_New");
    }
}
