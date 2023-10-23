package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;
import util.Cht_Helper;

import java.util.ArrayList;

public class Scarborough_Fair extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Scarborough_Fair");
    public static final String ID = "Scarborough_Fair";

    public Scarborough_Fair() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Scarborough_Fair.png", 2, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = (AbstractCard)new Parsley_N();
        this.cardsToPreview = (AbstractCard)new Sage_N();
        this.cardsToPreview = (AbstractCard)new Rosemary_N();
        this.cardsToPreview = (AbstractCard)new Thyme_N();
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new Parsley_N());
        stanceChoices.add(new Sage_N());
        stanceChoices.add(new Rosemary_N());
        stanceChoices.add(new Thyme_N());
        if (this.upgraded) {
            for (AbstractCard c : stanceChoices) {
                c.upgrade();
            }
        }
        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
        if (!Cht_Helper.isPlay) {
            CardCrawlGame.sound.playA("SCAR", 0.0F);
        }
        Cht_Helper.isPlay = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyPowers() {}

    public void calculateCardDamage(AbstractMonster mo) {}


    public AbstractCard makeCopy() {
        return new Scarborough_Fair();
    }
}

