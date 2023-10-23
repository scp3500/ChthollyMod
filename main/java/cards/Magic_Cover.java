package cards;

import action.DrawPerNonAttackAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;
import patches_cht.CardTagEnum;

public class Magic_Cover extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Cover");
    public static final String ID = "Magic_Cover";

    public Magic_Cover() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Cover.png", 2, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagEnum.Magic);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawPerNonAttackAction());
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Magic_Cover();
    }
}

