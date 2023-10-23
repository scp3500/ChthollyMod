package relics;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import power.Lose_Memory_Power;

/**
 * 创建人:???
 * 创建时间:2023/8/27 22:39
 * 备注: 初始遗物（面向对象入门）
 */
public class cLanguageProgramBegin extends CustomRelic {
    public static final String ID = "cLanguageProgramBegin";
    private static final String IMG = "img/relics_Seles/cLanguageProgramBegin.png";
    private static final String IMG_OTL = "img/relics_Seles/outline/cLanguageProgramBegin.png";

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public cLanguageProgramBegin() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, AbstractRelic.LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        //在战斗开始时触发
        this.counter = 0;
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new Lose_Memory_Power((AbstractCreature)AbstractDungeon.player, 2), 2));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //在用户使用牌时触发
        if (card.type == AbstractCard.CardType.SKILL) {
            this.counter++;
            if (this.counter % 3 == 0) {
                //如果是3的倍数，counter=0和获得5点格挡
                this.counter = 0;
                flash();
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 3));
            }
        }
    }

    @Override
    public void onVictory() {
        //在胜利时触发
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new cLanguageProgramBegin();
    }
}
