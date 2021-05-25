package theSynthesist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSynthesist.characters.SynthesistChar;

public class FreezeMixAction extends AbstractGameAction {

    private int freezeTurns;

    public FreezeMixAction(final int amount)
    {
        this.freezeTurns = amount;
    }

    @Override
    public void update() {
        this.isDone = true;
        AbstractPlayer p = AbstractDungeon.player;
        if(p instanceof SynthesistChar)
        {
            ((SynthesistChar)p).mix.freezeMix(this.freezeTurns);
        }
    }
}
