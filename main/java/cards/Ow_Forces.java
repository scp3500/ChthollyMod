package cards;

import basemod.abstracts.CustomCard;
import cards.special.Blue_Card;
import cards.special.Green_Card;
import cards.special.Purple_Card;
import cards.special.Red_Card;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.WhiteNoise;
import com.megacrit.cardcrawl.cards.green.Distraction;
import com.megacrit.cardcrawl.cards.purple.ForeignInfluence;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;

import java.util.ArrayList;

public class Ow_Forces extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Ow_Forces");
    public static final String ID = "Ow_Forces";

    public Ow_Forces() {
        super(ID, cardStrings.NAME, "img/cards_Seles/Ow_Forces.png", 1, cardStrings.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Chtho_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new Red_Card());
        stanceChoices.add(new Green_Card());
        stanceChoices.add(new Blue_Card());
        stanceChoices.add(new Purple_Card());
        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
    }

    public void getCard(AbstractCard c) {
        c.setCostForTurn(0);
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, true));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Ow_Forces();
    }
}

