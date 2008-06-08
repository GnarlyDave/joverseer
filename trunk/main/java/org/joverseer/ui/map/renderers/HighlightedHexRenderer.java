package org.joverseer.ui.map.renderers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import org.joverseer.ui.LifecycleEventsEnum;
import org.joverseer.ui.domain.mapItems.HighlightHexesMapItem;
import org.joverseer.ui.domain.mapItems.TrackCharacterMapItem;
import org.joverseer.ui.map.MapPanel;
import org.joverseer.ui.support.GraphicUtils;
import org.joverseer.ui.support.JOverseerEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Renders the highlighted hexes
 * 
 * @author Marios Skounakis
 */
public class HighlightedHexRenderer extends DefaultHexRenderer {
    String highlightColor = "#ff3300";
    int width = 2;
    
    public boolean appliesTo(Object obj) {
        return HighlightHexesMapItem.class.isInstance(obj) ||
        	TrackCharacterMapItem.class.isInstance(obj);
        
    }
    
    public void render(Object obj, Graphics2D g, int x, int y) {
        if (!appliesTo(obj)) {
            throw new IllegalArgumentException(obj.toString());
        }

        if (metadata == null) {
            init();
        }
        if (HighlightHexesMapItem.class.isInstance(obj)) renderHHMI(obj, g, x, y);
        if (TrackCharacterMapItem.class.isInstance(obj)) renderTCMI(obj, g, x, y);
    }
    
    private void renderHHMI(Object obj, Graphics2D g, int x, int y) {
    	Color color = Color.decode(getHighlightColor());
        Stroke currentStroke = g.getStroke();
        g.setStroke(GraphicUtils.getBasicStroke(getWidth()));
        HighlightHexesMapItem hmi = (HighlightHexesMapItem)obj;
        for (Integer hexNo : hmi.getHexesToHighlight()) {
            Point p = MapPanel.instance().getHexLocation(hexNo);
            Polygon polygon = new Polygon(xPoints, yPoints, 6);
            polygon.translate(p.x, p.y);
            g.setColor(color);
            g.drawPolygon(polygon);
        }
        g.setStroke(currentStroke);
    }
    
    private void renderTCMI(Object obj, Graphics2D g, int x, int y) {
    	Color color = Color.decode(getHighlightColor());
        Stroke currentStroke = g.getStroke();
        g.setStroke(GraphicUtils.getBasicStroke(getWidth()));
        TrackCharacterMapItem hmi = (TrackCharacterMapItem)obj;
        for (int i=0; i<hmi.getHexes().size(); i++) {
        	Integer hexNo = hmi.getHexes().get(i);
        	Integer turnNo = hmi.getTurns().get(i);
            Point p = MapPanel.instance().getHexLocation(hexNo);
            Polygon polygon = new Polygon(xPoints, yPoints, 6);
            polygon.translate(p.x, p.y);
            g.setColor(color);
            g.drawPolygon(polygon);
            
        	Point p2 = MapPanel.instance().getHexCenter(hexNo);
            if (i > 0) {
            	Integer prevHexNo = hmi.getHexes().get(i-1);
            	Point p1 = MapPanel.instance().getHexCenter(prevHexNo);
            	g.drawLine(p2.x, p2.y, p1.x, p1.y);
            }
            
            drawString(g, String.valueOf(turnNo), p2, p2);
            
        }
        g.setStroke(currentStroke);
    }

    private void drawString(Graphics2D g, String str, Point p1, Point p2) {
        // calculate and prepare character name rendering
        Point p = new Point((p1.x + p2.x)/2, (p1.y + p2.y)/2);
        Font f = GraphicUtils.getFont("Microsoft Sans Serif", Font.PLAIN, 9);
        FontMetrics fm = g.getFontMetrics(f);
        Rectangle2D bb = fm.getStringBounds(str, g);
        Rectangle b = new Rectangle(((Double)bb.getX()).intValue(),
                                        ((Double)bb.getY()).intValue(),
                                        ((Double)bb.getWidth()).intValue(),
                                        ((Double)bb.getHeight()).intValue());
        int xt = p.x - new Double(b.getWidth() / 2).intValue();
        int yt = p.y;
        b.translate(xt, yt);
        RoundRectangle2D rr = new RoundRectangle2D.Double(b.getX(), b.getY(), b.getWidth() + 2, b.getHeight() + 2, 3, 3);
        g.setFont(f);

        g.setColor(Color.BLACK);
        // fill rectangle behind char name
        g.fill(rr);
        // draw char name
        g.setColor(Color.WHITE);
        g.drawString(str, xt + 1, yt + 1);

    }
    
    public String getHighlightColor() {
        return highlightColor;
    }

    
    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    
    public int getWidth() {
        return width;
    }

    
    public void setWidth(int width) {
        this.width = width;
    }

    
}
