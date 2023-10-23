package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import patches_cht.AbstractCardEnum;

public class Tenacity_N extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Tenacity_N");
    public static final String ID = "Tenacity_N";

    public Tenacity_N() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Tenacity_N.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 15;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
        }
        addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, 1));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        boolean flag = false;
        if (!canUse) {
            return false;
        }
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            canUse = false;
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        }

        return canUse;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Tenacity_N();
    }
}
