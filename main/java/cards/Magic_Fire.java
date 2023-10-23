package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import patches_cht.AbstractCardEnum;
import patches_cht.CardTagEnum;

public class Magic_Fire extends CustomCard {
    public static final String ID = "Magic_Fire";
    private static final CardStrings cardStrings;

    public Magic_Fire() {
        this(0);
    }

    public Magic_Fire(int upgrades) {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Fire.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.tags.add(CardTagEnum.Magic);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2F));
        }

        for (int i = 0; i < this.magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    public void upgrade() {
            this.upgradeMagicNumber(1);
            this.timesUpgraded++;
            this.upgraded = true;
            this.name = cardStrings.NAME + "+" + this.timesUpgraded;
            this.initializeTitle();
    }

    public boolean canUpgrade() {
        return true;
    }

    public AbstractCard makeCopy() {
        return new Magic_Fire(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Fire");
    }
}

