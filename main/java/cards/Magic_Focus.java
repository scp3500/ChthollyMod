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
import patches_cht.CardTagEnum;
import power.Lose_Memory_Power;
import power.Magic_FocusPower;

public class Magic_Focus extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Focus");
    public static final String ID = "Magic_Focus";

    public Magic_Focus() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Focus.png", 1, cardStrings.DESCRIPTION, CardType.POWER, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new SavePower();
        this.tags.add(CardTagEnum.Magic);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new Magic_FocusPower(p, 1), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Lose_Memory_Power((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Magic_Focus();
    }
}

