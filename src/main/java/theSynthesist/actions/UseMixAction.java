package theSynthesist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSynthesist.characters.SynthesistChar;

public class UseMixAction extends AbstractGameAction {
    @Override
    public void update() {
        this.isDone = true;
        if(AbstractDungeon.player instanceof SynthesistChar)
        {
            ((SynthesistChar)AbstractDungeon.player).mix.drinkMix();
        }
    }
}
