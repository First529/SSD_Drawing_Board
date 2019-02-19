package objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
		this.recalculateRegion();
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		this.x += dX;
		this.y += dY;
		for (GObject g : gObjects) g.move(dX , dY);	
	}
	
	public void recalculateRegion() {
		int maxX = 0, minX = gObjects.get(0).x, maxY = 0, minY = gObjects.get(0).y;
		
		for (GObject g : gObjects) {
			if (minX > g.x) minX = g.x;
			if (minY > g.y) minY = g.y;
			if (maxX <= g.x + g.width) maxX = g.x + g.width;
			if (maxY <= g.y + g.height) maxY = g.y + g.height;
		}
		this.width = maxX - minX; 
		this.height = maxY - minY;
		this.x = minX;
		this.y = minY;
		
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject go : gObjects) go.paintObject(g);
	}

	@Override
	public void paintLabel(Graphics g) {
		for (GObject go : gObjects) go.paintLabel(g);
	}
	
}
