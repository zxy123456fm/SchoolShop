package utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by swg on 2017/2/17.
 */

/**
 * 图片加载器
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        UILUtils.displayImageNoAnim((String) path,imageView);
    }
}
