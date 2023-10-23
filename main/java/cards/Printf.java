package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches_cht.AbstractCardEnum;
public class Printf extends CustomCard {
    //从.json文件中提取键名为Strike_Seles的信息
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Printf");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Seles/Printf.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 14;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "Printf";
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Printf() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Chtho_COLOR, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        //添加基础攻击标签和将伤害设为8
        this.baseDamage = ATTACK_DMG;
        this.cardsToPreview = (AbstractCard)new SavePower();
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new SavePower(), 1));
    }

    @Override

    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Printf();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高6点伤害
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
