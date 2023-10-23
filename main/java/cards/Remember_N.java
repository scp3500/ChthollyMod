package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Remember_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Remember_N");
    public static final String ID = "Remember_N";

    public Remember_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Remember_N.png", 1, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 0;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.getPower("Lose_Memory") != null) {
            this.block = p.getPower("Lose_Memory").amount * this.magicNumber;
        }
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) p, (AbstractCreature) p, this.block));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Remember_N();
    }
}

