import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {

    private static final double BORDER_ENERGY = 1000.0d;

    private Picture picture;

    /**
     * create a seam carver object based on the given picture
     *
     * @param picture
     */
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("Picture cannot be null");
        }
        this.picture = new Picture(picture);
    }

    /**
     * @return current picture
     */
    public Picture picture() {
        return picture;
    }

    /**
     * @return width of current picture
     */
    public int width() {
        return picture.width();
    }

    /**
     * @return height of current picture
     */
    public int height() {
        return picture.height();
    }

    /**
     * energy of pixel at column x and row y
     */
    public double energy(int x, int y) {
        if (x >= picture.width() || y >= picture.height()) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        if (x == 0 || x == picture.width() - 1 || y == 0 || y == picture.height() - 1) {
            return BORDER_ENERGY;
        } else {
            Color leftColor = picture.get(x - 1, y);
            Color rightColor = picture.get(x + 1, y);
            Color topColor = picture.get(x, y - 1);
            Color bottomColor = picture.get(x, y + 1);
            double dx = Math.pow(leftColor.getRed() - rightColor.getRed(), 2) + Math.pow(leftColor.getGreen() - rightColor.getGreen(), 2) +
                    Math.pow(leftColor.getBlue() - rightColor.getBlue(), 2);
            double dy = Math.pow(topColor.getRed() - bottomColor.getRed(), 2) + Math.pow(topColor.getGreen() - bottomColor.getGreen(), 2) +
                    Math.pow(topColor.getBlue() - bottomColor.getBlue(), 2);
            return Math.sqrt(dx + dy);
        }
    }

    /**
     * sequence of indices for horizontal seam
     *
     * @return
     */
    public int[] findHorizontalSeam() {
        double[] distances = new double[width() * height()];
        for (int y = 0; y < width(); y++) {
            for (int x = 0; x < height(); x++) {
                distances[y * height() + x] = energy(y, x);
            }
        }
        return calculateSeam(height(), width(), distances);
    }

    /**
     * sequence of indices for vertical seam
     *
     * @return
     */
    public int[] findVerticalSeam() {
        double[] distances = new double[width() * height()];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                distances[y * width() + x] = energy(x, y);
            }
        }
        return calculateSeam(width(), height(), distances);
    }

    private static int[] calculateSeam(int xsize, int ysize, double[] distances) {
        int[] edgeFrom = new int[xsize * ysize];
        for (int y = 1; y < ysize; y++) {
            for (int x = 0; x < xsize; x++) {
                int currentIndex = y * xsize + x;
                int ancestor = getMinimumAncestor(currentIndex, xsize, distances);
                distances[currentIndex] += distances[(y - 1) * xsize + ancestor];
                edgeFrom[currentIndex] = ancestor;
            }
        }

        int minX = 0;
        for (int x = 1; x < xsize; x++) {
            int index = (ysize - 1) * xsize + x;
            if (distances[index] < distances[(ysize - 1) * xsize + minX]) {
                minX = x;
            }
        }
        int[] seam = new int[ysize];
        for (int y = ysize - 1; y >= 0; y--) {
            seam[y] = minX;
            minX = edgeFrom[y * xsize + minX];
        }

        return seam;
    }

    /**
     * @return column index of the ancestor on the shortest path
     */
    private static int getMinimumAncestor(int index, int xsize, double[] distances) {
        double currentDistance = Integer.MAX_VALUE;
        int minInd = index - xsize - 1;
        // only if index is not leftmost
        if (index % xsize != 0) {
            currentDistance = distances[minInd];
        }
        // check middle
        if (distances[index - xsize] < currentDistance) {
            currentDistance = distances[++minInd];
        }
        int right = index - xsize + 1;
        // check right, only if index is not rightmost
        if (right % xsize != 0 && distances[right] < currentDistance) {
            minInd = right;
//            currentDistance = distances[right]; // useless assignment
        }
        return minInd % xsize;
    }

    /**
     * remove horizontal seam from current picture
     */
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("The seam cannot be null");
        } else if (seam.length != picture.width()) {
            throw new IllegalArgumentException("Invalid seam");
        } else if (picture.width() <= 1) {
            throw new IllegalArgumentException("Image too small");
        }

        if (seam[0] < 0 || seam[0] >= height()) {
            throw new IllegalArgumentException("Invalid seam");
        }
        for (int x = 1; x < width(); x++) {
            if (seam[x] < 0 || seam[x] >= height() || seam[x] < seam[x - 1] - 1 || seam[x] > seam[x - 1] + 1) {
                throw new IllegalArgumentException("Invalid seam");
            }
        }

        Picture newPicture = new Picture(width(), height() - 1);

        for (int x = 0; x < width(); x++) {
            int newY = 0;
            for (int y = 0; y < height(); y++) {
                if (seam[x] != y) {
                    newPicture.set(x, newY, picture.get(x, y));
                    newY++;
                }
            }
        }
        picture = newPicture;
    }

    /**
     * remove vertical seam from current picture
     */
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("The seam cannot be null");
        } else if (seam.length != picture.height()) {
            throw new IllegalArgumentException("Invalid seam");
        } else if (picture.height() <= 1) {
            throw new IllegalArgumentException("Image too small");
        }
        if (seam[0] < 0 || seam[0] >= width()) {
            throw new IllegalArgumentException("Invalid seam");
        }
        for (int y = 1; y < height(); y++) {
            if (seam[y] < 0 || seam[y] >= width() || seam[y] < seam[y - 1] - 1 || seam[y] > seam[y - 1] + 1) {
                throw new IllegalArgumentException("Invalid seam");
            }
        }
        Picture newPicture = new Picture(width() - 1, height());
        for (int y = 0; y < height(); y++) {
            int newX = 0;
            for (int x = 0; x < width(); x++) {
                if (seam[y] != x) {
                    newPicture.set(newX, y, picture.get(x, y));
                    newX++;
                }
            }
        }
        picture = newPicture;
    }
}