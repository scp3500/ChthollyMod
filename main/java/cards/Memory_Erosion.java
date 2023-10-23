package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches_cht.AbstractCardEnum;
import power.Lose_Memory_Power;

import java.util.ArrayList;

public class Memory_Erosion extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Memory_Erosion");
    public static final String ID = "Memory_Erosion";

    public Memory_Erosion() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Memory_Erosion.png", 1, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Lose_Memory_Power((AbstractCreature)p, 1), 1));
        //addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new ErodedPower((AbstractCreature)m, p, this.magicNumber), this.magicNumber));
        if (p.powers != null) {
            removePower(p);
            removePower(m);
        }
    }

    public void removePower(AbstractCreature c) {
        ArrayList<AbstractPower> powers = c.powers;
        int rng = AbstractDungeon.cardRandomRng.random(powers.size() - 1);
        String powerId = powers.get(rng).ID;
        this.addToBot(new RemoveSpecificPowerAction(c, c, powerId));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Memory_Erosion();
    }
}

