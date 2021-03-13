package theSynthesist.mix;

import ACCEMCore.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theSynthesist.SynthesistMain.makeOrbPath;

public abstract class AbstractMixIngredient {
    public int amount = -1;

    public String name;
    public String description;
    public String id;

    private static final float WIDTH = 96.0F;
    private static final float HEIGHT = 96.0F;

    public Hitbox hb;
    public float cX;
    public float cY;
    public float tX;
    public float tY;
    public Color color;
    private Texture img;
    public float angle;
    public float scale;

    public AbstractMixIngredient(final String ID)
    {
        this.id = ID;
        this.name = languagePack.getOrbString(id).NAME;
        this.description = languagePack.getOrbString(id).NAME;
        this.img = TextureLoader.getTexture(makeOrbPath(ID + ".png"));
    }

    public void addAmount(int amountAdd)
    {
        this.amount += amountAdd;
        //TODO: Update visuals
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(this.img, this.cX - (WIDTH / 2.0F), this.cY - (HEIGHT / 2.0F), WIDTH / 2.0F, HEIGHT / 2.0F, WIDTH, HEIGHT, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        this.hb.render(sb);
    }

    public void update()
    {
        this.hb.update();
        if(this.hb.hovered)
        {
            TipHelper.renderGenericTip(this.tX + WIDTH * Settings.scale, this.tY + 64.0F * Settings.scale, name, description);

        }
    }

    public void moveIngredient(float x, float y)
    {
        this.cX = this.tX = x;
        this.cY = this.tY = y;
        this.hb.move(tX, tY);
    }

    public abstract void use();
}
