package theSynthesist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSynthesist.characters.SynthesistChar;

public class ThawMixAction extends AbstractGameAction {


    @Override
    public void update() {
        this.isDone = true;
        AbstractPlayer p = AbstractDungeon.player;
        if(p instanceof SynthesistChar)
        {
            ((SynthesistChar)p).mix.thawMix();
        }
    }
}
