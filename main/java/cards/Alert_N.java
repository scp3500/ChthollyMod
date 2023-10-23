package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Alert_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Alert_N");
    public static final String ID = "Alert_N";

    public Alert_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Alert_N.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 2;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        if (AbstractDungeon.player.getPower("Lose_Memory") != null) {
            this.baseMagicNumber = AbstractDungeon.player.getPower("Lose_Memory").amount;
        }
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        if (AbstractDungeon.player.getPower("Lose_Memory") != null) {
            this.baseMagicNumber = AbstractDungeon.player.getPower("Lose_Memory").amount;
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        this.calculateCardDamage(m);
        for (int i = 0; i < 3; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Alert_N();
    }
}

