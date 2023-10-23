package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches_cht.AbstractCardEnum;
import power.Near_Death_Power;

public class Endure_Eve extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Endure_Eve");
    public static final String ID = "Endure_Eve";

    public Endure_Eve() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Endure_Eve.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Near_Death_Power((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Endure_Eve();
    }
}

