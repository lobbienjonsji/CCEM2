package CCEMRelics.patches;

import CCEMRelics.relics.BundleOfChain;
import CCEMRelics.relics.FiftyFTOfRope;
import CCEMRelics.relics.PowerPill;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = AbstractCard.class,
        method = "renderCard"
)
public class RopeChainPillRenderCardPatch {

    public static void Postfix(AbstractCard __instance, SpriteBatch sb, boolean hovered, boolean selected)
    {

        if (!Settings.hideCards && !__instance.isFlipped) {
            if (ChainField.chain.get(__instance)) {
                if(AbstractDungeon.player.hasRelic(FiftyFTOfRope.ID)) {
                    FiftyFTOfRope relic = (FiftyFTOfRope) AbstractDungeon.player.getRelic(FiftyFTOfRope.ID);
                    if(__instance.cardID.equals(relic.getFiftyFTOfRopeCardID())) {
                        Texture ropeTexture = relic.getFiftyFTOfRopeTexture();
                        Vector2 tmp = new Vector2(135F, 185F);
                        tmp.rotate(__instance.angle);
                        tmp.scl(__instance.drawScale * Settings.scale);

                        sb.setColor(Color.WHITE.cpy());

                        sb.draw(
                                ropeTexture,
                                tmp.x + __instance.current_x - (float) ropeTexture.getWidth() / 2.0F,
                                tmp.y + __instance.current_y - (float) ropeTexture.getHeight() / 2.0F,
                                (float) ropeTexture.getWidth() / 2.0F,
                                (float) ropeTexture.getHeight() / 2.0F,
                                (float) ropeTexture.getWidth(),
                                (float) ropeTexture.getHeight(),
                                __instance.drawScale * 2.5F,
                                __instance.drawScale * 2.5F,
                                __instance.angle,
                                0,
                                0,
                                ropeTexture.getWidth(),
                                ropeTexture.getHeight(),
                                false,
                                false
                        );
                    }
                }
                if(AbstractDungeon.player.hasRelic(BundleOfChain.ID)) {
                    BundleOfChain relic = (BundleOfChain) AbstractDungeon.player.getRelic(BundleOfChain.ID);
                    if(__instance.cardID.equals(relic.getBundleOfChainCardID())) {
                        Texture bundleOfChainTexture = relic.getBundleOfChainTexture();
                        Vector2 tmp = new Vector2(135F, 185F);
                        tmp.rotate(__instance.angle);
                        tmp.scl(__instance.drawScale * Settings.scale);

                        sb.setColor(Color.WHITE.cpy());

                        sb.draw(
                                bundleOfChainTexture,
                                tmp.x + __instance.current_x - (float) bundleOfChainTexture.getWidth() / 2.0F,
                                tmp.y + __instance.current_y - (float) bundleOfChainTexture.getHeight() / 2.0F,
                                (float) bundleOfChainTexture.getWidth() / 2.0F,
                                (float) bundleOfChainTexture.getHeight() / 2.0F,
                                (float) bundleOfChainTexture.getWidth(),
                                (float) bundleOfChainTexture.getHeight(),
                                __instance.drawScale * 2.5F,
                                __instance.drawScale * 2.5F,
                                __instance.angle,
                                0,
                                0,
                                bundleOfChainTexture.getWidth(),
                                bundleOfChainTexture.getHeight(),
                                false,
                                false
                        );
                    }
                }
                if(AbstractDungeon.player.hasRelic(PowerPill.ID))
                {
                    if(__instance.type == AbstractCard.CardType.POWER)
                    {
                        PowerPill relic = (PowerPill) AbstractDungeon.player.getRelic(PowerPill.ID);
                        Texture powerPillTexture = relic.getPowerPillTexture();
                        Vector2 tmp = new Vector2(135F, 185F);
                        tmp.rotate(__instance.angle);
                        tmp.scl(__instance.drawScale * Settings.scale);

                        sb.setColor(Color.WHITE.cpy());

                        sb.draw(
                                powerPillTexture,
                                tmp.x + __instance.current_x - (float) powerPillTexture.getWidth() / 2.0F,
                                tmp.y + __instance.current_y - (float) powerPillTexture.getHeight() / 2.0F,
                                (float) powerPillTexture.getWidth() / 2.0F,
                                (float) powerPillTexture.getHeight() / 2.0F,
                                (float) powerPillTexture.getWidth(),
                                (float) powerPillTexture.getHeight(),
                                __instance.drawScale * 2.5F,
                                __instance.drawScale * 2.5F,
                                __instance.angle,
                                0,
                                0,
                                powerPillTexture.getWidth(),
                                powerPillTexture.getHeight(),
                                false,
                                false
                        );
                    }
                }
            }
        }
    }
}
