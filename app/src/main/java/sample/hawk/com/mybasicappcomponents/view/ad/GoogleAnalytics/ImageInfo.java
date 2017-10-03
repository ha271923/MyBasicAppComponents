package sample.hawk.com.mybasicappcomponents.view.ad.GoogleAnalytics;

/**
 * Pair of resource IDs representing an image and its title.
 */
public class ImageInfo {

    public final int image;
    public final String title;

    /**
     * Create a new ImageInfo.
     * @param image resource of image
     * @param title resource of title
     */
    public ImageInfo(int image, String title) {
        this.image = image;
        this.title = title;
    }

}