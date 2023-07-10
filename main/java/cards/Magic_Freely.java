package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import pathes.AbstractCardEnum;
import pathes.CardTagEnum;

public class Magic_Freely extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Freely");
    public static final String ID = "Magic_Freely";
    public int num1 = 1;
    public int num2 = 2;

    public Magic_Freely() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Freely.png", 0, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Seles_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagEnum.Magic);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            this.num1 = 2;
            this.num2 = 1;
        }
        if (p.getPower("Lose_Memory") == null) {
            applyEffect(this.num1,m);
        }
        if (p.getPower("Lose_Memory") != null && p.getPower("Lose_Memory").amount % 2 == 0) {
            applyEffect(this.num1,m);
        }
        if (p.getPower("Lose_Memory") != null && p.getPower("Lose_Memory").amount % 2 == 1) {
            applyEffect(this.num2,m);
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyEffect(int num, AbstractMonster m) {
        if (m != null) {
            if (num == 1) {
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
            }
            if (num == 2) {
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, AbstractDungeon.player, (AbstractPower)new VulnerablePower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Magic_Freely();
    }
}

