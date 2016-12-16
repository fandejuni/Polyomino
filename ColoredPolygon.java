import java.awt.*;

public class ColoredPolygon {

    public Color color;
    public Polygon polygon;
    
    public ColoredPolygon(int[] xcoords, int[] ycoords, Color color) {
        int n = xcoords.length;
        color = color;
        polygon = new Polygon(xcoords, ycoords, n);
    }


}
