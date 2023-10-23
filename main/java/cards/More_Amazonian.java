package cards;

import action.ModifyDamageTimesAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class More_Amazonian extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("More_Amazonian");
    public static final String ID = "More_Amazonian";

    public More_Amazonian() {
        super(ID, cardStrings.NAME, "img/cards_Seles/More_Amazonian.png", 2, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot((AbstractGameAction)new ModifyDamageTimesAction(this.uuid, 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new More_Amazonian();
    }
}

