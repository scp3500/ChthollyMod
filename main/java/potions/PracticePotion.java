package potions;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class PracticePotion extends AbstractPotion {
    public static final String POTION_ID = "PracticePotion";
    private static final PotionStrings potionStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private AbstractCard theCard = null;

    public PracticePotion() {
        super(NAME, "PracticePotion", PotionRarity.UNCOMMON, PotionSize.ANVIL, PotionColor.FRUIT);
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
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            upgradeCard(2);
        } else {
            upgradeCard(1);
        }
    }

    private void upgradeCard(int amount) {
        for (int i = 0; i < amount; i++) {
            ArrayList<AbstractCard> possibleCards = new ArrayList();
            Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();
                if (c.canUpgrade()) {
                    possibleCards.add(c);
                }
            }
            if (!possibleCards.isEmpty()) {
                this.theCard = (AbstractCard)possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
                this.theCard.upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(this.theCard);
            }
            if (this.theCard != null) {
                AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy()));
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            }
        }
    }

    public boolean canUse() {
        if (AbstractDungeon.actionManager.turnHasEnded && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            return false;
        } else {
            return AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain);
        }
    }

    @Override
    public int getPotency(int i) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new PracticePotion();
    }
    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString("PracticePotion");
        NAME = potionStrings.NAME;
        DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    }
}
