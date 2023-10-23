package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Crystal_Brooch extends CustomRelic {
    public static final String ID = "Crystal_Brooch";
    private static final String IMG = "img/relics_Seles/Crystal_Brooch.png";
    private static final String IMG_OTL = "img/relics_Seles/outline/Crystal_Brooch.png";

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public Crystal_Brooch() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
    }

    @Override
    public void onVictory() {
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new Crystal_Brooch();
    }
}
