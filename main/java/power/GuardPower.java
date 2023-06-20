//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package power;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GuardPower extends AbstractPower {
    public static final String POWER_ID = "Guard";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public GuardPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Guard";
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("noDraw");
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Guard");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
