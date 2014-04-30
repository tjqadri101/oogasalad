package game_authoring_environment;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

import engine.GameEngine;

import jgame.platform.JGEngine;

/**
 * The LayerUI in the GAE. Currently doing nothing yet, but exist
 * for future declaration of swing.
 * 
 * @author Nick Pengyi Pan
 * 
 * */

class GAELayerUI extends LayerUI<JComponent> {
//	private boolean mActive;
//	  private int mX, mY;
//
//	  
//
//	  @Override
//	  public void installUI(JComponent c) {
//	    super.installUI(c);
//	    JLayer jlayer = (JLayer)c;
//	    jlayer.repaint();
//	    jlayer.setLayerEventMask(
//	      AWTEvent.MOUSE_EVENT_MASK |
//	      AWTEvent.MOUSE_MOTION_EVENT_MASK
//	    );
//	  }
//
//	  @Override
//	  public void uninstallUI(JComponent c) {
//	    JLayer jlayer = (JLayer)c;
//	    jlayer.setLayerEventMask(0);
//	    super.uninstallUI(c);
//	  }
//
//	  @Override
//	  public void paint (Graphics g, JComponent c) {
//	    Graphics2D g2 = (Graphics2D)g.create();
//
//	    // Paint the view.
//	    super.paint (g2, c);
//
//	    if (true) {
//	    	int w = c.getWidth()*1/5+60;
//	        int h = c.getHeight();
//	     
//	        float fade = (float)20 / (float)30;
//	        // Gray it out.
//	        Composite urComposite = g2.getComposite();
//	        g2.setComposite(AlphaComposite.getInstance(
//	            AlphaComposite.SRC_OVER, .5f * fade));
//	        g2.fillRect(0, 0, w, h);
//	        g2.setComposite(urComposite);
//	     
//	        // Paint the wait indicator.
//	        int s = Math.min(w, h) / 5;
//	        int cx = w / 2;
//	        int cy = h / 2;
//	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//	            RenderingHints.VALUE_ANTIALIAS_ON);
//	        g2.setStroke(
//	            new BasicStroke(s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//	        g2.setPaint(Color.white);
//	        g2.rotate(Math.PI * 20 / 180, cx, cy);
//	        for (int i = 0; i < 12; i++) {
//	          float scale = (11.0f - (float)i) / 11.0f;
//	          g2.drawLine(cx + s, cy, cx + s * 2, cy);
//	          g2.rotate(-Math.PI / 6, cx, cy);
//	          g2.setComposite(AlphaComposite.getInstance(
//	              AlphaComposite.SRC_OVER, scale * fade));
//	        }
//	     
//	        g2.dispose();
//	    }
//
//	    g2.dispose();
//	  }
//
//	  @Override
//	  protected void processMouseEvent(MouseEvent e, JLayer l) {
//			if (e.getID() == MouseEvent.MOUSE_ENTERED) mActive = true;
//		    if (e.getID() == MouseEvent.MOUSE_EXITED) mActive = false;
//		    l.repaint(5);
//	    
//	  }
//
//	  @Override
//	  protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
//	    Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
//	    mX = p.x;
//	    mY = p.y;
//	    l.repaint(5);
//	  }
//
//	  
	}