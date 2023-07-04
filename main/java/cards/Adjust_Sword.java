package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import pathes.AbstractCardEnum;

public class Adjust_Sword extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Adjust_Sword");
    public static final String ID = "Adjust_Sword";

    public Adjust_Sword() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Adjust_Sword.png", 0, cardStrings.DESCRIPTION, CardType.POWER, AbstractCardEnum.Seles_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = (AbstractCard)new Miracle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new DexterityPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
        addToTop((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Miracle(), 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.cardsToPreview.upgrade();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Adjust_Sword();
    }
}

