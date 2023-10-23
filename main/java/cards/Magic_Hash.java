package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import patches_cht.AbstractCardEnum;
import patches_cht.CardTagEnum;

public class Magic_Hash extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Hash");
    public static final String ID = "Magic_Hash";

    public Magic_Hash() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Hash.png", 1, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagEnum.Magic);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, AbstractDungeon.player, (AbstractPower)new VulnerablePower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Magic_Hash();
    }
}

