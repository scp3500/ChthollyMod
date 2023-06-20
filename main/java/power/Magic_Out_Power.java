package power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Magic_Out_Power extends AbstractPower {
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Magic_Out");
    public static final String POWER_ID = "Magic_Out";
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Magic_Out_Power(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Magic_Out";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("berserk");
    }

    public void updateDescription() {
        //1.表示能量叠加
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[3]);
        for (int i = 0; i < this.amount; i++) {
            sb.append("[R] ");
        }
        sb.append(LocalizedStrings.PERIOD);
        String str1 = sb.toString();
        //2.表示拼接
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + str1;
    }

    public void atStartOfTurn() {
        flash();
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new Lose_Memory_Power((AbstractCreature)this.owner, this.amount), this.amount));
        addToBot((AbstractGameAction)new DrawCardAction(this.owner, this.amount));
        addToBot((AbstractGameAction) new GainEnergyAction(this.amount));
    }
}
