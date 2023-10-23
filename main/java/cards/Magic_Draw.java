package cards;

import action.Magic_DrawAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;
import patches_cht.CardTagEnum;

public class Magic_Draw extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magic_Draw");
    public static final String ID = "Magic_Draw";

    public Magic_Draw() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Magic_Draw.png", 2, cardStrings.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 14;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
        this.tags.add(CardTagEnum.Magic);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new Magic_DrawAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Magic_Draw();
    }
}

