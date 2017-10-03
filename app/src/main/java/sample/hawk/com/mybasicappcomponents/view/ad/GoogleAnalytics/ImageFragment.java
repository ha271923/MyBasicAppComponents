package sample.hawk.com.mybasicappcomponents.view.ad.GoogleAnalytics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * This fragment displays a featured, specified image.
 */
public class ImageFragment extends Fragment {
    private static final String ARG_PATTERN = "pattern";

    private int resId;

    /**
     * Create a {@link ImageFragment} displaying the given image.
     *
     * @param resId to display as the featured image
     * @return a new instance of {@link ImageFragment}
     */
    public static ImageFragment newInstance(int resId) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PATTERN, resId);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resId = getArguments().getInt(ARG_PATTERN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ga_imagefragment, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(resId);

        return view;
    }

}