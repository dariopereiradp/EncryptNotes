package dp.cryptd.activities;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import dp.cryptd.R;
import dp.cryptd.utils.ImageUtils;

/**
 * Simple activity to view a image using an ImageView and Glide
 */
public class ImageViewerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        ImageView imageView = findViewById(R.id.imageView);
        String profilePictureName = getIntent().getExtras().getString(ImageUtils.IMAGE_VIEWER);
        Glide.with(this)
                .load(profilePictureName)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).error(R.drawable.ic_addphoto_24dp)
                .placeholder(R.drawable.ic_addphoto_24dp)
                .into(imageView);

    }

}