package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import patches_cht.AbstractCardEnum;

public class Slash extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Slash");
    public static final String ID = "Slash";

    public Slash() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Slash.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard) new Slash();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeMagicNumber(1);
        }
    }
}
