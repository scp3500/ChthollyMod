package chthollyMod;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import cards.special.Get_Memory;
import cards.special.Lose_Memory;
import characters.seles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patches_cht.AbstractCardEnum;
import patches_cht.ThmodClassEnum;
import potions.MagicPotion;
import potions.PracticePotion;
import potions.TrendsPotion;
import relics.C_Plus;
import relics.Crystal_Brooch;
import relics.Grimoire;
import relics.cLanguageProgramBegin;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

@SpireInitializer
public class selesMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber {
    private static final String MOD_BADGE = "img/UI_Seles/badge.png";
    public static final Logger logger = LogManager.getLogger(selesMod.class.getName());
    //攻击、技能、能力牌的背景图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_SELES_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_SELES_s.png";
    private static final String POWER_CC = "img/512/bg_power_SELES_s.png";
    private static final String ENERGY_ORB_CC = "img/512/SELESOrb.png";
    //攻击、技能、能力牌的背景图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_SELES.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_SELES.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_SELES.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/SELESOrb.png";
    public static final String CARD_ENERGY_ORB = "img/UI_Seles/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/SelesButton.png";
    private static final String MARISA_PORTRAIT = "img/charSelect/SelesPortrait.png";
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public selesMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe((ISubscriber) this);
        BaseMod.addColor(AbstractCardEnum.Chtho_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, ATTACK_CC, SKILL_CC, POWER_CC, ENERGY_ORB_CC, ATTACK_CC_PORTRAIT, SKILL_CC_PORTRAIT, POWER_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
        BaseMod.addCharacter((AbstractPlayer) new seles("Seles"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT, ThmodClassEnum.Seles_CLASS);
    }

    //初始化整个MOD,一定不能删
    public static void initialize() {
        new selesMod();
    }

    @Override
    public void receiveEditCards() {
        //将卡牌批量添加
        loadCardsToAdd();
        Iterator<AbstractCard> var1 = this.cardsToAdd.iterator();
        while (var1.hasNext()) {
            AbstractCard card = var1.next();
            BaseMod.addCard(card);
        }
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {

    }


    @Override
    public void receivePowersModified() {
    }


    @Override
    public void receivePostDungeonInitialize() {
    }


    @Override
    public void receivePostDraw(AbstractCard arg0) {
    }

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveEditKeywords() {
        /*//读取关键字的JSON文本
        String keyword = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            keyword = "localization/ThMod_Seles_keywords-zh.json";
        } else {
            //其他语言配置的JSON
        }
        String keywordStrings = Gdx.files.internal(keyword).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, keywordStrings);*/
        String keywordsPath = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            keywordsPath = "localization/zhs/ThMod_Seles_keywords-zh.json";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            keywordsPath = "localization/zht/ThMod_Seles_keywords-zht.json";
        } else {
            //其他语言配置的JSON
            keywordsPath = "localization/eng/keyword.json";
        }

        Gson gson = new Gson();
        String keywordStrings = Gdx.files.internal(keywordsPath).readString(String.valueOf(StandardCharsets.UTF_8));
        Keywords keywords = (Keywords) gson.fromJson(keywordStrings, Keywords.class);
        Keyword[] var4 = keywords.keywords;
        Keyword[] var6 = var4;
        int var7 = var4.length;

        for (int var8 = 0; var8 < var7; ++var8) {
            Keyword key = var6[var8];
            logger.info("Loading keyword : " + key.NAMES[0]);
            BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
        }

        logger.info("Keywords setting finished.");
    }

    @Override
    public void receiveEditStrings() {
        //读取遗物，卡牌，能力，药水，事件的JSON文本

        String relic = "", card = "", power = "", potion = "", event = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            card = "localization/zhs/ThMod_Seles_cards-zh.json";
            relic = "localization/zhs/ThMod_Seles_relics-zh.json";
            power = "localization/zhs/ThMod_Seles_powers-zh.json";
            potion = "localization/zhs/ThMod_Seles_potions-zh.json";
            //event = "localization/ThMod_YM_events-zh.json";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            card = "localization/zht/ThMod_Seles_cards-zht.json";
            relic = "localization/zht/ThMod_Seles_relics-zht.json";
            power = "localization/zht/ThMod_Seles_powers-zht.json";
            potion = "localization/zht/ThMod_Seles_potions-zht.json";
        } else {
            //其他语言配置的JSON
            //card = "localization/zhs/ThMod_Seles_cards-zh.json";
            card = "localization/eng/card.json";
            relic = "localization/eng/relic.json";
            power = "localization/eng/power.json";
            potion = "localization/eng/potion.json";
        }

        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
//     String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
    }

    private void loadCardsToAdd() {
        //将自定义的卡牌添加到这里
        this.cardsToAdd.clear();
        this.cardsToAdd.add(new Strike_Seles());
        this.cardsToAdd.add(new Defend_Seles());
        this.cardsToAdd.add(new Combo_Attack());
        this.cardsToAdd.add(new Brilliance_N());
        this.cardsToAdd.add(new Practice_Cooking());
        this.cardsToAdd.add(new Slash());
        this.cardsToAdd.add(new Touch_And_Go());
        this.cardsToAdd.add(new Flying_Slash());
        this.cardsToAdd.add(new Insight_N());
        this.cardsToAdd.add(new Adjust_Sword());
        this.cardsToAdd.add(new Get_Energy());
        this.cardsToAdd.add(new On_Rush());
        this.cardsToAdd.add(new More_Amazonian());
        this.cardsToAdd.add(new Fearless_Of_Death());
        this.cardsToAdd.add(new Chop_Of_Death());
        this.cardsToAdd.add(new The_Last_Bullets());
        this.cardsToAdd.add(new Magic_Block());
        this.cardsToAdd.add(new Scarborough_Fair());
        this.cardsToAdd.add(new Parsley_N());
        this.cardsToAdd.add(new Sage_N());
        this.cardsToAdd.add(new Rosemary_N());
        this.cardsToAdd.add(new Thyme_N());
        this.cardsToAdd.add(new SavePower());
        this.cardsToAdd.add(new Printf());
        this.cardsToAdd.add(new Seek_N());
        this.cardsToAdd.add(new Remained_Image());
        this.cardsToAdd.add(new Illusory_Ruckus());
        this.cardsToAdd.add(new Wings_Of_Fairy());
        this.cardsToAdd.add(new Ow_Forces());
        this.cardsToAdd.add(new Endure_Eve());
        this.cardsToAdd.add(new Simplify_Life());
        this.cardsToAdd.add(new Fully_Armed());
        this.cardsToAdd.add(new Tenacity_N());
        this.cardsToAdd.add(new Recall_N());
        this.cardsToAdd.add(new illusion_Body());
        this.cardsToAdd.add(new Relive_N());
        this.cardsToAdd.add(new Magic_Out());
        this.cardsToAdd.add(new Hone_N());
        this.cardsToAdd.add(new The_Happiest());
        this.cardsToAdd.add(new Memory_Erosion());
        this.cardsToAdd.add(new Some_Kicks());
        this.cardsToAdd.add(new Sword_LPT());
        this.cardsToAdd.add(new Remember_N());
        this.cardsToAdd.add(new Selfless_N());
        this.cardsToAdd.add(new Look_Back());
        this.cardsToAdd.add(new Reminiscing_Past());
        this.cardsToAdd.add(new Fight_Battle());
        this.cardsToAdd.add(new Magic_Overflow());
        this.cardsToAdd.add(new Concern_N());
        this.cardsToAdd.add(new Narrow_Escape());
        this.cardsToAdd.add(new Unforgettable_N());
        this.cardsToAdd.add(new Redemption_N());
        this.cardsToAdd.add(new Many_Lives());
        this.cardsToAdd.add(new Avg_Strike());
        this.cardsToAdd.add(new Magic_Increase());
        this.cardsToAdd.add(new Magic_Illusion());
        this.cardsToAdd.add(new Magic_Circle());
        this.cardsToAdd.add(new Magic_Avoid());
        this.cardsToAdd.add(new Magic_Fire());
        this.cardsToAdd.add(new Emergency_Cooking());
        this.cardsToAdd.add(new Magic_Focus());
        this.cardsToAdd.add(new Butter_Cake());
        this.cardsToAdd.add(new Magic_Cover());
        this.cardsToAdd.add(new Spin_Destiny());
        this.cardsToAdd.add(new Magic_Freely());
        this.cardsToAdd.add(new Magic_Flammability());
        this.cardsToAdd.add(new Magic_Boundless());
        this.cardsToAdd.add(new Magic_Hash());
        this.cardsToAdd.add(new Magic_Chain());
        this.cardsToAdd.add(new Alert_N());
        this.cardsToAdd.add(new Stop_N());
        this.cardsToAdd.add(new Confidence_N());
        this.cardsToAdd.add(new Sleep_N());
        this.cardsToAdd.add(new Probe_N());
        this.cardsToAdd.add(new Magic_Draw());
        this.cardsToAdd.add(new Experience_N());
        this.cardsToAdd.add(new Get_Memory());
        this.cardsToAdd.add(new Lose_Memory());
        UnlockTracker.unlockCard("Strike_Seles");
        UnlockTracker.unlockCard("Defend_Seles");
        UnlockTracker.unlockCard("Combo_Attack");
        UnlockTracker.unlockCard("Brilliance_N");
        UnlockTracker.unlockCard("Practice_Cooking");
        UnlockTracker.unlockCard("Slash");
        UnlockTracker.unlockCard("Touch_And_Go");
        UnlockTracker.unlockCard("Flying_Slash");
        UnlockTracker.unlockCard("Insight_N");
        UnlockTracker.unlockCard("Adjust_Sword");
        UnlockTracker.unlockCard("Get_Energy");
        UnlockTracker.unlockCard("On_Rush");
        UnlockTracker.unlockCard("More_Amazonian");
        UnlockTracker.unlockCard("Fearless_Of_Death");
        UnlockTracker.unlockCard("Chop_Of_Death");
        UnlockTracker.unlockCard("The_Last_Bullets");
        UnlockTracker.unlockCard("Magic_Block");
        UnlockTracker.unlockCard("Scarborough_Fair");
        UnlockTracker.unlockCard("Parsley_N");
        UnlockTracker.unlockCard("Sage_N");
        UnlockTracker.unlockCard("Rosemary_N");
        UnlockTracker.unlockCard("Thyme_N");
        UnlockTracker.unlockCard("SavePower");
        UnlockTracker.unlockCard("Printf");
        UnlockTracker.unlockCard("Seek_N");
        UnlockTracker.unlockCard("Remained_Image");
        UnlockTracker.unlockCard("Illusory_Ruckus");
        UnlockTracker.unlockCard("Wings_Of_Fairy");
        UnlockTracker.unlockCard("Ow_Forces");
        UnlockTracker.unlockCard("Endure_Eve");
        UnlockTracker.unlockCard("Simplify_Life");
        UnlockTracker.unlockCard("Fully_Armed");
        UnlockTracker.unlockCard("Tenacity_N");
        UnlockTracker.unlockCard("Recall_N");
        UnlockTracker.unlockCard("illusion_Body");
        UnlockTracker.unlockCard("Relive_N");
        UnlockTracker.unlockCard("Magic_Out");
        UnlockTracker.unlockCard("Hone_N");
        UnlockTracker.unlockCard("The_Happiest");
        UnlockTracker.unlockCard("Memory_Erosion");
        UnlockTracker.unlockCard("Some_Kicks");
        UnlockTracker.unlockCard("Sword_LPT");
        UnlockTracker.unlockCard("Remember_N");
        UnlockTracker.unlockCard("Selfless_N");
        UnlockTracker.unlockCard("Look_Back");
        UnlockTracker.unlockCard("Reminiscing_Past");
        UnlockTracker.unlockCard("Fight_Battle");
        UnlockTracker.unlockCard("Magic_Overflow");
        UnlockTracker.unlockCard("Concern_N");
        UnlockTracker.unlockCard("Narrow_Escape");
        UnlockTracker.unlockCard("Unforgettable_N");
        UnlockTracker.unlockCard("Redemption_N");
        UnlockTracker.unlockCard("Many_Lives");
        UnlockTracker.unlockCard("Avg_Strike");
        UnlockTracker.unlockCard("Magic_Increase");
        UnlockTracker.unlockCard("Magic_Illusion");
        UnlockTracker.unlockCard("Magic_Circle");
        UnlockTracker.unlockCard("Magic_Avoid");
        UnlockTracker.unlockCard("Magic_Fire");
        UnlockTracker.unlockCard("Emergency_Cooking");
        UnlockTracker.unlockCard("Magic_Focus");
        UnlockTracker.unlockCard("Butter_Cake");
        UnlockTracker.unlockCard("Magic_Cover");
        UnlockTracker.unlockCard("Spin_Destiny");
        UnlockTracker.unlockCard("Magic_Freely");
        UnlockTracker.unlockCard("Magic_Flammability");
        UnlockTracker.unlockCard("Magic_Boundless");
        UnlockTracker.unlockCard("Magic_Hash");
        UnlockTracker.unlockCard("Magic_Chain");
        UnlockTracker.unlockCard("Alert_N");
        UnlockTracker.unlockCard("Stop_N");
        UnlockTracker.unlockCard("Confidence_N");
        UnlockTracker.unlockCard("Sleep_N");
        UnlockTracker.unlockCard("Probe_N");
        UnlockTracker.unlockCard("Magic_Draw");
        UnlockTracker.unlockCard("Experience_N");
        UnlockTracker.unlockCard("Get_Memory");
        UnlockTracker.unlockCard("Lose_Memory");
    }

    //添加一度
    @Override
    public void receiveEditRelics() {
        //将自定义的遗物添加到这里
        BaseMod.addRelicToCustomPool((AbstractRelic) new cLanguageProgramBegin(), AbstractCardEnum.Chtho_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Crystal_Brooch(), AbstractCardEnum.Chtho_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Grimoire(), AbstractCardEnum.Chtho_COLOR);
        BaseMod.addRelicToCustomPool((AbstractRelic) new C_Plus(), AbstractCardEnum.Chtho_COLOR);
        UnlockTracker.markRelicAsSeen("Crystal_Brooch");
        UnlockTracker.markRelicAsSeen("Grimoire");
        UnlockTracker.markRelicAsSeen("C_Plus");
    }

    public void receiveEditPotions() {
        BaseMod.addPotion(PracticePotion.class, null, null, null, "PracticePotion", ThmodClassEnum.Seles_CLASS);
        BaseMod.addPotion(TrendsPotion.class, null, null, null, "TrendsPotion", ThmodClassEnum.Seles_CLASS);
        BaseMod.addPotion(MagicPotion.class, null, null, null, "MagicPotion", ThmodClassEnum.Seles_CLASS);
    }

    @Override
    public void receiveRelicGet(AbstractRelic relic) {
        //移除遗物,这里移除了小屋子，太垃圾了。

        /*if (AbstractDungeon.player.name == "Seles") {
            AbstractDungeon.shopRelicPool.remove("TinyHouse");
        }*/
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
    }

    @Override
    public void receivePostBattle(AbstractRoom r) {
    }

    @Override
    public void receivePostInitialize() {
        receiveEditPotions();
    }

    @Override
    public void receivePostEnergyRecharge() {
        Iterator<AbstractCard> var1 = recyclecards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }

    class Keywords {
        Keyword[] keywords;

        Keywords() {
        }
    }
}

