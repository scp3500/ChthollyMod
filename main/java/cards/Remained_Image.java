package cards;

import action.DamagePerSkillPlayedAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Remained_Image extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Remained_Image");
    public static final String ID = "Remained_Image";

    public Remained_Image() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Remained_Image.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new DamagePerSkillPlayedAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();

        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.type == CardType.SKILL) {
                count++;
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;

        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }


    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Remained_Image();
    }
}

