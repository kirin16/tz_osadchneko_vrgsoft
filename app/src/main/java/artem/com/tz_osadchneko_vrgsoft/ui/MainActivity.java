package artem.com.tz_osadchneko_vrgsoft.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import artem.com.tz_osadchneko_vrgsoft.R;
import artem.com.tz_osadchneko_vrgsoft.ui.fragments.ArticleFragment;


public class MainActivity extends AppCompatActivity  {

     private ArticleFragment mArticleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mArticleFragment = (ArticleFragment)
                    getSupportFragmentManager().findFragmentByTag(ArticleFragment.class.getSimpleName());

        } else if (mArticleFragment == null) {
            mArticleFragment = new ArticleFragment();
            showArticles();
        }
    }

    public void showArticles() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, mArticleFragment, ArticleFragment.class.getSimpleName());
        ft.commit();
    }

}