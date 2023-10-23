package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import util.Cht_Helper;

import java.util.ArrayList;
import java.util.Iterator;

public class Grimoire extends CustomRelic {
    public static final String ID = "Grimoire";
    private static final String IMG = "img/relics_Seles/Grimoire.png";
    private static final String IMG_OTL = "img/relics_Seles/outline/Grimoire.png";
    private boolean cardsSelected = true;

    private static final int NUM = 2;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public Grimoire() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.cardsSelected = false;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard card : AbstractDungeon.player.masterDeck.getPurgeableCards().group) {
            if (card.type != AbstractCard.CardType.CURSE) {
                tmp.addToTop(card);
            }
        }

        if (tmp.group.isEmpty()) {
            this.cardsSelected = true;
        } else {
            if (tmp.group.size() <= NUM) {
                this.giveCards(tmp.group);
            } else if (!AbstractDungeon.isScreenUp) {
                AbstractDungeon.gridSelectScreen.open(tmp, NUM, this.DESCRIPTIONS[1] + this.name + LocalizedStrings.PERIOD, false, false, false, false);
            } else {
                AbstractDungeon.dynamicBanner.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.screen;
                AbstractDungeon.gridSelectScreen.open(tmp, NUM, this.DESCRIPTIONS[1] + this.name + LocalizedStrings.PERIOD, false, false, false, false);
            }

        }
    }

    public void update() {
        super.update();
        if (!this.cardsSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == NUM) {
            this.giveCards(AbstractDungeon.gridSelectScreen.selectedCards);
        }

    }

    public void giveCards(ArrayList<AbstractCard> group) {
        this.cardsSelected = true;
        float displayCount = 0.0F;
        Iterator<AbstractCard> i = group.iterator();

        while(i.hasNext()) {
            AbstractCard card = (AbstractCard)i.next();
            card.untip();
            card.unhover();
            AbstractDungeon.player.masterDeck.removeCard(card);
            AbstractDungeon.transformedCard = Cht_Helper.getMagicCard().makeCopy();
            if (AbstractDungeon.transformedCard.canUpgrade()) {
                AbstractDungeon.transformedCard.upgrade();
            }
            if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.TRANSFORM && AbstractDungeon.transformedCard != null) {
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(AbstractDungeon.getTransformedCard(), (float) Settings.WIDTH / 3.0F + displayCount, (float)Settings.HEIGHT / 2.0F, false));
                displayCount += (float)Settings.WIDTH / 6.0F;
            }
        }

        AbstractDungeon.gridSelectScreen.selectedCards.clear();
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new Grimoire();
    }
}
