package cards;

import action.Confidence_NAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Confidence_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Confidence_N");
    public static final String ID = "Confidence_N";

    public Confidence_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Confidence_N.png", 0, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(1, new Confidence_NAction(this.block)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Confidence_N();
    }
}

