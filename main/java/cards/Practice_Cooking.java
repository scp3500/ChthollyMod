package cards;

import action.IncreaseMiscAction_N;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

public class Practice_Cooking extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Practice_Cooking");
    public static final String ID = "Practice_Cooking";

    public Practice_Cooking() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Practice_Cooking.png", 2, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.misc = 4;
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new IncreaseMiscAction_N(this.uuid, this.misc, 1));
        this.magicNumber = this.misc;
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard) new Practice_Cooking();
    }

    public void applyPowers() {
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}
