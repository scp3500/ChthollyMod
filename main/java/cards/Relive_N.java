package cards;

import basemod.abstracts.CustomCard;
import characters.seles;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

public class Relive_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Relive_N");
    public static final String ID = "Relive_N";

    public Relive_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Relive_N.png", 1, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Seles_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.getPower("Lose_Memory") != null) {
            p.increaseMaxHp(p.getPower("Lose_Memory").amount / 2, false);
            this.addToTop(new RemoveSpecificPowerAction(p, p, "Lose_Memory"));
            p.img = ImageMaster.loadImage(seles.SELES_STAND);
        }
        if (p.getPower("EndTurnDeathPower_New") != null) {
            this.addToTop(new RemoveSpecificPowerAction(p, p, "EndTurnDeathPower_New"));
        }
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
        return new Relive_N();
    }
}
