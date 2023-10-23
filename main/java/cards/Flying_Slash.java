package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;
import patches_cht.AbstractCardEnum;

public class Flying_Slash extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Flying_Slash");
    public static final String ID = "Flying_Slash";

    public Flying_Slash() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Flying_Slash.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 4;
        this.baseBlock = 4;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) p, (AbstractCreature) p, this.block));
        addToBot((AbstractGameAction) new WaitAction(0.1F));
        if (p != null && m != null) {
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
        }
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeBlock(1);
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Flying_Slash();
    }
}
