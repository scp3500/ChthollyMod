package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches_cht.AbstractCardEnum;
import power.FlightPower_New;

public class Wings_Of_Fairy extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Wings_Of_Fairy");
    public static final String ID = "Wings_Of_Fairy";
    public int turnAmount = 0;

    public Wings_Of_Fairy() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Wings_Of_Fairy.png", 2, cardStrings.DESCRIPTION, CardType.POWER, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new FlightPower_New((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Wings_Of_Fairy();
    }
}

