//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UnforgettablePower extends AbstractPower {
    public static final String POWER_ID = "Unforgettable";
    private static final PowerStrings powerStrings;

    public UnforgettablePower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = "Unforgettable";
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = 1;
        this.updateDescription();
        this.loadRegion("noPain");
        GameActionManager var10000 = AbstractDungeon.actionManager;
        var10000.mantraGained += amount;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount * 4 + powerStrings.DESCRIPTIONS[1];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Unforgettable");
    }
}
