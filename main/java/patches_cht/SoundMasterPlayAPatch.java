package patches_cht;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;

import java.util.HashMap;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.audio.SoundMaster",
        method = "playA",
        paramtypes = {"java.lang.String", "float"}
)
public class SoundMasterPlayAPatch {
    public static HashMap<String, Sfx> map = new HashMap();

    public SoundMasterPlayAPatch() {
    }

    public static long Postfix(long res, SoundMaster _inst, String key, float pitchAdjust) {
        return map.containsKey(key) ? ((Sfx)map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + pitchAdjust, 0.0F) : 0L;
    }

    private static Sfx load(String filename) {
        return new Sfx("audio/sound/" + filename, false);
    }

    static {
        map.put("SELECT_CTH", load("test.ogg"));
        map.put("SCAR", load("sc.ogg"));
    }
}

