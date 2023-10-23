//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.RedCards;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import util.Cht_Helper;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class DiscoveryAction_N extends AbstractGameAction {
    private boolean retrieveCard = false;
    public int type;

    public DiscoveryAction_N(int amount, int type) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.type = type;
    }

    public void update() {
        ArrayList<AbstractCard> generatedCards;
        if (type == 0) {
            generatedCards = getMagicCard();
        } else if (type == 1) {
            generatedCards = getRedCard();
        } else if (type == 2) {
            generatedCards = getGreenCard();
        } else if (type == 3) {
            generatedCards = getBlueCard();
        } else {
            generatedCards = getPurpleCard();
        }
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], true);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    AbstractCard disCard2 = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        disCard.upgrade();
                        disCard2.upgrade();
                    }

                    disCard.setCostForTurn(0);
                    disCard2.setCostForTurn(0);
                    disCard.current_x = -1000.0F * Settings.xScale;
                    disCard2.current_x = -1000.0F * Settings.xScale + AbstractCard.IMG_HEIGHT_S;
                    if (this.amount == 1) {
                        if (AbstractDungeon.player.hand.size() < 10) {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        } else {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        }

                        disCard2 = null;
                    } else if (AbstractDungeon.player.hand.size() + this.amount <= 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard2, (float) Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    } else if (AbstractDungeon.player.hand.size() == 9) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, (float) Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, (float) Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }

    private ArrayList<AbstractCard> getMagicCard() {
        ArrayList<AbstractCard> generatedCards = new ArrayList<>();
        int count = 0;
        while (count < 3) {
            AbstractCard c = Cht_Helper.getMagicCard();
            if (!generatedCards.contains(c)) {
                generatedCards.add(c);
                count++;
            }
        }
        return generatedCards;
    }

    private ArrayList<AbstractCard> getRedCard() {
        ArrayList<AbstractCard> generatedCards = new ArrayList<>();
        int count = 0;
        while (count < 3) {
            AbstractCard c = Cht_Helper.getRedCard();
            if (!generatedCards.contains(c)) {
                generatedCards.add(c);
                count++;
            }
        }
        return generatedCards;
    }

    private ArrayList<AbstractCard> getPurpleCard() {
        ArrayList<AbstractCard> generatedCards = new ArrayList<>();
        int count = 0;
        while (count < 3) {
            AbstractCard c = Cht_Helper.getPurpleCard();
            if (!generatedCards.contains(c)) {
                generatedCards.add(c);
                count++;
            }
        }
        return generatedCards;
    }

    private ArrayList<AbstractCard> getBlueCard() {
        ArrayList<AbstractCard> generatedCards = new ArrayList<>();
        int count = 0;
        while (count < 3) {
            AbstractCard c = Cht_Helper.getBlueCard();
            if (!generatedCards.contains(c)) {
                generatedCards.add(c);
                count++;
            }
        }
        return generatedCards;
    }

    private ArrayList<AbstractCard> getGreenCard() {
        ArrayList<AbstractCard> generatedCards = new ArrayList<>();
        int count = 0;
        while (count < 3) {
            AbstractCard c = Cht_Helper.getGreenCard();
            if (!generatedCards.contains(c)) {
                generatedCards.add(c);
                count++;
            }
        }
        return generatedCards;
    }
}
