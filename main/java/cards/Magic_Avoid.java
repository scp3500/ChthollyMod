package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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

public class Magic_Avoid extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Avoid");
    public static final String ID = "Magic_Avoid";

    public Magic_Avoid() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Avoid.png", 2, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 10;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagEnum.Magic);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) p, (AbstractCreature) p, this.block));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Lose_Memory_Power((AbstractCreature)p, 2), 2));
        addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, this.magicNumber));
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
        return new Magic_Avoid();
    }
}

