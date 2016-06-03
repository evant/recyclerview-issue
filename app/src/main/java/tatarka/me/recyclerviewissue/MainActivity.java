package tatarka.me.recyclerviewissue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        items.add("F");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView textView = new TextView(parent.getContext());
                textView.setPadding(0, 100, 0, 100);
                return new RecyclerView.ViewHolder(textView) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(items.get(position));
            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.indexOf("A") == 0) {
                    items.removeAll(Arrays.asList("A", "B"));
                    recyclerView.getAdapter().notifyItemRangeRemoved(0, 2);
                    items.addAll(Arrays.asList("A", "B"));
                    recyclerView.getAdapter().notifyItemRangeInserted(4, 2);
                } else {
                    items.removeAll(Arrays.asList("A", "B"));
                    recyclerView.getAdapter().notifyItemRangeRemoved(4, 2);
                    items.addAll(0, Arrays.asList("A", "B"));
                    recyclerView.getAdapter().notifyItemRangeInserted(0, 2);
                }
            }
        });
    }
}
