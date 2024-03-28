package guiapplication.schedulePlanner.Simulator.npc;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class InfoScreen {

    private String[] info;
    private double width;
    private double height;
    private Point2D position;

    public InfoScreen(String[] info, Point2D position) {
        this.info = info;
        this.position = position;
        this.width = 150;
        this.height = 70; //todo calculate these
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fill(new Rectangle2D.Double(position.getX(), position.getY(), width, height));

        g.setColor(Color.BLACK);

        int lineHeight = g.getFontMetrics().getHeight();
        int textY = (int) (this.position.getY() + lineHeight);
        for (String line : this.info) {
            g.drawString(line, (int) (this.position.getX() + 10), textY);
            textY += lineHeight;
        }

        g.setClip(null);
    }

    public void updatePosition(double x, double y) {
        this.position = new Point2D.Double(x + 25, y - 50); //todo change these magic offsets
    }
}
