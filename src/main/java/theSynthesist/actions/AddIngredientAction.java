package theSynthesist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSynthesist.characters.SynthesistChar;
import theSynthesist.mix.AbstractMixIngredient;

public class AddIngredientAction extends AbstractGameAction {

    private AbstractMixIngredient ingredient;

    public AddIngredientAction(AbstractMixIngredient ingredient)
    {
        this.ingredient = ingredient;
    }

    @Override
    public void update() {
        this.isDone = true;
        AbstractPlayer p = AbstractDungeon.player;
        if(p instanceof SynthesistChar)
        {
            ((SynthesistChar) p).mix.addIngredient(ingredient);
        }
    }
}
