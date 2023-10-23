package potions;

import cards.special.Get_Memory;
import cards.special.Lose_Memory;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;

public class TrendsPotion extends AbstractPotion{
    public static final String POTION_ID = "TrendsPotion";
    private static final PotionStrings potionStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public TrendsPotion() {
        super(NAME, "TrendsPotion", AbstractPotion.PotionRarity.COMMON, PotionSize.HEART, PotionColor.WEAK);
        this.potency = this.getPotency();
        this.isThrown = false;
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[1];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }
    @Override
    public void use(AbstractCreature abstractCreature) {
        InputHelper.moveCursorToNeutralPosition();
        ArrayList<AbstractCard> stanceChoices = new ArrayList();
        stanceChoices.add(new Get_Memory());
        stanceChoices.add(new Lose_Memory());
        this.addToBot(new ChooseOneAction(stanceChoices));
    }

    @Override
    public int getPotency(int i) {
        return 0;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new TrendsPotion();
    }
    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString("TrendsPotion");
        NAME = potionStrings.NAME;
        DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    }
}
