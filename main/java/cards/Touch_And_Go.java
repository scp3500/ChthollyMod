package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Touch_And_Go extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Touch_And_Go");
    public static final String ID = "Touch_And_Go";

    public Touch_And_Go() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Touch_And_Go.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && m.getIntentBaseDmg() >= 0) {
            for (int i = 0; i < this.magicNumber + 1; i++) {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        } else {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard) new Touch_And_Go();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}
