package theSynthesist.mix;

import aramod.utils.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;

import java.util.ArrayList;

public class SynthesistMix {
    private static final String MIX_TEXTURE = "theSynthesistResources/images/orbs/default_orb.png";

    private String[] ingredientNames;
    private String[] ingredientDescriptions;

    public int ingredientFlameroot;
    public int ingredientCrystalShard;
    public int ingredientToxflower;
    public int ingredientDwarven;
    public int ingredientBerserk;
    public int ingredientDefend;
    public int ingredientGreed;
    public int ingredientIgnition;
    public int ingredientDew;
    public int ingredientAncient;
    public int ingredientCondensedMana;
    public int ingredientMercurium;

    public int turnCounter;
    public boolean frozen;

    private ArrayList<PowerTip> tips = new ArrayList();
    public Hitbox hb;
    private static final float WIDTH = 96.0F;
    private static final float HEIGHT = 96.0F;
    public float cX = 0.0F;
    public float cY = 0.0F;
    public float tX;
    public float tY;
    public Color color;
    private Texture img;
    private float angle;
    private float scale;


    public SynthesistMix()
    {
        this.color = Color.WHITE.cpy();
        this.hb = new Hitbox(WIDTH * Settings.scale, HEIGHT * Settings.scale);// 37
        this.img = TextureLoader.getTexture(MIX_TEXTURE);
    }

    public void update()
    {
        this.hb.update();
        if(this.hb.hovered)
        {
            TipHelper.renderGenericTip(this.tX + 96.0F * Settings.scale, this.tY + 64.0F * Settings.scale, this.name, this.description);
        }
    }
}
