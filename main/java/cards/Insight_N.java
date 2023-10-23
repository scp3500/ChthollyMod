package cards;

import action.ExhaustAction_N;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import patches_cht.AbstractCardEnum;

import java.util.Random;

public class Insight_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Insight_N");
    public static final String ID = "Insight_N";

    public Insight_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Insight_N.png", 3, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.block = this.baseBlock;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) p, (AbstractCreature) p, this.block));
    }

    public void tookDamage() {
        this.updateCost(-1);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        AbstractCard tmp = new Insight_N();
        if (AbstractDungeon.player != null) {
            tmp.updateCost(-AbstractDungeon.player.damagedThisCombat);
        }

        return tmp;
    }
}

