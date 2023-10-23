package potions;
import action.DiscoveryAction_N;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class MagicPotion extends AbstractPotion {
    public static final String POTION_ID = "MagicPotion";
    private static final PotionStrings potionStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public MagicPotion() {
        super(NAME, "MagicPotion", PotionRarity.RARE, PotionSize.EYE, PotionEffect.RAINBOW, Color.WHITE, (Color)null, (Color)null);
        this.potency = this.getPotency();
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
        /*if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            getCard(Cht_Helper.getMagicCard());
            getCard(Cht_Helper.getMagicCard());
        } else {
            getCard(Cht_Helper.getMagicCard());
        }*/
        this.addToBot(new DiscoveryAction_N(this.potency, 0));
    }

    @Override
    public int getPotency(int i) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new MagicPotion();
    }

    public void getCard(AbstractCard c) {
        c.setCostForTurn(0);
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, true));
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString("MagicPotion");
        NAME = potionStrings.NAME;
        DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    }
}
