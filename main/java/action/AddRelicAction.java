package action;

import basemod.patches.com.megacrit.cardcrawl.helpers.RelicLibrary.GetRelicFix;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PutOnBottomOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.*;

import java.util.ArrayList;
import java.util.Collections;

public class AddRelicAction extends AbstractGameAction {
    public int num;
    public AddRelicAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractRelic> rs = new ArrayList<>();
        AbstractRelic r1 = new BurningBlood();
        AbstractRelic r2 = new CrackedCore();
        AbstractRelic r3 = new SnakeRing();
        AbstractRelic r4 = new PureWater();
        Collections.addAll(rs, r1,r2,r3,r4);
        int[] arr = {1, 2, 3, 4};
        int index = AbstractDungeon.cardRandomRng.random(arr.length - 1);
        this.num = arr[index];
        switch (num) {
            case 1:
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, rs.get(0));
                this.isDone = true;
                break;
            case 2:
                p.maxOrbs = 1;
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, rs.get(1));
                this.isDone = true;
                break;
            case 3:
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, rs.get(2));
                this.isDone = true;
                break;
            case 4:
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, rs.get(3));
                this.isDone = true;
                break;
            default:
                System.out.println("出错了...");
                this.isDone = true;
                break;
        }
    }

    public AbstractRelic getRelic() {
        if (this.num == 1) {
            return new BurningBlood();
        }
        if (this.num == 2) {
            return new CrackedCore();
        }
        if (this.num == 3) {
            return new SnakeRing();
        }
        if (this.num == 4) {
            return new PureWater();
        }
        return null;
    }
}
