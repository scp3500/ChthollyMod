package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches_cht.AbstractCardEnum;
import patches_cht.CardTagEnum;
import power.Lose_Memory_Power;

public class Magic_Overflow extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Overflow");
    public static final String ID = "Magic_Overflow";
    public static final int GAIN = 1;

    public Magic_Overflow() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Overflow.png", 0, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTagEnum.Magic);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new GainEnergyAction(GAIN));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Lose_Memory_Power((AbstractCreature)p, this.magicNumber), this.magicNumber));
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
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
        return new Magic_Overflow();
    }
}


