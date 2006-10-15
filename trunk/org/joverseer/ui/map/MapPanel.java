package org.joverseer.ui.map;

import org.springframework.richclient.application.Application;
import org.springframework.richclient.application.event.LifecycleApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.joverseer.ui.map.MapMetadata;
import org.joverseer.ui.events.SelectedHexChangedEvent;
import org.joverseer.ui.events.SelectedHexChangedListener;
import org.joverseer.ui.SimpleLifecycleAdvisor;
import org.joverseer.ui.LifecycleEventsEnum;
import org.joverseer.ui.support.JOverseerEvent;
import org.joverseer.metadata.domain.Hex;
import org.joverseer.metadata.GameMetadata;
import org.joverseer.domain.PopulationCenter;
import org.joverseer.domain.Character;
import org.joverseer.domain.Army;
import org.joverseer.game.Game;
import org.joverseer.game.TurnElementsEnum;
import org.joverseer.support.GameHolder;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: Sep 10, 2006
 * Time: 4:28:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapPanel extends JPanel implements MouseListener {
    protected javax.swing.event.EventListenerList listenerList =
            new javax.swing.event.EventListenerList();

    private static Logger logger = Logger.getLogger(MapPanel.class);
    private static MapPanel _instance = null;

    Polygon hex = new Polygon();
    int[] xPoints = new int[6];
    int[] yPoints = new int[6];
    Point location = new Point();

    BufferedImage map = null;
    BufferedImage mapItems = null;

    Point selectedHex = null;

    Game game = ((GameHolder)Application.instance().getApplicationContext().getBean("gameHolder")).getGame();

    public MapPanel() {
        addMouseListener(this);
        _instance = this;
    }

    public static MapPanel instance() {
        return _instance;
    }

    private void setHexLocation(int x, int y) {
        x--;
        y--;
        MapMetadata metadata = (MapMetadata)Application.instance().getApplicationContext().getBean("mapMetadata");
        if (y % 2 == 0) {
            location.setLocation(metadata.getHexSize() * metadata.getGridCellWidth() * x,
                                 metadata.getHexSize() * 3 / 4 * y * metadata.getGridCellHeight());
        } else {
            location.setLocation((x + .5) * metadata.getHexSize() * metadata.getGridCellWidth(),
                    metadata.getHexSize() * 3 / 4 * y * metadata.getGridCellHeight());
        }
    }

    private void setPoints(int x, int y) {
        MapMetadata metadata = (MapMetadata)Application.instance().getApplicationContext().getBean("mapMetadata");
        xPoints[0] = metadata.getHexSize() / 2;
        xPoints[1] = metadata.getHexSize();
        xPoints[2] = metadata.getHexSize();
        xPoints[3] = metadata.getHexSize() / 2;
        xPoints[4] = 0;
        xPoints[5] = 0;

        yPoints[0] = 0;
        yPoints[1] = metadata.getHexSize() / 4;
        yPoints[2] = metadata.getHexSize() * 3 / 4;
        yPoints[3] = metadata.getHexSize();
        yPoints[4] = metadata.getHexSize() * 3 / 4;
        yPoints[5] = metadata.getHexSize() / 4;

        setHexLocation(x, y);

        for (int i=0; i<6; i++) {
            xPoints[i] = xPoints[i] * metadata.getGridCellWidth() + location.x;
            yPoints[i] = yPoints[i] * metadata.getGridCellHeight() + location.y;
        }


    }

    /**
     * Creates the basic map (the background layer with the hexes)
     * The hexes are retrieved from the Game Metadata and rendered with all the
     * valid available renderers found in the Map Metadata
     *
     */
    private void createMap() {
        MapMetadata metadata;
        try {
            metadata = (MapMetadata)Application.instance().getApplicationContext().getBean("mapMetadata");
        }
        catch (Exception exc) {
            // application is not ready
            return;
        }

        GameMetadata gm = game.getMetadata();

        int width = metadata.getMapColumns() * metadata.getHexSize() * metadata.getGridCellWidth();
        int height = metadata.getMapRows() * metadata.getHexSize() * metadata.getGridCellHeight();
        map = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = map.createGraphics();

        for (Hex h : (Collection<Hex>)gm.getHexes()) {
            setHexLocation(h.getColumn(), h.getRow());
            for (org.joverseer.ui.map.renderers.Renderer r : (Collection<org.joverseer.ui.map.renderers.Renderer>)metadata.getRenderers()) {
                if (r.appliesTo(h)) {
                    r.render(h, g, location.x, location.y);
                }
            }
        }
    }

    /**
     * Draws the items on the map, i.e. everything that is in front
     * of the background (terrain)
     * todo update with renderers
     */
    private void createMapItems() {
        MapMetadata metadata;
        try {
            metadata = (MapMetadata)Application.instance().getApplicationContext().getBean("mapMetadata");
        }
        catch (Exception exc) {
            // application is not ready
            return;
        }

        int width = metadata.getMapColumns() * metadata.getHexSize() * metadata.getGridCellWidth();
        int height = metadata.getMapRows() * metadata.getHexSize() * metadata.getGridCellHeight();
        mapItems = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


        Graphics2D g = mapItems.createGraphics();

        if (map == null) {
            createMap();
        }
        g.drawImage(map, 0, 0, this);

        try {
            ArrayList pcs = game.getTurn().getContainer(TurnElementsEnum.PopulationCenter).getItems();
            for (PopulationCenter pc : (ArrayList<PopulationCenter>)pcs) {
                for (org.joverseer.ui.map.renderers.Renderer r : (Collection<org.joverseer.ui.map.renderers.Renderer>)metadata.getRenderers()) {
                    if (r.appliesTo(pc)) {
                        setHexLocation(pc.getX(), pc.getY());
                        try {
                            r.render(pc, g, location.x, location.y);
                        }
                        catch (Exception exc) {
                            logger.error("Error pc " + pc.getName() + " " + exc.getMessage());
                        }

                    }
                }
            }
        }
        catch (Exception exc) {
            logger.error("Error rendering pop centers " + exc.getMessage());
        }

        try {
            ArrayList characters = game.getTurn().getContainer(TurnElementsEnum.Character).getItems();
            for (Character c : (ArrayList<Character>)characters) {
                for (org.joverseer.ui.map.renderers.Renderer r : (Collection<org.joverseer.ui.map.renderers.Renderer>)metadata.getRenderers()) {
                    if (r.appliesTo(c)) {
                        setHexLocation(c.getX(), c.getY());
                        try {
                            r.render(c, g, location.x, location.y);
                        }
                        catch (Exception exc) {
                            logger.error("Error rendering character " + c.getName() + " " + exc.getMessage());
                        }
                    }
                }
            }
        }
        catch (Exception exc) {
            logger.error("Error rendering pop centers " + exc.getMessage());
        }

        try {
            ArrayList armies = game.getTurn().getContainer(TurnElementsEnum.Army).getItems();
            for (Army army : (ArrayList<Army>)armies) {
                for (org.joverseer.ui.map.renderers.Renderer r : (Collection<org.joverseer.ui.map.renderers.Renderer>)metadata.getRenderers()) {
                    if (r.appliesTo(army)) {
                        setHexLocation(army.getX(), army.getY());
                        try {
                            r.render(army, g, location.x, location.y);
                        }
                        catch (Exception exc) {
                            logger.error("Error rendering army " + army.getCommanderName() + " " + exc.getMessage());
                        }
                    }
                }
            }
        }
        catch (Exception exc) {
            logger.error("Error rendering pop centers " + exc.getMessage());
        }
    }

    public void invalidate() {
    }

    public void invalidateAll() {
        map = null;
        mapItems = null;
        game = ((GameHolder)Application.instance().getApplicationContext().getBean("gameHolder")).getGame();
    }

    /**
     * Basic painting
     * todo update/cleanup
     * @param g
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (mapItems == null) {
            createMapItems();
        }
        //g.drawImage(map, 0, 0, this);
        g.drawImage(mapItems, 0, 0, this);

        if (getSelectedHex() != null)
        {
            Stroke s = ((Graphics2D)g).getStroke();
            Stroke r = new BasicStroke(2);
            setPoints(getSelectedHex().x, getSelectedHex().y);
            g.setColor(Color.YELLOW);
            ((Graphics2D)g).setStroke(r);
            g.drawPolygon(xPoints, yPoints, 6);
            ((Graphics2D)g).setStroke(s);
        }

    }

    public Point getSelectedHex() {
        return selectedHex;
    }

    public void setSelectedHex(Point selectedHex) {
        if (this.selectedHex == null || selectedHex.x != this.selectedHex.x || selectedHex.y != this.selectedHex.y) {
            this.selectedHex = selectedHex;
            //fireMyEvent(new SelectedHexChangedEvent(this));
            Application.instance().getApplicationContext().publishEvent(
                    new JOverseerEvent(LifecycleEventsEnum.SelectedHexChangedEvent.toString(), selectedHex, this));
        }
    }

    public Rectangle getSelectedHexRectangle() {
        setHexLocation(getSelectedHex().x,  getSelectedHex().y);
        MapMetadata metadata;
        try {
            metadata = (MapMetadata)Application.instance().getApplicationContext().getBean("mapMetadata");
            return new Rectangle(location.x, location.y, metadata.getHexSize() * metadata.getGridCellWidth(), metadata.getHexSize() * metadata.getGridCellHeight());
        }
        catch (Exception exc) {
            // application is not ready
        }
        return new Rectangle(location.x, location.y, 1, 1);
    }

    /**
     * Handles the mouse pressed event to change the current selected hex
     */
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point p = e.getPoint();
//            MessageDialog dlg = new MessageDialog("test", p.x + "," + p.y);
//            dlg.getDialog().show();
            Point hex = getHexFromPoint(p);
            setSelectedHex(hex);
            this.updateUI();
        }
    }

    /**
     * Given a client point (eg from mouse click), it finds the containing hex
     * and returns it as a point (i.e. point.x = hex.column, point.y = hex.row)
     */
    private Point getHexFromPoint(Point p) {
        MapMetadata metadata = (MapMetadata)Application.instance().getApplicationContext().getBean("mapMetadata");
        int y = p.y / (metadata.getHexSize() * 3 / 4 * metadata.getGridCellHeight()) + 1;
        int x;
        if (y % 2 == 1) {
            x = p.x / (metadata.getHexSize() * metadata.getGridCellWidth()) + 1;
        } else {
            x = (p.x - metadata.getHexSize() / 2 * metadata.getGridCellWidth()) / (metadata.getHexSize() * metadata.getGridCellWidth()) + 1;
        }
        return new Point(x, y);
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    // This methods allows classes to register for MyEvents
    public void addSelectedHexChangedEventListener(SelectedHexChangedListener listener) {
        listenerList.add(SelectedHexChangedListener.class, listener);
    }

    void fireMyEvent(SelectedHexChangedEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==SelectedHexChangedListener.class) {
                ((SelectedHexChangedListener)listeners[i+1]).eventOccured(evt);
            }
        }
    }

}
