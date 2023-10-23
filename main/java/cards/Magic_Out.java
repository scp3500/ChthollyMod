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
import power.Magic_Out_Power;

public class Magic_Out extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Out");
    public static final String ID = "Magic_Out";

    public Magic_Out() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Out.png", 1, cardStrings.DESCRIPTION, CardType.POWER, AbstractCardEnum.Chtho_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagEnum.Magic);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Magic_Out_Power((AbstractCreature) p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard) new Magic_Out();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
