package theSynthesist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSynthesist.characters.TheSynthesist;

public class UseMixAction extends AbstractGameAction {
    @Override
    public void update() {
        this.isDone = true;
        if(AbstractDungeon.player instanceof TheSynthesist)
        {
            ((TheSynthesist)AbstractDungeon.player).mix.drinkMix();
        }
    }
}
