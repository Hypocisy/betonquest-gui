package co.uk.mommyheather.betonquestgui.gui.widgets;

import co.uk.mommyheather.betonquestgui.gui.Row;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.mutable.MutableInt;

import javax.annotation.Nonnull;
import java.util.Optional;

import static co.uk.mommyheather.betonquestgui.gui.compass.CompassOverlay.drawString;

@OnlyIn(Dist.CLIENT)
public class WidgetRow implements Renderable {
    private final int x;
    private final int y;
    private final int color;
    private final Row row;

    public WidgetRow(int x, int y, Row row) {
        this(x, y, ChatFormatting.WHITE.getColor(), row);
    }

    public WidgetRow(int x, int y, int color, Row row) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.row = row;
    }

    @Override
    @SuppressWarnings("resource")
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float unused) {
        MutableInt xOffset = new MutableInt();
        this.row.getText().visit((style, text) ->
        {
            Component textComponent = Component.literal(text).setStyle(style);
            drawString(guiGraphics, textComponent.getString(), this.x + xOffset.getValue(), this.y);
            xOffset.add(Minecraft.getInstance().font.width(textComponent));
            return Optional.empty();
        }, Style.EMPTY);
    }
}
