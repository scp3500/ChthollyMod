package util;

import action.AddRelicAction;
import cards.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

public class Cht_Helper extends AbstractGameAction {
    public static boolean isPlay = false;

    private Cht_Helper() {
    }

    @Override
    public void update() {
    }

    public static AbstractCard getMagicCard() {
        ArrayList<AbstractCard> magic = new ArrayList<>();
        magic.add(new Get_Energy());
        magic.add(new Magic_Avoid());
        magic.add(new Magic_Block());
        magic.add(new Magic_Boundless());
        magic.add(new Magic_Chain());
        magic.add(new Magic_Circle());
        magic.add(new Magic_Cover());
        magic.add(new Magic_Draw());
        magic.add(new Magic_Fire());
        magic.add(new Magic_Flammability());
        magic.add(new Magic_Focus());
        magic.add(new Magic_Freely());
        magic.add(new Magic_Hash());
        magic.add(new Magic_Illusion());
        magic.add(new Magic_Increase());
        magic.add(new Magic_Out());
        magic.add(new Magic_Overflow());
        magic.add(new Magic_Tap());
        int rng = AbstractDungeon.cardRandomRng.random(magic.size() - 1);
        return magic.get(rng);
    }

    public static AbstractCard getRedCard() {
        ArrayList<AbstractCard> redCards = new ArrayList<>();
        redCards.add(new Anger());
        redCards.add(new Armaments());
        redCards.add(new Barricade());
        redCards.add(new Bash());
        redCards.add(new BattleTrance());
        redCards.add(new Berserk());
        redCards.add(new BloodForBlood());
        redCards.add(new Bloodletting());
        redCards.add(new Bludgeon());
        redCards.add(new BodySlam());
        redCards.add(new Brutality());
        redCards.add(new BurningPact());
        redCards.add(new Carnage());
        redCards.add(new Clash());
        redCards.add(new Cleave());
        redCards.add(new Clothesline());
        redCards.add(new Combust());
        redCards.add(new Corruption());
        redCards.add(new DarkEmbrace());
        redCards.add(new Defend_Red());
        redCards.add(new DemonForm());
        redCards.add(new Disarm());
        redCards.add(new DoubleTap());
        redCards.add(new Dropkick());
        redCards.add(new DualWield());
        redCards.add(new Entrench());
        redCards.add(new Evolve());
        redCards.add(new Exhume());
        redCards.add(new Feed());
        redCards.add(new FeelNoPain());
        redCards.add(new FiendFire());
        redCards.add(new FireBreathing());
        redCards.add(new FlameBarrier());
        redCards.add(new Flex());
        redCards.add(new GhostlyArmor());
        redCards.add(new Havoc());
        redCards.add(new Headbutt());
        redCards.add(new HeavyBlade());
        redCards.add(new Hemokinesis());
        redCards.add(new Immolate());
        redCards.add(new Impervious());
        redCards.add(new InfernalBlade());
        redCards.add(new Inflame());
        redCards.add(new Intimidate());
        redCards.add(new IronWave());
        redCards.add(new Juggernaut());
        redCards.add(new LimitBreak());
        redCards.add(new Metallicize());
        redCards.add(new Offering());
        redCards.add(new PerfectedStrike());
        redCards.add(new PommelStrike());
        redCards.add(new PowerThrough());
        redCards.add(new Pummel());
        redCards.add(new Rage());
        redCards.add(new Rampage());
        redCards.add(new Reaper());
        redCards.add(new RecklessCharge());
        redCards.add(new Rupture());
        redCards.add(new SearingBlow());
        redCards.add(new SecondWind());
        redCards.add(new SeeingRed());
        redCards.add(new Sentinel());
        redCards.add(new SeverSoul());
        redCards.add(new Shockwave());
        redCards.add(new ShrugItOff());
        redCards.add(new SpotWeakness());
        redCards.add(new Strike_Red());
        redCards.add(new SwordBoomerang());
        redCards.add(new ThunderClap());
        redCards.add(new TrueGrit());
        redCards.add(new TwinStrike());
        redCards.add(new Uppercut());
        redCards.add(new Warcry());
        redCards.add(new Whirlwind());
        redCards.add(new WildStrike());
        int rng = AbstractDungeon.cardRandomRng.random(redCards.size() - 1);
        return redCards.get(rng);
    }

    public static AbstractCard getGreenCard() {
        ArrayList<AbstractCard> greenCards = new ArrayList<>();
        greenCards.add(new Accuracy());
        greenCards.add(new Acrobatics());
        greenCards.add(new Adrenaline());
        greenCards.add(new AfterImage());
        greenCards.add(new Alchemize());
        greenCards.add(new AllOutAttack());
        greenCards.add(new AThousandCuts());
        greenCards.add(new Backflip());
        greenCards.add(new Backstab());
        greenCards.add(new Bane());
        greenCards.add(new BladeDance());
        greenCards.add(new Blur());
        greenCards.add(new BouncingFlask());
        greenCards.add(new BulletTime());
        greenCards.add(new Burst());
        greenCards.add(new CalculatedGamble());
        greenCards.add(new Caltrops());
        greenCards.add(new Catalyst());
        greenCards.add(new Choke());
        greenCards.add(new CloakAndDagger());
        greenCards.add(new Concentrate());
        greenCards.add(new CorpseExplosion());
        greenCards.add(new CripplingPoison());
        greenCards.add(new DaggerSpray());
        greenCards.add(new DaggerThrow());
        greenCards.add(new Dash());
        greenCards.add(new DeadlyPoison());
        greenCards.add(new Defend_Green());
        greenCards.add(new Deflect());
        greenCards.add(new DieDieDie());
        greenCards.add(new Distraction());
        greenCards.add(new DodgeAndRoll());
        greenCards.add(new Doppelganger());
        greenCards.add(new EndlessAgony());
        greenCards.add(new Envenom());
        greenCards.add(new EscapePlan());
        greenCards.add(new Eviscerate());
        greenCards.add(new Expertise());
        greenCards.add(new Finisher());
        greenCards.add(new Flechettes());
        greenCards.add(new FlyingKnee());
        greenCards.add(new Footwork());
        greenCards.add(new GlassKnife());
        greenCards.add(new GrandFinale());
        greenCards.add(new HeelHook());
        greenCards.add(new InfiniteBlades());
        greenCards.add(new LegSweep());
        greenCards.add(new Malaise());
        greenCards.add(new MasterfulStab());
        greenCards.add(new Neutralize());
        greenCards.add(new Nightmare());
        greenCards.add(new NoxiousFumes());
        greenCards.add(new Outmaneuver());
        greenCards.add(new PhantasmalKiller());
        greenCards.add(new PiercingWail());
        greenCards.add(new PoisonedStab());
        greenCards.add(new Predator());
        greenCards.add(new Prepared());
        greenCards.add(new QuickSlash());
        greenCards.add(new Reflex());
        greenCards.add(new RiddleWithHoles());
        greenCards.add(new Setup());
        greenCards.add(new Skewer());
        greenCards.add(new Slice());
        greenCards.add(new StormOfSteel());
        greenCards.add(new Strike_Green());
        greenCards.add(new SuckerPunch());
        greenCards.add(new Survivor());
        greenCards.add(new Tactician());
        greenCards.add(new Terror());
        greenCards.add(new ToolsOfTheTrade());
        greenCards.add(new SneakyStrike());
        greenCards.add(new Unload());
        greenCards.add(new WellLaidPlans());
        greenCards.add(new WraithForm());
        int rng = AbstractDungeon.cardRandomRng.random(greenCards.size() - 1);
        return greenCards.get(rng);
    }

    public static AbstractCard getBlueCard() {
        ArrayList<AbstractCard> blueCards = new ArrayList<>();
        blueCards.add(new Aggregate());
        blueCards.add(new AllForOne());
        blueCards.add(new Amplify());
        blueCards.add(new AutoShields());
        blueCards.add(new BallLightning());
        blueCards.add(new Barrage());
        blueCards.add(new BeamCell());
        blueCards.add(new BiasedCognition());
        blueCards.add(new Blizzard());
        blueCards.add(new BootSequence());
        blueCards.add(new Buffer());
        blueCards.add(new Capacitor());
        blueCards.add(new Chaos());
        blueCards.add(new Chill());
        blueCards.add(new Claw());
        blueCards.add(new ColdSnap());
        blueCards.add(new CompileDriver());
        blueCards.add(new ConserveBattery());
        blueCards.add(new Consume());
        blueCards.add(new Coolheaded());
        blueCards.add(new CoreSurge());
        blueCards.add(new CreativeAI());
        blueCards.add(new Darkness());
        blueCards.add(new Defend_Blue());
        blueCards.add(new Defragment());
        blueCards.add(new DoomAndGloom());
        blueCards.add(new DoubleEnergy());
        blueCards.add(new Dualcast());
        blueCards.add(new EchoForm());
        blueCards.add(new Electrodynamics());
        blueCards.add(new Fission());
        blueCards.add(new ForceField());
        blueCards.add(new FTL());
        blueCards.add(new Fusion());
        blueCards.add(new GeneticAlgorithm());
        blueCards.add(new Glacier());
        blueCards.add(new GoForTheEyes());
        blueCards.add(new Heatsinks());
        blueCards.add(new HelloWorld());
        blueCards.add(new Hologram());
        blueCards.add(new Hyperbeam());
        blueCards.add(new Leap());
        blueCards.add(new LockOn());
        blueCards.add(new Loop());
        blueCards.add(new MachineLearning());
        blueCards.add(new Melter());
        blueCards.add(new MeteorStrike());
        blueCards.add(new MultiCast());
        blueCards.add(new Overclock());
        blueCards.add(new Rainbow());
        blueCards.add(new Reboot());
        blueCards.add(new Rebound());
        blueCards.add(new Recursion());
        blueCards.add(new Recycle());
        blueCards.add(new ReinforcedBody());
        blueCards.add(new Reprogram());
        blueCards.add(new RipAndTear());
        blueCards.add(new Scrape());
        blueCards.add(new Seek());
        blueCards.add(new SelfRepair());
        blueCards.add(new Skim());
        blueCards.add(new Stack());
        blueCards.add(new StaticDischarge());
        blueCards.add(new SteamBarrier());
        blueCards.add(new Storm());
        blueCards.add(new Streamline());
        blueCards.add(new Strike_Blue());
        blueCards.add(new Sunder());
        blueCards.add(new SweepingBeam());
        blueCards.add(new Tempest());
        blueCards.add(new ThunderStrike());
        blueCards.add(new Turbo());
        blueCards.add(new Equilibrium());
        blueCards.add(new WhiteNoise());
        blueCards.add(new Zap());
        int rng = AbstractDungeon.cardRandomRng.random(blueCards.size() - 1);
        return blueCards.get(rng);
    }

    public static AbstractCard getPurpleCard() {
        ArrayList<AbstractCard> purpleCards = new ArrayList<>();
        purpleCards.add(new Alpha());
        purpleCards.add(new BattleHymn());
        purpleCards.add(new Blasphemy());
        purpleCards.add(new BowlingBash());
        purpleCards.add(new Brilliance());
        purpleCards.add(new CarveReality());
        purpleCards.add(new Collect());
        purpleCards.add(new Conclude());
        purpleCards.add(new ConjureBlade());
        purpleCards.add(new Consecrate());
        purpleCards.add(new Crescendo());
        purpleCards.add(new CrushJoints());
        purpleCards.add(new CutThroughFate());
        purpleCards.add(new DeceiveReality());
        purpleCards.add(new Defend_Watcher());
        purpleCards.add(new DeusExMachina());
        purpleCards.add(new DevaForm());
        purpleCards.add(new Devotion());
        purpleCards.add(new EmptyBody());
        purpleCards.add(new EmptyFist());
        purpleCards.add(new EmptyMind());
        purpleCards.add(new Eruption());
        purpleCards.add(new Establishment());
        purpleCards.add(new Evaluate());
        purpleCards.add(new Fasting());
        purpleCards.add(new FearNoEvil());
        purpleCards.add(new FlurryOfBlows());
        purpleCards.add(new FlyingSleeves());
        purpleCards.add(new FollowUp());
        purpleCards.add(new ForeignInfluence());
        purpleCards.add(new Foresight());
        purpleCards.add(new Halt());
        purpleCards.add(new Indignation());
        purpleCards.add(new InnerPeace());
        purpleCards.add(new Judgement());
        purpleCards.add(new JustLucky());
        purpleCards.add(new LessonLearned());
        purpleCards.add(new LikeWater());
        purpleCards.add(new MasterReality());
        purpleCards.add(new Meditate());
        purpleCards.add(new MentalFortress());
        purpleCards.add(new Nirvana());
        purpleCards.add(new Omniscience());
        purpleCards.add(new Perseverance());
        purpleCards.add(new Pray());
        purpleCards.add(new PressurePoints());
        purpleCards.add(new Prostrate());
        purpleCards.add(new Protect());
        purpleCards.add(new Ragnarok());
        purpleCards.add(new ReachHeaven());
        purpleCards.add(new Rushdown());
        purpleCards.add(new Sanctity());
        purpleCards.add(new SandsOfTime());
        purpleCards.add(new SashWhip());
        purpleCards.add(new Scrawl());
        purpleCards.add(new SignatureMove());
        purpleCards.add(new SimmeringFury());
        purpleCards.add(new SpiritShield());
        purpleCards.add(new Strike_Purple());
        purpleCards.add(new Study());
        purpleCards.add(new Swivel());
        purpleCards.add(new TalkToTheHand());
        purpleCards.add(new Tantrum());
        purpleCards.add(new ThirdEye());
        purpleCards.add(new Tranquility());
        purpleCards.add(new Vault());
        purpleCards.add(new Vigilance());
        purpleCards.add(new Wallop());
        purpleCards.add(new WaveOfTheHand());
        purpleCards.add(new Weave());
        purpleCards.add(new WheelKick());
        purpleCards.add(new WindmillStrike());
        purpleCards.add(new Wish());
        purpleCards.add(new Worship());
        purpleCards.add(new WreathOfFlame());
        int rng = AbstractDungeon.cardRandomRng.random(purpleCards.size() - 1);
        return purpleCards.get(rng);
    }
}
