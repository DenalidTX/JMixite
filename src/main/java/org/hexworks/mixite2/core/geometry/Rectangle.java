package org.hexworks.mixite2.core.geometry;

public record Rectangle(double x, double y, double width, double height)
{
    public double aspectRatio()
    {
       return (height == 0.0) ? Double.NaN : width / height;
    }


    public boolean contains(double x, double y)
    {
        return this.x <= x
                && this.x + this.width >= x
                && this.y <= y
                && this.y + this.height >= y;
    }

    public boolean contains(Rectangle rectangle) {
        double xMin = rectangle.x;
        double xMax = xMin + rectangle.width;

        double yMin = rectangle.y;
        double yMax = yMin + rectangle.height;

        return xMin > x
                && xMin < x + width
                && xMax > x
                && xMax < x + width
                && yMin > y
                && yMin < y + height
                && yMax > y
                && yMax < y + height;
    }

    public boolean overlaps(Rectangle r) {
        return x < r.x + r.width
                && x + width > r.x
                && y < r.y + r.height
                && y + height > r.y;
    }

    public String toString() {
        return "[$x,$y,$width,$height]";
    }

}
