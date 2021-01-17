package artem.com.tz_osadchneko_vrgsoft.engine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import artem.com.tz_osadchneko_vrgsoft.R;

import artem.com.tz_osadchneko_vrgsoft.ui.fragments.ImageFragment;
import artem.com.tz_osadchneko_vrgsoft.model.Article;
import artem.com.tz_osadchneko_vrgsoft.model.Children;
import artem.com.tz_osadchneko_vrgsoft.utils.DateManager;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleItemViewHolder> {

    private List<Children> childrenList = new ArrayList<>();

    public void setArticles(List<Children> childrenList) {
        this.childrenList = childrenList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);
        return new ArticleItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ArticleItemViewHolder holder, int position) {
        Article articleItem = childrenList.get(position).getArticle();
        holder.bind(articleItem);
        holder.mImage.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_main,
                            ImageFragment.newInstance((articleItem.getThumbnail())))
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {

        return childrenList.size();

    }

    class ArticleItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mAuthor, mTitle, mNumComments, mPublished;
        private ImageView mImage;

        ArticleItemViewHolder(View itemView) {
            super(itemView);

            mAuthor = itemView.findViewById(R.id.tvAuthor);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mNumComments = itemView.findViewById(R.id.tvComments);
            mPublished = itemView.findViewById(R.id.tvPublished);
            mImage = itemView.findViewById(R.id.ivImg);

        }

        public void bind(Article article){
            mAuthor.setText(nullCheck(article.getAuthor()));
            mTitle.setText(nullCheck(article.getTitle()));
            mNumComments.setText(nullCheck(article.getNumComments()
                    + itemView.getResources().getString(R.string.num_comments)));
            Picasso.with(itemView.getContext()).load(article.getThumbnail()).into(mImage);

            try {
                mPublished.setText(checkDate(article.getCreatedUtc()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        private String nullCheck(String str){
            return (str == null || str.equals("")) ? "" : str;
        }

        /**
         * Processes and returns the result in the form of 'just now or hours/minutes ago'
         * @param x - expected created_utc value
         * @return - result in the form of 'just now or hours/minutes ago'
         */

        private String checkDate(long x) throws ParseException{
            String result, difference;
            difference = DateManager.getInstance().getDifference(x);
            String[] separated = difference.split(":");
            if (separated[0].equals("0") && separated[1].equals("0")){
                result = itemView.getContext().getResources().getString(R.string.just_now);
            } else if (separated[0].equals("1") && separated[1].equals("0")){
                result = separated[0] + itemView.getContext().getResources().getString(R.string.hour);
            } else if (separated[1].equals("1") && separated[0].equals("0")) {
                result = itemView.getContext().getResources().getString(R.string.just_now);
            } else if (separated[0].equals("0")){
                result = separated[1] + itemView.getContext().getResources().getString(R.string.mins);
            }
            else {
                result = separated[0] + itemView.getContext().getResources().getString(R.string.hours);
            }

            return  result;

        }

    }

}
