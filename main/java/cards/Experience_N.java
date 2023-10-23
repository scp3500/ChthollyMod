package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Experience_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Experience_N");
    public static final String ID = "Experience_N";

    public Experience_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Experience_N.png", 2, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new DiscardAction(p, p, p.hand.size(), false));
        this.addToBot(new BetterDrawPileToHandAction(this.magicNumber));
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
        return new Experience_N();
    }
}

