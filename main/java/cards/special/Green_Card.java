package cards.special;

import action.DiscoveryAction_N;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import potions.MagicPotion;
import power.Lose_Memory_Power;

public class Green_Card extends CustomCard {
    public static final String ID = "Green_Card";
    private static final CardStrings cardStrings;

    public Green_Card() {
        super("Green_Card", cardStrings.NAME, "img/cards_Seles/Green_Card.png", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.STATUS, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void onChoseThisOption() {
        this.addToBot(new DiscoveryAction_N(1, 2));
    }

    public void upgrade() {
    }

    public AbstractCard makeCopy() {
        return new Green_Card();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Green_Card");
    }
}
