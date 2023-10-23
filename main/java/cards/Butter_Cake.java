package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches_cht.AbstractCardEnum;

public class Butter_Cake extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Butter_Cake");
    public static final String ID = "Butter_Cake";

    public Butter_Cake() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Butter_Cake.png", 1, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Slimed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("Strength")) {
            int strAmt = p.getPower("Strength").amount;
            this.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, strAmt), strAmt));
            this.addToTop(new ApplyPowerAction(p, p, new LoseStrengthPower(p, strAmt), strAmt));
        }
        this.addToTop(new MakeTempCardInHandAction(new Slimed(), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Butter_Cake();
    }
}


