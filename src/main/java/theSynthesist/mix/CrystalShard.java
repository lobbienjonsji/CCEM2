package theSynthesist.mix;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CrystalShard extends AbstractMixIngredient{

    public static final String ID = CrystalShard.class.getSimpleName();

    public CrystalShard(int amount)
    {
        super(ID, amount);
    }

    @Override
    public void use() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.amount));
    }
}
