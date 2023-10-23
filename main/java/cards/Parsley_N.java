package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import patches_cht.AbstractCardEnum;

public class Parsley_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Parsley_N");
    public static final String ID = "Parsley_N";

    public Parsley_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Parsley_N.png", 1, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(AbstractCard.CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Parsley_N();
    }
}

