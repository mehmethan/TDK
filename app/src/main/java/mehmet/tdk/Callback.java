package mehmet.tdk;

/**
 * Created by mehmet on 20.09.2015.
 */
public interface Callback {

    public void onProgress();
    public void onResult(String http_result);

}
