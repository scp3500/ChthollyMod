package cards.special;

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
import power.Lose_Memory_Power;

public class Lose_Memory extends CustomCard {
    public static final String ID = "Lose_Memory";
    private static final CardStrings cardStrings;
    public static final int NUM1 = 4;
    public static final int NUM2 = 8;

    public Lose_Memory() {
        super("Get_Memory", cardStrings.NAME, "img/cards_Seles/Lose_Memory.png", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.STATUS, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.hasRelic("SacredBark")) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Lose_Memory_Power((AbstractCreature) p, NUM2), NUM2));
        } else {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Lose_Memory_Power((AbstractCreature) p, NUM1), NUM1));
        }
    }

    public void upgrade() {
    }

    public AbstractCard makeCopy() {
        return new Lose_Memory();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Lose_Memory");
    }
}
