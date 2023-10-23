package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches_cht.AbstractCardEnum;
import power.Lose_Memory_Power;

public class Reminiscing_Past extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Reminiscing_Past");
    public static final String ID = "Reminiscing_Past";

    public Reminiscing_Past() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Reminiscing_Past.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 9;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (p.getPower("Lose_Memory") != null && p.getPower("Lose_Memory").amount <= this.magicNumber) {
            this.addToTop(new RemoveSpecificPowerAction(p, p, "Lose_Memory"));
        }else {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Lose_Memory_Power((AbstractCreature) p, -this.magicNumber), -this.magicNumber));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Reminiscing_Past();
    }
}

