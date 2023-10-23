package cards;

import action.Many_LivesAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Many_Lives extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Many_Lives");
    public static final String ID = "Many_Lives";

    public Many_Lives() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Many_Lives.png", 1, cardStrings.DESCRIPTION, CardType.POWER, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new Many_LivesAction(this.upgraded));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Many_Lives();
    }
}

