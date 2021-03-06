package aramod.relics;

import aramod.powers.FastPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TestRelic extends CustomRelic {
    public static final String ID = "AraMod:TestRelic";

    public TestRelic(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/hm.png"), TextureLoader.getTexture("aramod/img/relics/hm-outline.png"), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart(){
        //AbstractMonster mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.miscRng);
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FastPower(p, 0)));
    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}