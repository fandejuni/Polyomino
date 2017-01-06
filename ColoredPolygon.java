import java.awt.*;

public class ColoredPolygon {

    public Color color;
    public Polygon polygon;
    
    public ColoredPolygon(int[] xcoords, int[] ycoords, Color new_color) {
        int n = xcoords.length;
        color = new_color;
        polygon = new Polygon(xcoords, ycoords, n);
    }


}
